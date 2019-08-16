val FastParseVersion = "2.1.3"
val CatsVersion = "2.0.0-RC1"

lazy val root = (project in file("."))
  .settings(
    organization := "org.ioreskovic.candies",
    name := "candies",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.0",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "fastparse" % FastParseVersion,
      "org.typelevel" %% "cats-core" % CatsVersion
    ),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
  )
