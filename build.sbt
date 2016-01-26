enablePlugins(PlayScala)

organization := "com.thoughtworks.microbuilder.tutorial"

name := "organization-list"

scalaVersion in ThisBuild := "2.11.7"

routesGenerator := InjectedRoutesGenerator

libraryDependencies += "com.thoughtworks.each" %% "each" % "0.5.1"

libraryDependencies += "com.thoughtworks.microbuilder" %% "microbuilder-play" % "3.1.0"

libraryDependencies += "com.thoughtworks.microbuilder.tutorial" %% "github-sdk" % "1.0.2"
