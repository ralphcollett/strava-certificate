package grosvenorknight.stravacertificate

import argonaut.DecodeJson
import org.joda.time.Duration

case class Activity(id: Int, name: String, distanceInMetres: Float, duration: Duration, activityType: String)

object Activity {
  implicit def ActivityDecodeJson: DecodeJson[Activity] =
    DecodeJson(c => for {
      id <- (c --\ "id").as[Int]
      name <- (c --\ "name").as[String]
      distance <- (c --\ "distance").as[Float]
      durationInSeconds <- (c --\ "elapsed_time").as[Int]
      activityType <- (c --\ "type").as[String]
    } yield Activity(id, name, distance, Duration.standardSeconds(durationInSeconds), activityType))
}
