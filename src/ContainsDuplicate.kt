class ContainsDuplicate {

    /** Brute force Linear search
     * Time complexity O(n2)
     * Space complexity O(1)
     */
    fun containsDuplicate(nums: IntArray): Boolean {
        for (i in nums.indices) {
            for (j in 0 until i) {
                if (nums[j] == nums[i]) return true
            }
        }
        return false
    }

    /** Using sorting algorithms
     * Time complexity O(nlog(n))
     * Space complexity O(1)
     * It's a good practise to keep the original input intact and work with a copy
     */
    fun containsDuplicate2(nums: IntArray): Boolean {
        val sorted = nums.sortedArray()
        for (i in 0 until sorted.size - 1) {
            if (sorted[i] == sorted[i + 1]) return true
        }
        return false
    }

    /** Using dynamic data structure eg: HashSet
     * Time complexity O(n)
     * Space complexity O(n)
     */
    fun containsDuplicate3(nums: IntArray): Boolean {
        val map = hashSetOf<Int>()
        for (n in nums) {
            if (map.contains(n)) {
                return true
            }
            map.add(n)
        }
        return false
    }

    /** Using Set
     * Time complexity O(n)
     * Space complexity O(n)
     */
    fun containsDuplicate4(nums: IntArray): Boolean {
        return nums.toSet().size != nums.size
    }
}

fun main() {
    val containsDuplicate = ContainsDuplicate()
    val input = intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)

    val firstStartTime = System.currentTimeMillis()
    println(containsDuplicate.containsDuplicate(input))
    val firstEndTime = System.currentTimeMillis()

    val secondStartTime = System.currentTimeMillis()
    println(containsDuplicate.containsDuplicate2(input))
    val secondEndTime = System.currentTimeMillis()

    val thirdStartTime = System.currentTimeMillis()
    println(containsDuplicate.containsDuplicate3(input))
    val thirdEndTime = System.currentTimeMillis()

    val forthStartTime = System.currentTimeMillis()
    println(containsDuplicate.containsDuplicate4(input))
    val forthEndTime = System.currentTimeMillis()

    println("First Solution execution time: ${firstEndTime - firstStartTime}")
    println("Second Solution execution time: ${secondEndTime - secondStartTime}")
    println("Third Solution execution time: ${thirdEndTime - thirdStartTime}")
    println("Forth Solution execution time: ${forthEndTime - forthStartTime}")
}