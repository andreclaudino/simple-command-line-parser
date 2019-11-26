package com.b2wdigital.iafront.clparser

import scala.annotation.tailrec
import scala.collection.immutable

class BaseParser(args:Array[String], name:Option[String])  {

  private val parsedValues = parse(args)

  def sourcePathOption:Option[String] = parsedValues.get("source")
  def outputPathOption:Option[String] = parsedValues.get("output")

  def confs:Map[String, String] = parsedValues-("source")-("output")

  def apply(key:String):Option[String] = parsedValues.get(key)

  @tailrec
  private def parse(arguments:Seq[String], parsed:Map[String, String] = Map(),
            parsedPositional:Seq[Int]=Seq()):Map[String, String] = {
    val newParsed =
      arguments.headOption match {
      case Some("-c") | Some("--conf") => parseConf(arguments.tail.head)
      case Some("-s") | Some("--source") =>
        Map("source" -> arguments.tail.head)
      case Some("-o") | Some("--output") => Map("output" -> arguments.tail.head)
      case Some(value) => parseAutonomous(value, arguments)
      case None => parsed
    }
    if(arguments.tail.length > 1){
      parse(arguments.tail.tail, newParsed ++ parsed)
    }
    else {
     parsed ++ newParsed
    }
  }

  private def parseConf(value:String):Map[String, String] = ???
  private def parseAutonomous(value:String, arguments:Seq[String]):Map[String, String] = ???
}
