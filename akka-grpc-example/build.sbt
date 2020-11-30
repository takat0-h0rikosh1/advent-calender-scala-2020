ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "akka-grpc-example",
    libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-http" % "10.2.1",
        "com.typesafe.akka" %% "akka-stream" % "2.6.10",
        "com.typesafe.akka" %% "akka-discovery" % "2.6.10",
        "com.typesafe.akka" %% "akka-protobuf" % "2.6.10",
        "com.typesafe.akka" %% "akka-http2-support" % "10.2.1",
        "ch.qos.logback" % "logback-classic" % "1.2.3"
    )
  )
  .enablePlugins(AkkaGrpcPlugin)


