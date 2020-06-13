package week2

import java.util.*


class LinkedListCycle {


    /** Hash Table
     * To detect if a list is cyclic, we can check whether a node had been visited before
     * Time complexity : O(n)
     * Space complexity: O(n)
     * */
    fun hasCycle(head: ListNode?): Boolean {
        var currentHead = head
        val nodesSeen: MutableSet<ListNode> = HashSet()
        while (currentHead != null) {
            if (nodesSeen.contains(currentHead)) {
                return true
            } else {
                nodesSeen.add(currentHead)
            }
            currentHead = currentHead.next
        }
        return false
    }

    /** Two Pointers
     * Imagine two runners running on a track at different speed.
     * If there's a cycle eventually the fast pointer will be equal to the slow pointer.
     * Time complexity : O(n + k)
     * Space complexity: O(1)
     */
    fun hasCycle2(head: ListNode?): Boolean {
        if (head?.next == null) {
            return false
        }
        var slow = head
        var fast = head.next
        while (slow != fast) {
            if (fast?.next == null) {
                return false
            }
            slow = slow!!.next
            fast = fast.next!!.next
        }
        return true
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

    val reverseLinkedList = LinkedListCycle()

    val firstStartTime = System.currentTimeMillis()
    reverseLinkedList.hasCycle(head)
    val firstEndTime = System.currentTimeMillis()

    val secondStartTime = System.currentTimeMillis()
    reverseLinkedList.hasCycle2(head)
    val secondEndTime = System.currentTimeMillis()

    println("First Solution execution time: ${firstEndTime - firstStartTime}")
    println("Second Solution execution time: ${secondEndTime - secondStartTime}")

}