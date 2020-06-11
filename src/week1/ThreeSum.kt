package week1

import java.util.*

class ThreeSum {

    /**
     * Fix the first number and look for the other two by using the two sum algorithm (map)
     * Time complexity : O(n2)
     * Space complexity : O(n)
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            map[nums[i]] = i
        }
        val result = mutableSetOf<List<Int>>()
        for (i in nums.indices) {
            val sum = 0 - nums[i]
            for (j in i + 1 until nums.size) {
                val y = sum - nums[j]
                if (map.containsKey(y) && map[y] != j && map[y] != i) {
                    result.add(listOf(nums[i], nums[j], y).sorted())
                }
            }
        }
        return result.toList()
    }

    /**
     * Fix the first number and look for the other two by using the two sum algorithm
     * Time complexity : O(n2)
     * Space complexity : O(1)
     */
    fun threeSum2(nums: IntArray): List<List<Int>> {
        nums.sort()
        val result = mutableSetOf<List<Int>>()
        for (i in 0 until nums.size - 2) {
            var low = i + 1
            var high = nums.size - 1
            while (low < high) {
                val sum = nums[i] + nums[low] + nums[high]
                when {
                    sum == 0 -> {
                        result.add(listOf(nums[i], nums[low], nums[high]))
                        low++
                        high--
                    }
                    sum < 0 -> {
                        low++
                    }
                    else -> {
                        high--
                    }
                }
            }
        }
        return result.toList()
    }
}

fun main() {
    val threeSum = ThreeSum()
    val input = intArrayOf(-1, 0, 1, 2, -1, -4)

    val firstStartTime = System.currentTimeMillis()
    println(threeSum.threeSum(input))
    val firstEndTime = System.currentTimeMillis()

    val secondStartTime = System.currentTimeMillis()
    println(threeSum.threeSum2(input))
    val secondEndTime = System.currentTimeMillis()

    println("First Solution execution time: ${firstEndTime - firstStartTime}")
    println("Second Solution execution time: ${secondEndTime - secondStartTime}")
}