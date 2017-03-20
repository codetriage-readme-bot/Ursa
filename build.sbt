name := "ursa"

version := "1.0"

libraryDependencies ++= Seq(javaWs)


lazy val `ursa` = (project in file(".")).enablePlugins(PlayJava)

val appDependencies = Seq(
  "ws.securesocial" %% "securesocial" % "master-SNAPSHOT"
)

//val main = play.Project(appName, appVersion, appDependencies).settings(
//  resolvers += Resolver.sonatypeRepo("snapshots")
//)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(javaJdbc, cache, javaWs)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

