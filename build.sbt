name := "scala_neighbors"

version := "0.1"

scalaVersion := "2.13.0"

libraryDependencies+= "org.scalatest" %% "scalatest" % "3.0.8" % "test"

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

// (optional) If you need scalapb/scalapb.proto or anything from
// google/protobuf/*.proto
libraryDependencies += "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf"
