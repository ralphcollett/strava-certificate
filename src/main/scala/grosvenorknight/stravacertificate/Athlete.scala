package grosvenorknight.stravacertificate

import argonaut.DecodeJson

case class Athlete(id: Int, email: String, firstName: String, surname: String)

object Athlete {
  implicit def AthleteDecodeJson: DecodeJson[Athlete] =
    DecodeJson(c => for {
      id <- (c --\ "id").as[Int]
      email <- (c --\ "email").as[String]
      firstName <- (c --\ "firstname").as[String]
      surname <- (c --\ "lastname").as[String]
    } yield Athlete(id, email, firstName, surname))
}
