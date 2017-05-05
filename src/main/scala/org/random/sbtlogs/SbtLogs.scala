package org.random.sbtlogs

import sbt._
import Keys._
import java.io.FileWriter
import scala.io.Source

object SbtLogs extends AutoPlugin {
  object autoImport {
    val addLogsEndpoints = inputKey[Unit]("Add endpoints to manage play application logs.")
  }

  import autoImport._

  override def projectSettings: Seq[Setting[_]] = Seq(
    addLogsEndpointsSetting)

  def addLogsEndpointsSetting: Setting[_] = addLogsEndpoints := {
    addRouteEntries
    addController
    println("Added log endpoints.")
  }

  def addRouteEntries = {
    val logRoutes = getResourceFileContents("routes.append")
    addTextToFile("./conf/routes", logRoutes)
  }

  def addController = {
    val controllerSource = getResourceFileContents("LogsController.scala")
    addTextToFile("./app/controllers/LogsController.scala", controllerSource)
  }

  def getResourceFileContents(fileName: String) = {
    val resourceStream = getClass().getResourceAsStream("/" + fileName)
    Source.fromInputStream(resourceStream).getLines().mkString("\n")
  }

  def addTextToFile(dstFileName: String, text: String) = {
    val fw = new FileWriter(dstFileName, true)
    try {
      fw.write(text)
    } finally fw.close()
  }
}
