package grosvenorknight.stravacertificate

import org.joda.time.DateTime
import org.joda.time.format.PeriodFormatterBuilder

import scalaz.{-\/, \/-}


object Main extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  val accessToken = args(0)
  val client = new Strava(accessToken)


  val formatter = new PeriodFormatterBuilder()
    .appendHours()
    .appendSuffix(":")
    .appendMinutes()
    .appendSuffix(":")
    .appendSeconds()
    .toFormatter

  def format(activity: Activity) =
    s"${activity.name}" +
      s" - ${BigDecimal(activity.distanceInMetres / 1000).setScale(2, BigDecimal.RoundingMode.HALF_UP)} km" +
      s" - ${formatter.print(activity.duration.toPeriod)}"

  for {
    response <- client.activities(Some(DateTime.now.minusDays(7)), Some(DateTime.now))
  } yield {
    response match {
      case \/-(activities) => println(activities.map(format).mkString("\n\n"))
      case -\/(error) => println(s"error $error")
    }
  }
}
