name := """l12group3"""
organization := "psu.edu"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  guice,
  "com.h2database" % "h2" % "1.4.199",
  "org.hibernate" % "hibernate-core" % "5.4.9.Final",
)

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"
