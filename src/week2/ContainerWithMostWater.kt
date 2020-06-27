package week2

import kotlin.math.min
import kotlin.system.measureNanoTime

class ContainerWithMostWater {

    /** Brute force
     * Time complexity : O(n2)
     * Space complexity : O(1)
     */
    fun maxArea(height: IntArray): Int {
        if (height.isEmpty()) return 0

        var maxArea = 0
        for (i in height.indices) {
            for (j in i + 1 until height.size) {
                val currentArea = min(height[i], height[j]) * (j - i)
                if (currentArea > maxArea) {
                    maxArea = currentArea
                }
            }
        }
        return maxArea
    }

    /** Using two pointers
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    fun maxArea2(height: IntArray): Int {
        if (height.isEmpty()) return 0

        var maxArea = 0
        var left = 0
        var right = height.size - 1
        while (left < right) {
            val currentArea = min(height[left], height[right]) * (right - left)
            if (currentArea > maxArea) {
                maxArea = currentArea
            }
            if (height[left] < height[right]) {
                left++
            } else {
                right--
            }
        }
        return maxArea
    }
}

fun main() {
    val input = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)

    val containerWithMostWater = ContainerWithMostWater()

    val firstSolutionTime = measureNanoTime { println(containerWithMostWater.maxArea(input)) }
    val secondSolutionTime = measureNanoTime { println(containerWithMostWater.maxArea2(input)) }

    println("First Solution execution time: $firstSolutionTime")
    println("Second Solution execution time: $secondSolutionTime")
}