sbtPlugin := true

organization := "org.random"

name := "sbt-play-logs"

version := "1.0.0"

scalaVersion := "2.10.6"

scalacOptions ++= Seq("-deprecation", "-feature")

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
    "org.scalaz"    %% "scalaz-core"   % "7.1.0",
    "org.scalaz"    %% "scalaz-effect" % "7.1.0" )
  
publishMavenStyle := false
