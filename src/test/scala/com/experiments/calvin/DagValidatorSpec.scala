package com.experiments.calvin

import com.experiments.calvin.dag.DagValidator
import org.scalatest.{FunSuite, MustMatchers}

class DagValidatorSpec extends FunSuite with DagValidator with MustMatchers {
  test("An empty graph should have no cycles") {
    val graph: Graph[Int] = Graph.empty
    detectCycle(graph) mustBe false
  }

  test("A valid Directed Acyclic Graph (DAG) #1 must have no cycles") {
    /**
      *          -->- 2 -->
      *         /          \
      *    1 ---->-------------- 3
      */
    val graph: Graph[Int] = Graph(
      1 -> List(2, 3),
      2 -> List(3),
      3 -> Nil
    )
    detectCycle(graph) mustBe false
  }

  test("An invalid DAG #1 will contain cycles and will be detected") {
    // cycle: A -> B -> C -> E -> D -> B
    val graphWithCycle: Graph[String] = Map(
      "A" -> List("B"),
      "B" -> List("C"),
      "C" -> List("E"),
      "E" -> List("F", "D"),
      "D" -> List("B"),
      "F" -> Nil
    )

    detectCycle(graphWithCycle) mustBe true
  }

  test("An invalid DAG #2 will contain cycles and will be detected") {
    // cycle: 4 -> 5 -> 6 -> 4
    val graphWithCycle: Graph[String] = Map(
      "4" -> List("1", "5"),
      "1" -> List("2"),
      "2" -> Nil,
      "5" -> List("6"),
      "6" -> List("4")
    )
    detectCycle(graphWithCycle) mustBe true
  }
}
