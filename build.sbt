name := "ShoppingCart"
version := "1.0"
scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.9" % Test,
  "com.beachape" %% "enumeratum" % "1.7.+",
  "org.typelevel" %% "cats-core" % "2.8.0"
)

Compile / run / mainClass := Some("ShoppingCartApp")