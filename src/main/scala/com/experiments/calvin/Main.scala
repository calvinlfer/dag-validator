package com.experiments.calvin

import com.experiments.calvin.dag.DagValidator

object Main extends App with DagValidator {
  val graphWithCycle: Graph[String] = Map(
    "A" -> List("B"),
    "B" -> List("C"),
    "C" -> List("E"),
    "E" -> List("F", "D"),
    "D" -> List("B"),
    "F" -> Nil
  )

  println {
    detectCycle(graphWithCycle)
  }
}
