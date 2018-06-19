## Directed Acyclic Graph Validator ##
*Cycle detection for DAGs*

[![Build Status](https://travis-ci.org/calvinlfer/dag-validator.svg?branch=master)](https://travis-ci.org/calvinlfer/dag-validator)

### Example Usage ###
Here is an example of an valid directed graph which has no cycles
```scala
import com.experiments.calvin.dag.DagValidator
object Dag extends DagValidator 
import Dag._

val graph: Graph[Int] = Graph(
  1 -> List(2, 3),
  2 -> List(3),
  3 -> Nil
)
detectCycle(graph) 
// false
```

Here's an example of a graph with cycles
```scala
import com.experiments.calvin.dag.DagValidator
object Dag extends DagValidator 
import Dag._

val graphWithCycle: Graph[String] = Map(
  "A" -> List("B"),
  "B" -> List("C"),
  "C" -> List("E"),
  "E" -> List("F", "D"),
  "D" -> List("B"),
  "F" -> Nil
)

detectCycle(graphWithCycle) 
// true
```
