package week1

class SearchRotatedArray {

    /**
     * Calculate the midpoint and test if the target in the left or the right of the midpoint
     * and update left and right as required
     * Time complexity : O(log n)
     * Space complexity : O(1)
     */
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = (left + right) / 2
            if (nums[mid] == target) {
                return mid
            } else if (nums[mid] >= nums[left]) {
                if (nums[mid] > target && nums[left] <= target) {
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            } else {
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
        }
        return -1
    }
}

fun main() {
    val input = intArrayOf(4, 5, 6, 7, 0, 1, 2)

    val searchRotatedArray = SearchRotatedArray()

    println(searchRotatedArray.search(input, 0))
}