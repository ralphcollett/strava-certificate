package grosvenorknight.stravacertificate


object Main extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  val accessToken = args(0)
  val client = new Strava(accessToken)

  for (futureActivity <- client.activity(417548680)) yield {
    for (activity <- futureActivity) yield {
      for (futureAthlete <- client.athlete(activity.athleteId)) yield {
        for (athlete <- futureAthlete) yield {
          println(s"${activity.name} completed by ${athlete.firstName} ${athlete.surname} in ${activity.duration}")
        }
      }
    }
  }
}
