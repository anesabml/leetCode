package week4

import week2.ListNode
import java.util.*


class MergeKList {

    /** Brute force
     * Adding all the number to a list and sorting that list
     * Then creating a new LinkedList from the sorted list
     * Time complexity : O(n log n)
     * Space complexity: O(n)
     */
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null

        val mutableList = mutableListOf<Int>()

        for (node in lists) {
            var _node = node
            while (_node != null) {
                mutableList.add(_node.`val`)
                _node = _node.next
            }
        }

        val head = ListNode(0)
        var h = head
        for (number in mutableList.sorted()) {
            val node = ListNode(number)
            h.next = node
            h = h.next!!
        }
        return head.next
    }

    /** Compare one by one
     * Comparing all the numeber to each other and adding the smallest number
     * in each iteration
     * Time complexity : O(kn)
     * Space complexity: O(n)
     */
    fun mergeKLists2(lists: Array<ListNode?>): ListNode? {
        var minIndex = 0
        val head = ListNode(0)
        var h = head
        while (true) {
            var isBreak = true
            var min = Int.MAX_VALUE
            for (i in lists.indices) {
                if (lists[i] != null) {
                    if (lists[i]!!.`val` < min) {
                        minIndex = i
                        min = lists[i]!!.`val`
                    }
                    isBreak = false
                }
            }
            if (isBreak) {
                break
            }
            val a = ListNode(lists[minIndex]!!.`val`)
            h.next = a
            h = h.next!!
            lists[minIndex] = lists[minIndex]!!.next
        }
        return head.next
    }

    /** Priority Queue
     * Time complexity : O(n log k)
     * Space complexity: O(n)
     */
    fun mergeKLists3(lists: Array<ListNode?>): ListNode? {
        val q: Queue<ListNode> = PriorityQueue(compareBy { it.`val` })
        for (list in lists) {
            list?.let {
                q.add(list)
            }
        }
        val head = ListNode(0)
        var h = head
        while (!q.isEmpty()) {
            h.next = q.poll()
            h = h.next!!
            val next = h.next
            if (next != null) {
                q.add(next)
            }
        }
        return head.next
    }
}