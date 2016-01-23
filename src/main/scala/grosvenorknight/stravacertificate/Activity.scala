package grosvenorknight.stravacertificate

import argonaut.DecodeJson
import scala.concurrent.duration._

case class Activity(id: Int, name: String, duration: Duration, activityType: String, athleteId: Int)

object Activity {
  implicit def ActivityDecodeJson: DecodeJson[Activity] =
    DecodeJson(c => for {
      id <- (c --\ "id").as[Int]
      name <- (c --\ "name").as[String]
      durationInSeconds <- (c --\ "elapsed_time").as[Int]
      activityType <- (c --\ "type").as[String]
      athleteId <- (c --\ "athlete" --\ "id").as[Int]
    } yield Activity(id, name, durationInSeconds seconds, activityType, athleteId))
}
