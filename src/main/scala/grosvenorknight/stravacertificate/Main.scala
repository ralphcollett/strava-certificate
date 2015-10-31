package grosvenorknight.stravacertificate

import scalaz._
import Scalaz._
import kiambogo.scrava.ScravaClient

object Main extends App {

  val accessToken = args(0)
  val client = new ScravaClient(accessToken)
  for (
   athlete <- client.retrieveAthlete(Some(2204639)).leftMap(_ => new AthleteRetrievalError())
  ) yield println(athlete)

  class AthleteRetrievalError
}
