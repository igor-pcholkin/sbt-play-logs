# sbt-play-logs - Sbt plugin to manage configuration of deployed Play! application

Suppose that you work in a big company with lot of deployments where it is difficult to have immediate access to 
production deployment configuration, e.g. log files.
This plugin allows to enhance web application with REST endpoints which provide access to runtime information and configuration 
of application running in production.

Example commands:

sbt addLogsEndpoints - add REST endpoint to list log files (i.e. contents of /log folder) of monitored application.

NB! In case if project using this plugin also uses sbteclipse plugin it could be necessary to ensure that both use
the same scalaz versions. Current configuration supposes that sbteclipse 4.0.0 could be used (with scalaz version 7.1.0).
If versions don't match NoClassDefFoundError could be thrown.
