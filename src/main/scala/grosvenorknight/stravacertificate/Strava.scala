package grosvenorknight.stravacertificate

import argonaut.Argonaut._
import argonaut._
import dispatch.Defaults._
import dispatch._

import scalaz._

class Strava(accessToken: String) {

  val authString = s"Bearer $accessToken"

  def activity(id: Int): Future[ActivityRetrievalError \/ Activity] = {
    val svc = url(s"https://www.strava.com/api/v3/activities/$id").addHeader("Authorization", authString)
    for (response <- Http(svc OK as.String)) yield response.decodeEither[Activity].leftMap(ActivityRetrievalError)
  }

  case class Activity(id: Int, name: String, time: Int, activityType: String)

  implicit def ActivityDecodeJson: DecodeJson[Activity] =
    DecodeJson(c => for {
      id <- (c --\ "id").as[Int]
      name <- (c --\ "name").as[String]
      time <- (c --\ "elapsed_time").as[Int]
      activityType <- (c --\ "type").as[String]
    } yield Activity(id, name, time, activityType))

  case class ActivityRetrievalError(message: String)

}
