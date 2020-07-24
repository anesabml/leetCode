package week4

import java.lang.Integer.max
import java.lang.Integer.min
import java.util.*

class InsertInterval {

    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty())
            return arrayOf(newInterval)
        val output = Stack<IntArray>()
        var previousInterval = newInterval
        for (currentInterval in intervals) {
            when {
                previousInterval[1] < currentInterval[0] -> {
                    output.push(previousInterval)
                    previousInterval = currentInterval
                }
                currentInterval[1] < previousInterval[0] -> {
                    output.push(currentInterval)
                }
                else -> {
                    previousInterval[0] = min(currentInterval[0], previousInterval[0])
                    previousInterval[1] = max(currentInterval[1], previousInterval[1])
                }
            }
        }
        output.push(previousInterval)
        return output.toTypedArray()
    }
}