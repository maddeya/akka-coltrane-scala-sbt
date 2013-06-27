name := "akka-project-name"
 
version := "1.0"
 
scalaVersion := "2.10.2"
 
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
 
libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-actor_2.10" % "2.2.0-RC2",
  "com.typesafe.akka" % "akka-testkit_2.10" % "2.2.0-RC2",
  "com.typesafe.akka" % "akka-kernel_2.10" % "2.2.0-RC2",
  "com.typesafe.akka" % "akka-agent_2.10" % "2.2.0-RC2",
  "com.typesafe.akka" % "akka-transactor_2.10" % "2.2.0-RC2",
  "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test",
  "junit" % "junit" % "4.11" % "test"
)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")


