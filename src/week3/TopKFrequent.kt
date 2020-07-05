package week3

import java.util.*
import kotlin.system.measureNanoTime


class TopKFrequent {

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        if (k == nums.size) return nums

        val map = hashMapOf<Int, Int>()
        nums.forEach { n ->
            map[n] = map.getOrDefault(n, 0) + 1

        }

        return map.toList().sortedByDescending { (_, value) -> value }.toMap().keys.toList().subList(0, k).toIntArray()
    }

    /** PriorityQueue - maxHeap
     * Time complexity: O(n log k)
     * Space complexity: O(n + k)*/
    fun topKFrequent2(nums: IntArray, k: Int): IntArray {
        if (k == nums.size) return nums

        val map = hashMapOf<Int, Int>()
        nums.forEach { n ->
            map[n] = map.getOrDefault(n, 0) + 1
        }

        val priorityQueue = PriorityQueue<Int>(compareByDescending { map[it] })
        map.forEach {
            priorityQueue.add(it.key)
        }
        val output = IntArray(k)
        for (i in 0 until k) {
            output[i] = priorityQueue.poll()
        }
        return output
    }

    /** Bucket sort
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    fun topKFrequent3(nums: IntArray, k: Int): IntArray {
        if (k == nums.size) return nums

        val map = hashMapOf<Int, Int>()
        nums.forEach { n ->
            map[n] = map.getOrDefault(n, 0) + 1

        }
        val buckets = Array<MutableList<Int>>(nums.size + 1) { mutableListOf() }
        map.forEach { (key, value) ->
            buckets[value].add(key)
        }

        val output = mutableListOf<Int>()
        var i = buckets.size - 1
        var j = k
        while (i > 0 && j > 0) {
            if (buckets[i].isNotEmpty()) {
                output.addAll(buckets[i])
                j -= buckets[i].size
            }
            i--
        }
        return output.toIntArray()
    }
}

fun main() {
    val nums = intArrayOf(1, 1, 1, 2, 2, 3)
    val k = 2

    val topKFrequent = TopKFrequent()

    val firstSolutionTime = measureNanoTime { println(topKFrequent.topKFrequent(nums, k).contentToString()) }
    val secondSolutionTime = measureNanoTime { println(topKFrequent.topKFrequent2(nums, k).contentToString()) }
    val thirdSolutionTime = measureNanoTime { println(topKFrequent.topKFrequent3(nums, k).contentToString()) }

    println("First Solution execution time: $firstSolutionTime")
    println("Second Solution execution time: $secondSolutionTime")
    println("Third Solution execution time: $thirdSolutionTime")
}