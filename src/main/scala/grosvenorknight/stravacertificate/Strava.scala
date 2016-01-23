package grosvenorknight.stravacertificate

import argonaut.Argonaut._
import dispatch.Defaults._
import dispatch._
import org.joda.time.DateTime

import scalaz._

class Strava(accessToken: String) {

  val authString = s"Bearer $accessToken"

  def activities(after: Option[DateTime], before: Option[DateTime]): Future[RetrievalError \/ List[Activity]] = {
    def asEpochSeconds(dt: DateTime) = String.valueOf(dt.toDate.toInstant.toEpochMilli / 1000)

    var req = url("https://www.strava.com/api/v3/athlete/activities")
      .addHeader("Authorization", authString)

    req = before.map(dt => req.addParameter("before", asEpochSeconds(dt))).getOrElse(req)
    req = after.map(dt => req.addParameter("after", asEpochSeconds(dt))).getOrElse(req)

    for (response <- Http(req OK as.String)) yield {
      response.decodeEither[List[Activity]].leftMap(RetrievalError)
    }
  }

  case class RetrievalError(message: String)
}
