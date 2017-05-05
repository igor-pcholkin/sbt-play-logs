sbtPlugin := true

organization := "org.random"

name := "sbt-play-logs"

version := "1.0.0"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-deprecation", "-feature")

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
)

publishMavenStyle := false

