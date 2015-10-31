package grosvenorknight.stravacertificate

import dispatch.Future

import scalaz.\/


object Main extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  val accessToken = args(0)
  val client = new Strava(accessToken)
  val future: Future[\/[client.ActivityRetrievalError, client.Activity]] = client.activity(417548680)
  for (dis <- future) yield {
    for (activity <- dis) yield println(activity)
  }
}
