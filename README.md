GitHub/Twitter mashup
===

This is a sample project to showcase how to query for "reactive" project and tweets that mention them.

# How to run

Execute `sbt assembly`.

```
$ sbt assembly
...
[info] Packaging ...target/scala-2.11/github-twitter-mashup-assembly-1.0.0.jar
```

Once built, you can run the uber-jar using its full path.

```
$ java -jar target/scala-2.11/github-twitter-mashup-assembly-1.0.0.jar
Usage: mashup <consumer key> <consumer secret> <access token> <access token secret>
```

You need to specify twitter credentials as described in the [official documentation](https://dev.twitter.com/overview/api).

# How to run using twitter4j.properties

Rather than specifying the twitter credentials on the command line, you can save them in `twitter4j.properties` file and use as follows:

```
$ java -DPROPS -cp .:target/scala-2.11/github-twitter-mashup-assembly-1.0.0.jar mashup
```

The key points to remember is to use `-DPROPS` system property and the directory with the `twitter4j.properties` file as the first entry on the CLASSPATH.

PROTIP: Use `twitter4j.properties.template` as the template.

Good luck!
