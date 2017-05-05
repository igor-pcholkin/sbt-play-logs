# sbt-play-logs - Sbt plugin to manage configuration of deployed Play! application

Suppose that you work in a big company with lot of deployments where it is difficult to have immediate access to 
production deployment configuration, e.g. log files.
This plugin allows to enhance web application with REST endpoints which provide access to runtime information and configuration 
of application running in production.

Example commands:

sbt addLogsEndpoints - add REST endpoint to list log files (i.e. contents of /log folder) of monitored application.


