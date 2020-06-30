package week3

class NonOverlappingIntervals {

    /**
     * Time complexity : O(n logn)
     * Space complexity : O(1)
     */
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        if (intervals.size <= 1) return 0

        intervals.sortBy { it[1] }
        var count = 0
        var end = intervals[0][1]
        for (i in 1 until intervals.size) {
            if (intervals[i][0] < end) {
                count += 1
            } else {
                end = intervals[i][1]
            }
        }
        return count
    }
}

fun main() {
    val input = arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))

    val nonOverlappingIntervals = NonOverlappingIntervals()

    println(nonOverlappingIntervals.eraseOverlapIntervals(input))
}