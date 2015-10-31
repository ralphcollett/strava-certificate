package grosvenorknight.stravacertificate

object Main extends App {

  val accessToken = args(0)
  val client = new Strava(accessToken)
  for (
   athlete <- client.athlete(2204639)
  ) yield println(athlete)

}
