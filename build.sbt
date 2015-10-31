name := "strava-certificate"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.1",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "io.argonaut" %% "argonaut" % "6.1"
)