name := "ursa"

version := "1.0"

lazy val `ursa` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(jdbc, cache, javaWs, "mysql" % "mysql-connector-java" % "5.1.27")

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
