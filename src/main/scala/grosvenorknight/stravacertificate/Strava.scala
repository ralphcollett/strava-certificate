package grosvenorknight.stravacertificate

import kiambogo.scrava.ScravaClient
import kiambogo.scrava.models.AthleteSummary

import scala.util.Try
import scalaz._
import Scalaz._

class Strava(accessToken: String) {

  val client = new ScravaClient(accessToken)

  def athlete(id: Int): AthleteRetrievalError \/ AthleteSummary = {
    Try(client.retrieveAthlete(Some(id))) match {
      case scala.util.Success(athlete) => athlete.leftMap(_ => new AthleteRetrievalError(s"Could not retrieve $id")).disjunction
      case scala.util.Failure(f) => new AthleteRetrievalError(f.getMessage).left[AthleteSummary]
    }

  }
}

case class AthleteRetrievalError(message: String)
