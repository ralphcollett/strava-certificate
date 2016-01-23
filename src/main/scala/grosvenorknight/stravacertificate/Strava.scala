package grosvenorknight.stravacertificate

import argonaut.Argonaut._
import dispatch.Defaults._
import dispatch._

import scalaz._

class Strava(accessToken: String) {

  val authString = s"Bearer $accessToken"

  def activity(id: Int): Future[RetrievalError \/ Activity] = {
    val svc = url(s"https://www.strava.com/api/v3/activities/$id").addHeader("Authorization", authString)
    for (response <- Http(svc OK as.String)) yield response.decodeEither[Activity].leftMap(RetrievalError)
  }

  def athlete(id: Int): Future[RetrievalError \/ Athlete] = {
    val svc = url(s"https://www.strava.com/api/v3/athletes/$id").addHeader("Authorization", authString)
    for (response <- Http(svc OK as.String)) yield response.decodeEither[Athlete].leftMap(RetrievalError)
  }

  case class RetrievalError(message: String)
}
