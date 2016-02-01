object mashup extends App {

  if (sys.props.get("PROPS").isEmpty) {
    if (args.length < 4) {
      System.err.println("Usage: mashup <consumer key> <consumer secret> " +
        "<access token> <access token secret>")
      System.exit(1)
    }
    val Array(consumerKey, consumerSecret, accessToken, accessTokenSecret) = args.take(4)

    System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
    System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
    System.setProperty("twitter4j.oauth.accessToken", accessToken)
    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)
  }

  def reactiveProjects = {
    val url = "https://api.github.com/search/repositories?q=reactive"
    io.Source.fromURL(url).mkString
  }
  val projs = reactiveProjects

  def names(json: String) = {
    import spray.json._
    json.parseJson.asJsObject.getFields("items")
      .collect { case JsArray(vs) => vs }.flatten
      .flatMap(_.asJsObject.getFields("name"))
      .collect { case JsString(name) => name }
  }

  val reactives = names(projs).toSet.toSeq.sorted.take(10)
  println(s"10 Reactive Projects: ${reactives.mkString(", ")}")

  import twitter4j._
  val twitter = new TwitterFactory().getInstance()
  reactives.foreach { p =>
    println(s"Searching for tweets about $p")
    val tweets = twitter.search().search(new Query(p)).getTweets
    println(tweets)
  }
}

