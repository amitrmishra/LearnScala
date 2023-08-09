ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.8"

lazy val root = (project in file("."))
  .settings(
    name := "LearnScala"
  )

//resolvers ++= Seq(
//  "Typesafe" at "http://repo.typesafe.com/typesafe/releases/"
//)

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-lang3" % "3.12.0",
  "com.spotify.sparkey" % "sparkey" % "3.2.4",
  "com.google.protobuf" % "protobuf-java" % "3.22.2",
  "com.spotify" % "uri" % "0.1.142",
  "com.spotify.data.platform" %% "platform-parser" % "1.2.132",
  "org.example" % "LearnJava" % "1.0-SNAPSHOT" from "file:/Users/amitr/IdeaProjects/LearnJava/target/LearnJava-1.0-SNAPSHOT.jar"
)

val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

// https://mvnrepository.com/artifact/com.spotify/magnolify-avro
libraryDependencies += "com.spotify" %% "magnolify-avro" % "0.6.2"

// https://mvnrepository.com/artifact/com.google.guava/guava
libraryDependencies += "com.google.guava" % "guava" % "31.1-jre"
