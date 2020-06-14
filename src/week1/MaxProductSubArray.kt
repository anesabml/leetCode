package week1

import kotlin.math.max
import kotlin.system.measureNanoTime

class MaxProductSubArray {

    /** Brute force (really bad)
     * Time complexity : O(n2)
     * Space complexity : O(1)
     */
    fun maxProductSubArray(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var maxProduct = Int.MIN_VALUE
        for (start in nums.indices) {
            var currentMaxProduct = 1
            for (i in start until nums.size) {
                currentMaxProduct *= nums[i]
                maxProduct = max(maxProduct, currentMaxProduct)
            }
        }
        return maxProduct
    }

    /** Keep track of the max product (positive), and keep track of the min product (negative)
     * because we may find another negative number and the product of negative * negative = positive
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    fun maxProductSubArray2(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var currentMaxProduct = nums[0]
        var currentMinProduct = nums[0]
        var maxProduct = nums[0]
        for (i in 1 until nums.size) {
            val temp = currentMaxProduct
            currentMaxProduct = maxOf(currentMaxProduct * nums[i], currentMinProduct * nums[i], nums[i])
            currentMinProduct = minOf(temp * nums[i], currentMinProduct * nums[i], nums[i])
            maxProduct = max(maxProduct, currentMaxProduct)
        }
        return maxProduct
    }

    /** Keep track of the product from the both sides of the array and return the max
     * This works because we just calculate the product as we go
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    fun maxProductSubArray3(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var max = nums[0]
        var left = 1
        var right = 1
        for (i in nums.indices) {
            left = (if (left == 0) 1 else left) * nums[i]
            right = (if (right == 0) 1 else right) * nums[nums.size - 1 - i]
            max = maxOf(max, left, right)
        }
        return max
    }
}

fun main() {
    val input = intArrayOf(-2, 3, -4)

    val maxProductSubArray = MaxProductSubArray()

    val firstSolutionTime = measureNanoTime { println(maxProductSubArray.maxProductSubArray(input)) }
    val secondSolutionTime = measureNanoTime { println(maxProductSubArray.maxProductSubArray2(input)) }
    val thirdSolutionTime = measureNanoTime { println(maxProductSubArray.maxProductSubArray3(input)) }

    println("First Solution execution time: $firstSolutionTime")
    println("Second Solution execution time: $secondSolutionTime")
    println("Third Solution execution time: $thirdSolutionTime")
}