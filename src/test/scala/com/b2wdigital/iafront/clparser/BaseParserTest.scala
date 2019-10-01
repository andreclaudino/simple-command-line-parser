package com.b2wdigital.iafront.clparser

import org.scalatest.{FlatSpec, Matchers}

class BaseParserTest extends FlatSpec with Matchers {

  it should "match generic config version" in {
    val commandLine = "-c version=A".split(" ")
    val parsed = new BaseParser(commandLine, Some("test"))

    parsed.confs("version") shouldBe "A"
  }

}
