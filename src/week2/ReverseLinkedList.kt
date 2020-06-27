package week2

import kotlin.system.measureNanoTime

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class ReverseLinkedList {

    /** Iterative solution
     * Iterate over the LinkedList and add new elements at start
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun reverseList(head: ListNode?): ListNode? {
        if (head == null) return null

        var result: ListNode? = null
        var currentNode = head
        while (currentNode != null) {
            val node = ListNode(currentNode.`val`)
            node.next = result
            result = node
            currentNode = currentNode.next
        }
        return result
    }

    /** Recursive solution
     * Iterate over the LinkedList and add new elements at start
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun reverseList2(head: ListNode?): ListNode? {
        return reverseListRecursive(head, null)
    }

    private tailrec fun reverseListRecursive(head: ListNode?, result: ListNode?): ListNode? {
        if (head == null)
            return result
        val node = ListNode(head.`val`)
        node.next = result
        return reverseListRecursive(head.next, node)
    }
}

fun main() {
    val head = ListNode(1)
    var current = head
    for (i in 2..5) {
        val node = ListNode(i)
        current.next = node
        current = node
    }

    val reverseLinkedList = ReverseLinkedList()

    val firstSolutionTime = measureNanoTime { reverseLinkedList.reverseList(head) }
    val secondSolutionTime = measureNanoTime { reverseLinkedList.reverseList2(head) }

    println("First Solution execution time: $firstSolutionTime")
    println("Second Solution execution time: $secondSolutionTime")

}