# Simple Command Line Parser

A simple library to parse command lines with batteries included

## How it works

This library was developed in B2W to help creating ETL jobs in apache spark, but can be used in any kind Scala/JVM application.

AS an ELT tool, there is already a `source` and an `output` command lines. For example, for a source file names `sourceFile.csv` and an output file names `outputFile.csv` you may just:

```
<application> --source souceFile.csv --output outputFile.csv
``` 

or, using shortcuts

```bash
<application> -s souceFile.csv -o outputFile.csv
```

You may already use any (`key` -> `value`) with the `--conf` parameter (or it alias `-c`)

```bash
<application> -c filename=filename.csv -c max_node_count=3 -c "text=A value with whitespaces"
```

## Using in code

Code usage is simple, you just need to create an instance of `BaseParser`

```scala

import com.b2wdigital.iafront.clparser.BaseParser

object Application extends App {
  val appName = "Application name displayed"
  val parameters = new BaseParser(args, appName)
  
  
  println(parameters.sourcePathOption) // An option with --source or -s value
  println(parameters.outputPathOption) // An option with --output or -o value
  
  println(parameters.confs) // A dictionary with all --conf or -c conf key=values where key is in dictionary keys and values in dictionary values.

}
```

## Instalation

If you are build it, you should publish locally before use in any project. In `simple-command-line-parser` folder run:

```bash
sbt publishLocal
```

Then, import in your project `build.sbt` file

```scala
libraryDependencies ++= Seq(
  "com.b2wdigital.iafront" %% "simple-command-line-parser" % "1.1-SNAPSHOT"
)
```

## TODO:

The project is based on Argot, but it's abandoned. To keep it maintainable, next step is migrate to another parser tool. 
