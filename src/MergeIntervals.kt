import java.lang.Integer.max
import java.util.*

class MergeIntervals {

    /** Using Stack
     * Sort the intervals based on increasing order of first element.
     * Push the first interval on to a stack.
     * For each interval do the following
     * If the current interval does not overlap with the previous interval, push it.
     * Else if update the previous interval with the biggest ending of current and previous interval.
     * Time complexity : O(n log n)
     * Space complexity : O(1)
     */
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if (intervals.size <= 1)
            return intervals
        intervals.sortBy { it.first() }
        val output = Stack<IntArray>()
        var previousInterval = intervals[0]
        output.push(previousInterval)
        for (i in 1 until intervals.size) {
            val currentInterval = intervals[i]
            if (previousInterval[1] < currentInterval[0]) {
                previousInterval = currentInterval
                output.push(previousInterval)
            } else {
                previousInterval[1] = max(currentInterval[1], previousInterval[1])
            }
        }
        return output.toTypedArray()
    }

    /** Same as the first solution but using LinkedList
     * Time complexity : O(n log n)
     * Space complexity : O(1)
     */
    fun merge2(intervals: Array<IntArray>): Array<IntArray> {
        if (intervals.size <= 1)
            return intervals
        intervals.sortBy { it.first() }
        val output = mutableListOf<IntArray>()
        var previousInterval = intervals[0]
        output.add(previousInterval)
        for (i in 1 until intervals.size) {
            val currentInterval = intervals[i]
            if (previousInterval[1] < currentInterval[0]) {
                previousInterval = currentInterval
                output.add(previousInterval)
            } else {
                previousInterval[1] = max(currentInterval[1], previousInterval[1])
            }
        }
        return output.toTypedArray()
    }
}

fun main() {
    val input = arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))

    val mergeIntervals = MergeIntervals()

    val firstStartTime = System.currentTimeMillis()
    mergeIntervals.merge(input).forEach {
        print(it.contentToString())
    }
    val firstEndTime = System.currentTimeMillis()

    println()

    val secondStartTime = System.currentTimeMillis()
    mergeIntervals.merge2(input).forEach {
        print(it.contentToString())
    }
    val secondEndTime = System.currentTimeMillis()

    println("First Solution execution time: ${firstEndTime - firstStartTime}")
    println("Second Solution execution time: ${secondEndTime - secondStartTime}")
}