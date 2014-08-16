name := "scalaz-outlaws"

organization := "org.typelevel"

version := "0.2"

scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.10.4", "2.11.0")

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-Yno-adapted-args",
  "-Ywarn-value-discard"
)

resolvers ++= Seq(Resolver.sonatypeRepo("releases"),
                  Resolver.sonatypeRepo("snapshots"))

libraryDependencies ++= Seq(
  "org.scalaz"         %% "scalaz-core"               % "7.1.0-RC1",
  "org.scalaz"         %% "scalaz-scalacheck-binding" % "7.1.0-RC1"  % "test")

seq(bintraySettings:_*)

publishMavenStyle := true

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

scmInfo := Some(ScmInfo(url("https://github.com/stew/scalaz-outlaws"),
  "git@github.com:stew/scalaz-outlaws.git"))

bintray.Keys.packageLabels in bintray.Keys.bintray :=
Seq("typeclasses", "functional programming", "scala")

bintray.Keys.repository in bintray.Keys.bintray := "snapshots"

osgiSettings

OsgiKeys.bundleSymbolicName := "org.scalaz.outlaws"

OsgiKeys.exportPackage := Seq("scalaz.outlaws.*")

OsgiKeys.importPackage := Seq(
  """scala.*;version="$<range;[===,=+);$<@>>"""",
  """scalaz.*;version="$<range;[===,=+);$<@>>"""",
  "*"
)
