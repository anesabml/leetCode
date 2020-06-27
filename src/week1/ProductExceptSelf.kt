package week1

import kotlin.system.measureNanoTime

class ProductExceptSelf {

    /** Brute force
     * Iterate over the array and calculate the multiply result
     * Time complexity : O(n2)
     * Space complexity : O(n)
     */
    fun productExceptSelf(nums: IntArray): IntArray {
        val output = IntArray(nums.size) { 1 }
        nums.forEachIndexed { i1, num1 ->
            nums.forEachIndexed { i2, num2 ->
                if (i1 != i2) output[i1] *= num2
            }
        }
        return output
    }

    /** Better solution
     * If we observe the array we see that a multiple of nums = (left side) times (right side).
     * So we Iterate from start to eng and calculate the result of multiplying the left side of i.
     * After that we iterate from end to start and calculate the result of multiplying the right side of i.
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun productExceptSelf2(nums: IntArray): IntArray {
        val output = IntArray(nums.size)
        val left = IntArray(nums.size) { 1 }
        val right = IntArray(nums.size) { 1 }

        for (i in 1 until nums.size) {
            left[i] = left[i - 1] * nums[i - 1]
        }
        for (i in nums.size - 2 downTo 0) {
            right[i] = right[i + 1] * nums[i + 1]
        }

        output.forEachIndexed { index, i -> output[index] = left[index] * right[index] }

        return output
    }

    /** Better solution
     * If we observe the array we see that a multiple of nums = (left side) times (right side).
     * So we Iterate from start to eng and calculate the result of multiplying the left side of i.
     * After that we iterate from end to start and calculate the result of multiplying the right side of i.
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun productExceptSelf3(nums: IntArray): IntArray {
        val output = IntArray(nums.size) { 1 }
        var temp = 1
        for (i in nums.indices) {
            output[i] *= temp
            temp *= nums[i]
        }
        temp = 1
        for (i in nums.size - 1 downTo 0) {
            output[i] *= temp
            temp *= nums[i]
        }
        return output
    }
}


fun main() {
    val input = intArrayOf(2, 7, 11, 15)
    val productExceptSelf = ProductExceptSelf()

    val firstSolutionTime = measureNanoTime { println(productExceptSelf.productExceptSelf(input).contentToString()) }
    val secondSolutionTime = measureNanoTime { println(productExceptSelf.productExceptSelf2(input).contentToString()) }
    val thirdSolutionTime = measureNanoTime { println(productExceptSelf.productExceptSelf3(input).contentToString()) }

    println("First Solution execution time: $firstSolutionTime")
    println("Second Solution execution time: $secondSolutionTime")
    println("Third Solution execution time: $thirdSolutionTime")
}