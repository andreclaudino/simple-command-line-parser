name := "simple-command-line-parser"
organization := "com.b2wdigital.iafront"
version := "1.1-SNAPSHOT"

scalaVersion := "2.11.12"
crossScalaVersions := Seq("2.11.12", "2.12.9")

libraryDependencies ++=Seq(
  "com.monovore"     %% "decline"       % "0.5.0",
  "org.clapper" %% "argot" % "1.0.3",
  /// Testes
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
assembleArtifact in assemblyPackageScala := false
assemblyJarName := s"${name.value}-${version.value}.jar"

artifact in (Compile, assembly) := {
  val art = (artifact in (Compile, assembly)).value
  art.withClassifier(Some("assembly"))
}
addArtifact(artifact in (Compile, assembly), assembly)