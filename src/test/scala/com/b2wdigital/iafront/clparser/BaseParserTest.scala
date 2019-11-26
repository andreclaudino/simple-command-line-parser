package com.b2wdigital.iafront.clparser

import org.scalatest.{FlatSpec, Matchers}

class BaseParserTest extends FlatSpec with Matchers {

  private val sourceArgs:Array[String] = Array("-s","/home/user/projetos/Scala/directory-project-name")

  private val ourtputArgs:Array[String] = Array("-o","/home/user/projetos/Scala/directory-output-data-name")

  private val confsArgs:Array[String] = Array("-c","sparkMaster","local[2]","conf1", "item1","conf2",
    "item2","conf3","item3")

  "sourcePathOption" should
    """
      |return a map(dictionary) where the keys will be source path, output path and configurations and the values
      |will be what was passed in the command line
      |""".stripMargin in {
    val sourceTeste:Option[String] = new BaseParser(sourceArgs,Some("teste")).sourcePathOption

    sourceTeste shouldBe  Some("/home/user/projetos/Scala/directory-project-name")
  }
}
