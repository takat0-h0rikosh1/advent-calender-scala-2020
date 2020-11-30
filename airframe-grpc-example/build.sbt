name := "airframe-grpc-example"

ThisBuild / scalaVersion := "2.13.4"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

val airframeGrpc = "org.wvlet.airframe" %% "airframe-http-grpc" % "20.11.0"

lazy val api = (project in file("api"))
  .settings(
    libraryDependencies += airframeGrpc
  )

lazy val server = (project in file("server"))
  .settings(libraryDependencies += airframeGrpc)
  .dependsOn(api)

lazy val client = (project in file("client"))
  .settings(
    libraryDependencies += airframeGrpc,
    airframeHttpClients := Seq("api:grpc")
  )
  .enablePlugins(AirframeHttpPlugin)
  .dependsOn(api)
