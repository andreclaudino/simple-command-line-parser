name := "simple-command-line-parser"
organization := "com.b2wdigital.iafront"
version := "2.0-SNAPSHOT"

enablePlugins(GitBranchPrompt)
git.gitTagToVersionNumber := { tag: String =>
  if(tag matches "v[0-9]+\\..*") Some(tag)
  else None
}

scalaVersion := "2.11.12"

libraryDependencies ++=Seq(
  "org.scalatest" %% "scalatest" % "3.1.0" % "test"
)

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
assembleArtifact in assemblyPackageScala := false
assemblyJarName := s"${name.value}-${version.value}.jar"

artifact in (Compile, assembly) := {
  val art = (artifact in (Compile, assembly)).value
  art.withClassifier(Some("assembly"))
}
addArtifact(artifact in (Compile, assembly), assembly)

/// Publishing
publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)
publishMavenStyle := true

licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

sonatypeProfileName := "com.b2wdigital"
publishTo := sonatypePublishToBundle.value

import xerial.sbt.Sonatype._
sonatypeProjectHosting := Some(GitHubHosting("andreclaudino", "simple-command-line-parser", ""))

homepage := Some(url("https://github.com/andreclaudino/simple-command-line-parser"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/andreclaudino/simple-command-line-parser"),
    "scm:git@github.com:andreclaudino/simple-command-line-parser.git"
  )
)