package week4

import java.util.*


class MedianFinder {
    /** initialize your data structure here. */
    val list = mutableListOf<Int>()

    fun addNum(num: Int) {
        list.add(num)
    }

    fun findMedian(): Double {
        if (list.isEmpty()) return 0.0

        list.sort()
        val mid = list.lastIndex / 2
        return if (list.size % 2 == 0) {
            (list[mid] + list[mid + 1]) / 2.0
        } else {
            list[mid].toDouble()
        }
    }
}

class MediaFinder2 {

    private val small: Queue<Long> = PriorityQueue<Long>()
    private val large: Queue<Long> = PriorityQueue<Long>(Collections.reverseOrder())

    fun addNum(num: Int) {
        large.add(num.toLong())
        small.add(large.poll())
        if (large.size < small.size) large.add(small.poll())
    }

    fun findMedian(): Double {
        return if (large.size > small.size) large.peek().toDouble() else (large.peek() + small.peek()) / 2.0
    }
}