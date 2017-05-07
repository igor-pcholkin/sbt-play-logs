package org.random.sbtlogs

import java.io.FileWriter

import scala.io.Source

import sbt._
import sbt.Keys._
import scalaz._
import scalaz.Scalaz._
import scalaz.effect.IO

object SbtLogs extends AutoPlugin {
  object autoImport {
    val addLogsEndpoints = inputKey[Unit]("Add endpoints to manage play application logs.")
  }

  import autoImport._

  override def projectSettings: Seq[Setting[_]] = Seq(
    addLogsEndpointsSetting)

  def addLogsEndpointsSetting: Setting[_] = addLogsEndpoints := {
    val validatedEffects = for {
      effect <- Seq(addController, addRouteEntries)
    } yield (effect)

    validatedEffects.reduce { (validatedEffect, newEffect) =>
      if (validatedEffect.isLeft)
        validatedEffect
      else if (newEffect.isLeft)
        newEffect
      else validatedEffect |+| newEffect
    }.fold(onFailure, onSuccess)
  }

  def onFailure(error: String) = {
    scala.Console.err.println(error)
  }

  def onSuccess(effect: IO[Unit]) = {
    effect.unsafePerformIO()
    println("Added log endpoints.")
  }

  def addRouteEntries = {
    val logRoutes = getResourceFileContents("routes.append")
    val writeFile = "./conf/routes"
    if (new File(writeFile).exists()) {
      IO {
        addTextToFile(writeFile, logRoutes)
      }.right
    } else {
      s"$writeFile doesn't exist. Aborting.".left
    }
  }

  def addController = IO {
    val controllerSource = getResourceFileContents("LogsController.scala.template")
    addTextToFile("./app/controllers/LogsController.scala", controllerSource)
  }.right

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
