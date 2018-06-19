package com.experiments.calvin.dag

import scala.annotation.tailrec

// Inspiration: https://www.educative.io/page/11000001/60001
trait DagValidator {
  type Graph[A] = Map[A, List[A]]
  object Graph {
    def empty[A]: Graph[A] = Map.empty[A, List[A]]

    def apply[A](relationships: (A, List[A])*): Graph[A] = relationships.toMap
  }

  /**
    * Conduct a slightly modified depth first search whilst keeping track of nodes that are being explored/are fully
    * explored/have yet to be explored
    * @param graph is an adjacency list representation of a graph
    * @tparam A
    * @return true if a cycle is detected
    */
  def detectCycle[A](graph: Graph[A]): Boolean = {
    @tailrec
    def process(unvisited: List[A], visiting: List[A], visited: Set[A]): Boolean = {
      // no cycles
      if (unvisited.isEmpty && visiting.isEmpty) false
      else if (unvisited.nonEmpty && visiting.isEmpty) {
        val node = unvisited.head
        process(unvisited.tail, node :: visiting, visited)
      } else {
        // explore the nodes currently being visited
        val node = visiting.head
        val neighbours =  graph(node)
        val neighboursNotYetVisited = neighbours.toSet &~ visited
        if (neighboursNotYetVisited.isEmpty) {
          // current node has no unexplored neighbours so it's fully explored
          process(unvisited, visiting.tail, visited + node)
        } else {
          // node has neighbours yet to be visited
          val firstNeighbour = neighboursNotYetVisited.head
          // if we are trying to add a neighbour that is currently in the process of traversal we have a cycle
          if (visiting contains firstNeighbour) true
          else process(unvisited, firstNeighbour :: visiting, visited)
        }
      }
    }
    process(graph.keys.toList, Nil, Set.empty)
  }
}
