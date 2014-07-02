name := "scalaz-outlaws"

organization := "org.typelevel"

scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.10.4", "2.11.0")

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-Yno-adapted-args"
)

resolvers ++= Seq(Resolver.sonatypeRepo("releases"),
                  Resolver.sonatypeRepo("snapshots"))

libraryDependencies ++= Seq(
  "org.scalaz"         %% "scalaz-core"               % "7.1.0-RC1",
  "org.scalaz"         %% "scalaz-scalacheck-binding" % "7.1.0-RC1"  % "test")
