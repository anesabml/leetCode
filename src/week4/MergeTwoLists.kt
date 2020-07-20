package week4

import week2.ListNode

class MergeTwoLists {

    /**
     * Time complexity : O(n)
     * Space complexity: O(1)
     */
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        val head = ListNode(0)
        var h = head
        var _l1 = l1
        var _l2 = l2
        while (_l1 != null && _l2 != null) {
            if (_l1.`val` < _l2.`val`) {
                h.next = _l1
                h = h.next!!
                _l1 = _l1.next
            } else {
                h.next = _l2
                h = h.next!!
                _l2 = _l2.next
            }
        }
        if (_l1 == null) {
            h.next = _l2
        } else {
            h.next = _l1
        }
        return head.next
    }
}