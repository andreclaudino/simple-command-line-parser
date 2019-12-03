name := "simple-command-line-parser"
organization := "com.b2wdigital.iafront"
version := "2.0-SNAPSHOT"

scalaVersion := "2.11.12"
crossScalaVersions := Seq("2.11.12", "2.12.9","2.13.1")

val nexusB2w = sys.env.getOrElse("DEPLOY_REPOSITORY", "")

val username = sys.env.getOrElse("DEPLOY_USER", "")
val password = sys.env.getOrElse("DEPLOY_PASSWORD", "")

publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)
credentials += Credentials("Sonatype Nexus Repository Manager", nexusB2w, username, password)

publishTo := Some("snapshots" at "http://" + nexusB2w + "/repository/maven-private/")

publishMavenStyle := true

libraryDependencies ++=Seq(
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