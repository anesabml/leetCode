package week1

import java.util.*
import kotlin.system.measureNanoTime

class TwoSum {

    /** Brute force
     * Loop through each element x and find if there is a value that equals to target - x
     * Time complexity : O(n2)
     * Space complexity : O(1)
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[j] == target - nums[i]) {
                    return intArrayOf(i, j)
                }
            }
        }
        throw IllegalArgumentException("No solution")
    }

    /** Using a HashTable
     * Insert all the nums values and look for y
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun twoSum2(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            map[nums[i]] = i
        }
        for (i in nums.indices) {
            val y = target - nums[i]
            if (map.containsKey(y) && map[y] != i) {
                return intArrayOf(i, map[y]!!)
            }
        }
        throw IllegalArgumentException("No solution")
    }

    /** Using a HashTable
     * Insert all the nums values and look for y
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun twoSum3(nums: IntArray, target: Int): IntArray {
        val map = hashMapOf<Int, Int>()
        for (i in nums.indices) {
            val y = target - nums[i]
            map[y]?.let {
                return intArrayOf(i, it).apply { sort() }
            }
            map[nums[i]] = i
        }
        throw IllegalArgumentException("No solution")
    }
}

fun main() {
    val twoSum = TwoSum()
    val input = intArrayOf(2, 7, 11, 15)
    val target = 9

    val firstSolutionTime = measureNanoTime { println(twoSum.twoSum(input, target).contentToString()) }
    val secondSolutionTime = measureNanoTime { println(twoSum.twoSum2(input, target).contentToString()) }
    val thirdSolutionTime = measureNanoTime { println(twoSum.twoSum3(input, target).contentToString()) }

    println("First Solution execution time: $firstSolutionTime")
    println("Second Solution execution time: $secondSolutionTime")
    println("Third Solution execution time: $thirdSolutionTime")

}