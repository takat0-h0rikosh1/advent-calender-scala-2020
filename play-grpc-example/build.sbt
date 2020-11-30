name := """play-grpc-example"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, PlayAkkaHttp2Support, AkkaGrpcPlugin)


scalaVersion := "2.13.4"

libraryDependencies ++= Seq(
  guice,
 "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
 "com.lightbend.play" %% "play-grpc-runtime" % "0.9.1",
  "com.typesafe.akka" %% "akka-discovery" % "2.6.8",
)

import play.grpc.gen.scaladsl.PlayScalaServerCodeGenerator
akkaGrpcExtraGenerators += PlayScalaServerCodeGenerator

import play.grpc.gen.scaladsl.PlayScalaClientCodeGenerator
akkaGrpcExtraGenerators += PlayScalaClientCodeGenerator
