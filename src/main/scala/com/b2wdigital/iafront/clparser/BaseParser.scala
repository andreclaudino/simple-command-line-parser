package com.b2wdigital.iafront.clparser

import org.clapper.argot.{ArgotParser, SingleValueOption}

class BaseParser(args:Array[String], name:Option[String])  {

  val parser = new ArgotParser(name.getOrElse(""))

  // Source Path
  private val sourcePathOpt =
    stringParser("source", "s", "sourcePath", help="Spark input datasource")
  def sourcePathOption:Option[String] = sourcePathOpt.value

  // Output Path
  private val outputPathOpt =
    stringParser("output", "o", "outputPath", help="Spark output datasource")
  def outputPathOption:Option[String] = outputPathOpt.value

  private val confsParse =
    parser.multiOption[Map[String, String]](List("c", "conf"), "configuration", "Add a custom configuration flag as key=value"){
      (sValue, opt) => {
        val Seq(k, v) = sValue.split("=").toSeq
        Map[String, String](k -> v)
      }
    }
  def confs:Map[String,String] = confsParse.value.reduceOption((u, v) => u ++ v).getOrElse(Map[String, String]())

  // Helpers
  private def stringParser(name:String, short:String = "", valueName:String = "", default:String="", help:String=""):SingleValueOption[String] ={
    val names = if(short.isEmpty) List(name) else List(short, name)
    parser.option[String](names, valueName, help){
      (sValue, _) => if(sValue != null) sValue else default
    }
  }

  parser.parse(args)
}
