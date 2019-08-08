val FastParseVersion = "2.1.3"

lazy val root = (project in file("."))
  .settings(
    organization := "org.ioreskovic.candies",
    name := "candies",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.0",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "fastparse" % FastParseVersion,
    ),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
  )
