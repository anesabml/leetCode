package week4

import kotlin.math.max

class LongestConsecutive {

    /** Brute force
     * Time complexity : O(n3)
     * Space complexity : O(1)
     */
    fun longestConsecutive(nums: IntArray): Int {
        var maxCount = 0
        for (num in nums) {
            var currentNumber = num
            var currentCount = 1
            while (contains(nums, currentNumber + 1)) {
                currentNumber += 1
                currentCount += 1
            }
            maxCount = max(maxCount, currentCount)
        }
        return maxCount
    }

    private fun contains(nums: IntArray, num: Int): Boolean {
        for (i in nums.indices) {
            if (num == nums[i]) {
                return true
            }
        }
        return false
    }

    /** HashSet
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun longestConsecutive2(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        val numSet = nums.toHashSet()
        var maxCount = 0
        for (num in nums) {
            var currentNumber = num
            var currentCount = 1
            while (numSet.contains(currentNumber + 1)) {
                currentNumber += 1
                currentCount += 1
            }
            maxCount = maxOf(maxCount, currentCount)
        }
        return maxCount
    }

    /** Sort
     * Time complexity : O(n log n)
     * Space complexity : O(1)
     */
    fun longestConsecutive3(nums: IntArray): Int {
        if (nums.isEmpty()) return 0

        nums.sort()
        var maxCount = 0
        var currentCount = 1

        for (i in 1 until nums.size) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == nums[i - 1] + 1) {
                    currentCount += 1
                } else {
                    maxCount = maxOf(maxCount, currentCount)
                    currentCount = 1
                }
            }
        }
        return maxOf(maxCount, currentCount)
    }
}