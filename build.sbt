name := "cycle-detection"
version := "0.1"
scalaVersion := "2.12.6"
scalacOptions ++= Seq(
  "-language:_",
  "-Ypartial-unification",
  "-Xfatal-warnings"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test