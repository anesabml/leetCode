import kotlin.math.max

class MaxSubArray {

    /** Brute force (really bad)
     * Time complexity : O(n2)
     * Space complexity : O(1)
     */
    fun maxSubArray(nums: IntArray): Int {
        var max = Int.MIN_VALUE
        for (start in nums.indices) {
            var currentMax = 0
            for (i in start until nums.size) {
                currentMax += nums[i]
                max = max(max, currentMax)
            }
        }
        return max
    }

    /** The Kadane’s Algorithm
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    fun maxSubArray2(nums: IntArray): Int {
        var max = nums[0]
        var currentMax = nums[0]
        for (i in 1 until nums.size) {
            currentMax = max(currentMax + nums[i], nums[i])
            max = max(max, currentMax)
        }
        return max
    }

    /** The Kadane’s Algorithm functional solution
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    fun maxSubArray3(nums: IntArray): Int {
        return nums.fold(Pair(Int.MIN_VALUE, 0)) { (max, currentMax), i ->
            Pair(max(max, max(currentMax + i, i)), max(currentMax + i, i))
        }.first
    }
}

fun main() {
    val input = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
    val maxSubArray = MaxSubArray()

    val firstStartTime = System.currentTimeMillis()
    println(maxSubArray.maxSubArray(input))
    val firstEndTime = System.currentTimeMillis()

    val secondStartTime = System.currentTimeMillis()
    println(maxSubArray.maxSubArray2(input))
    val secondEndTime = System.currentTimeMillis()

    val thirdStartTime = System.currentTimeMillis()
    println(maxSubArray.maxSubArray3(input))
    val thirdEndTime = System.currentTimeMillis()

    println("First Solution execution time: ${firstEndTime - firstStartTime}")
    println("Second Solution execution time: ${secondEndTime - secondStartTime}")
    println("Third Solution execution time: ${thirdEndTime - thirdStartTime}")
}