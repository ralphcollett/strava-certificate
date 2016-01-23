name := "strava-certificate"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.1",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "io.argonaut" %% "argonaut" % "6.1",
  "joda-time" % "joda-time" % "2.9.1",
  "org.slf4j" % "slf4j-api" % "1.7.13",
  "org.slf4j" % "slf4j-simple" % "1.7.13"
)