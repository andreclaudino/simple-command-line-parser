package com.b2wdigital.iafront.clparser

import org.scalatest.{FlatSpec, Matchers}

class BaseParserTest extends FlatSpec with Matchers {

  private val sourceArgs:Array[String] = Array("-s","/home/user/projetos/Scala/directory-project-name")

  private val errorArgs:Array[String] = Array("-s","-c","key1=value1")

  private val outputArgs:Array[String] = Array("-o","/home/user/projetos/Scala/directory-output-data-name")

  private val emptyArray:Array[String] = Array()

  private val confsArgs:Array[String] = Array("-c","key1=value1","-c","key2=value2","-c","key3=value3")

  private val confsArgs2:Array[String] = Array("-c","key1=value1","-c","key2=value=2","-c","key3=value3")

  private val allConfs:Array[String] = Array("-s","/home/user/projetos/Scala/directory-project-name","-o",
    "/home/user/projetos/Scala/directory-output-data-name","-c","key1=value1","-c","key2=value2","-c","key3=value3")

  //--------------------------------------------------------------------------------------------------------------------
  //----------------------------------------BEGINNING OF THE TEST-------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------

  "sourcePathOption" should
    """
      |return a string passed in the command line representing the path of a source file
      |""".stripMargin in {
    val sourceTeste:Option[String] = new BaseParser(sourceArgs,Some("teste")).sourcePathOption

    sourceTeste shouldBe  Some("/home/user/projetos/Scala/directory-project-name")
  }
  //--------------------------------------------------------------------------------------------------------------------
  //----------------------------------------END OF THIS TEST-------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------
  "sourcePathOption" should
    """
      |return a None value because we have no source in the array
      |""".stripMargin in {
    val sourceTeste:Option[String] = new BaseParser(outputArgs,Some("teste")).sourcePathOption

    sourceTeste shouldBe  None
  }

  //--------------------------------------------------------------------------------------------------------------------
  //---------------------------------------END OF THIS TEST-------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------

  "outputPathOption" should
    """
      |return a string passed in the command line representing the path where the program will save the result
      |""".stripMargin in {
    val outputTeste:Option[String] = new BaseParser(outputArgs,Some("teste")).outputPathOption

    outputTeste shouldBe  Some("/home/user/projetos/Scala/directory-output-data-name")
  }
  //--------------------------------------------------------------------------------------------------------------------
  //----------------------------------------END OF THIS TEST-------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------

  "BaseParser" should
    """
      |a empty Map (dictionary) when nothing was passed in the command lines
      |""".stripMargin in {
    val emptyLines:Map[String,String] = new BaseParser(emptyArray,Some("teste")).confs

    emptyLines shouldBe Map.empty
  }

  //--------------------------------------------------------------------------------------------------------------------
  //----------------------------------------END OF THIS TEST-------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------

  "outputPathOption" should
    """
      |return none value because no output was passed in the command line
      |""".stripMargin in {
    val outputTeste:Option[String] = new BaseParser(sourceArgs,Some("teste")).outputPathOption

    outputTeste shouldBe  None

  }
  //--------------------------------------------------------------------------------------------------------------------
  //----------------------------------------END OF THIS TEST-------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------

  "confs" should
    """
      |a Map built from the configurations passed in the command line where the keys are the
      |configurations variables and the values are the respective values passed in the command line.
      |""".stripMargin in{
    val confDict:Map[String,String] = Map("key2"->"value2","key1"->"value1","key3"->"value3")

    val confTest:Map[String,String] = new BaseParser(confsArgs,Some("teste")).confs

    confTest shouldBe confDict
  }
  //--------------------------------------------------------------------------------------------------------------------
  //----------------------------------------END OF THIS TEST-------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------


  "confs" should
    """
      |a Map built from the configurations passed in the command line where the keys are the
      |configurations variables and the values are the respective values passed in the command line. Different type
      |of typographic value
      |""".stripMargin in{
    val confDict:Map[String,String] = Map("key2"->"value=2","key1"->"value1","key3"->"value3")

    val confTest:Map[String,String] = new BaseParser(confsArgs2,Some("teste")).confs

    confTest shouldBe confDict
  }
  //--------------------------------------------------------------------------------------------------------------------
  //----------------------------------------END OF THIS TEST-------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------

  "apply"  should "return the value of the key passed as the functions's argument" in {
    val specifcKey:Option[String] = new BaseParser(allConfs,Some("teste"))("key2")

    specifcKey shouldBe Some("value2")
  }
  //--------------------------------------------------------------------------------------------------------------------
  //----------------------------------------END OF THIS TEST-------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------

  "Instance" should
    """
      |return a error exception
      |""".stripMargin in{
      an [Exception] shouldBe thrownBy(new BaseParser(errorArgs,Some("teste")))
  }
  //--------------------------------------------------------------------------------------------------------------------
  //----------------------------------------END OF THIS TEST-------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------
  "confs" should
    """
      |return a empty Map when no confs were passed
      |""".stripMargin in{
    val noConfs:Array[String] = sourceArgs ++ outputArgs

    new BaseParser(noConfs,Some("teste")).confs shouldBe Map.empty
  }
  //--------------------------------------------------------------------------------------------------------------------
  //----------------------------------------END OF THIS TEST-------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------
  "confs" should
    """
      |return only confs Map when all information is passed
      |""".stripMargin in{
    val confDict:Map[String,String] = Map("key1"->"value1","key2"->"value2","key3"->"value3")

    new BaseParser(allConfs,Some("teste")).confs shouldBe confDict
  }
}
