package com.b2wdigital.iafront.clparser

import scala.annotation.tailrec

class BaseParser(args:Array[String], name:Option[String])  {

  private val parsedValues = parse(args)

  def sourcePathOption:Option[String] = parsedValues.get("source")

  def outputPathOption:Option[String] = parsedValues.get("output")

  def confs:Map[String, String] = parsedValues-("source")-("output")

  def apply(key:String):Option[String] = parsedValues.get(key)

  @tailrec
  private def parse(arguments:Seq[String], parsed:Map[String, String] = Map(),
            parsedPositional:Seq[Int]=Seq()):Map[String, String] = {
    if(arguments.isEmpty){
      Map.empty
    }
    else{
    val newParsed: Map[String, String] =
      arguments.headOption match {
        case Some("-c") | Some("--conf") => parseConf(arguments.tail.head)
        case Some("-s") | Some("--source") => Map("source" -> arguments.tail.head)
        case Some("-o") | Some("--output") => Map("output" -> arguments.tail.head)
        case Some(value) => parseAutonomous(value)
          throw new java.lang.Exception(s"Command Line Error verify parameter $value" )
        case None => parsed
      }
    if (arguments.tail.length > 1) {
      parse(arguments.tail.tail, newParsed ++ parsed)
    }
    else {
      parsed ++ newParsed
    }
     }
  }

  def parseConf(configuration:String):Map[String, String] = {
     val confKeyValue:Array[String] = configuration.split("=",2)
     Map[String,String](confKeyValue{0} -> confKeyValue{1})
  }
   def parseAutonomous(value:String): Unit ={
     println(s"Greetings user! You have a problem in the command line $value")
   }
}
