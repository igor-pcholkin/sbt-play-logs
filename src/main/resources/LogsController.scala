package controllers

import javax.inject._
import play.api.mvc.Action
import play.api.mvc.Controller
import java.io.File

@Singleton
class LogsController @Inject() extends Controller {
  def sendLogFiles() = Action {
    val logFiles = new File("./logs").list().mkString("\n")
    Ok(logFiles)
  }
}
