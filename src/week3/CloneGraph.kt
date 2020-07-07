package week3

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList()
}

class CloneGraph {
    /** DFS Recursive
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null
        val visited = HashMap<Int, Node>()
        return copyNode(node, visited)
    }

    fun copyNode(node: Node?, visited: HashMap<Int, Node>): Node? {
        if (node == null) {
            return null
        }

        if (visited.contains(node.`val`)) {
            return visited[node.`val`]
        }

        val newNode = Node(node.`val`)
        newNode.neighbors = ArrayList()
        visited[newNode.`val`] = newNode
        for (n in node.neighbors) {
            newNode.neighbors.add(copyNode(n, visited))
        }
        return newNode
    }

    /** DFS Iterative
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    fun cloneGraph2(node: Node?): Node? {
        if (node == null) return null
        val copyNode = Node(node.`val`)
        val map = HashMap<Node, Node>()
        map[node] = copyNode
        val stack: Stack<Node> = Stack()
        stack.push(node)
        while (stack.isNotEmpty()) {
            val currentNode = stack.pop()
            for (neigh in currentNode.neighbors) {
                if (map.containsKey(neigh)) {
                    map[currentNode]?.neighbors?.add(map[neigh])
                } else if (neigh != null) {
                    val neighCopy = Node(neigh.`val`)
                    map[neigh] = neighCopy
                    map[currentNode]?.neighbors?.add(neighCopy)
                    stack.push(neigh)
                }
            }
        }
        return copyNode
    }

    /** BFS Iterative
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    fun cloneGraph3(node: Node?): Node? {
        if (node == null) return null
        val map = HashMap<Node, Node>()
        val queue: Queue<Node> = LinkedList<Node>()
        queue.add(node)
        val copy = Node(node.`val`)
        map[node] = copy
        while (!queue.isEmpty()) {
            val current = queue.poll()
            for (neigh in current.neighbors) {
                if (map.containsKey(neigh)) {
                    map[current]?.neighbors?.add(map[neigh])
                } else if (neigh != null) {
                    val neighCopy = Node(neigh.`val`)
                    map[neigh] = neighCopy
                    map[current]?.neighbors?.add(neighCopy)
                    queue.add(neigh)
                }
            }
        }
        return copy
    }
}