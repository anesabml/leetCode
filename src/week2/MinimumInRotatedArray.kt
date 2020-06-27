package week2

class MinimumInRotatedArray {

    /** Binary search
     * Time complexity : O(log n)
     * Space complexity : O(1)
     */

    fun findMin(nums: IntArray): Int {
        if (nums.size == 1) {
            return nums[0]
        }
        var left = 0
        var right = nums.size - 1
        if (nums[right] > nums[0]) {
            return nums[0]
        }
        while (left < right) {
            val mid = left + (right - left) / 2
            if (nums[mid] > nums[left]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        nums.min()
        return nums[left]
    }
}

fun main() {
    val input = intArrayOf(4, 5, 6, 7, 0, 1, 2)

    val minimumInRotatedArray = MinimumInRotatedArray()
    println(minimumInRotatedArray.findMin(input))
}