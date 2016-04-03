package grosvenorknight.stravacertificate

import org.joda.time.DateTime
import org.joda.time.format.PeriodFormatterBuilder

import scalaz.{-\/, \/-}


object Main extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  val accessToken = args(0)
  val client = new Strava(accessToken)


  val formatter = new PeriodFormatterBuilder()
    .minimumPrintedDigits(2)
    .appendHours()
    .printZeroAlways()
    .appendSuffix(":")
    .appendMinutes()
    .appendSuffix(":")
    .appendSeconds()
    .toFormatter

  def format(activity: Activity) =
    s"${activity.name}" +
      s" - ${BigDecimal(activity.distanceInMetres / 1000).setScale(2, BigDecimal.RoundingMode.HALF_UP)} km" +
      s" - ${formatter.print(activity.duration.toPeriod)}"

  val output = for {
    response <- client.activities(Some(DateTime.now.minusDays(7)), Some(DateTime.now))
  } yield {
    response match {
      case \/-(activities) =>
        activities.filter(_.activityType == "Run").reverse.map(format).mkString("\n\n")
      case -\/(error) => s"error $error"
    }
  }
  output.foreach(println)
}
