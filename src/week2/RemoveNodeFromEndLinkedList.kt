package week2

import kotlin.system.measureNanoTime

class RemoveNodeFromEndLinkedList {

    /** Remove the length - n + 1 element from the beginning of the list
     * First calculate the length of the list
     * Then get the item to remove index
     * Iterate until we arrive to the item to remove and relink the pointer to the next next pointer
     * Time complexity: O(N)
     * Space complexity: O(1)
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var current = head
        var length = 0
        while (current != null) {
            length++
            current = current.next
        }
        val dummy = ListNode(0)
        dummy.next = head
        var output: ListNode? = dummy
        var itemToRemove = length - n
        while (itemToRemove > 0) {
            itemToRemove--
            output = output?.next
        }
        output?.next = output?.next?.next
        return dummy.next
    }

    /** Two pointer
     * Maintain two pointer first and second
     * First is n+1 ahead of second
     * When first arrives at the end, it means that second.next it the item to remove
     * We relink the second pointer to the next next second pointer
     * Time complexity: O(N)
     * Space complexity: O(1)
     */
    fun removeNthFromEnd2(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head
        var first: ListNode? = dummy
        var second: ListNode? = dummy

        // Advance the first pointer
        for (i in 0..n) {
            first = first?.next
        }

        while (first != null) {
            first = first.next
            second = second?.next
        }
        second?.next = second?.next?.next
        return dummy.next
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

    val removeNode = RemoveNodeFromEndLinkedList()

    val firstSolutionTime = measureNanoTime { removeNode.removeNthFromEnd(head, 1) }
    val secondSolutionTime = measureNanoTime { removeNode.removeNthFromEnd2(head, 1) }

    println("First Solution execution time: $firstSolutionTime")
    println("Second Solution execution time: $secondSolutionTime")

}