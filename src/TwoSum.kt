import java.util.*

class TwoSum {

    /** Brute force
     * Time complexity O(n2)
     * Space complexity O(1)
     * Loop through each element x and find if there is a value that equals to target - x
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        for (i in 0 until nums.size) {
            for (j in i + 1 until nums.size) {
                if (nums[j] == target - nums[i]) {
                    return intArrayOf(i, j)
                }
            }
        }
        throw IllegalArgumentException("No solution")
    }

    /** Using a HashTable
     * Time complexity O(n)
     * Space complexity O(n)
     * Insert all the nums values and look for y
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
     * Time complexity O(n)
     * Space complexity O(n)
     * Insert all the nums values and look for y
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

    val firstStartTime = System.currentTimeMillis()
    println(twoSum.twoSum(input, target).contentToString())
    val firstEndTime = System.currentTimeMillis()

    val secondStartTime = System.currentTimeMillis()
    println(twoSum.twoSum2(input, target).contentToString())
    val secondEndTime = System.currentTimeMillis()

    val thirdStartTime = System.currentTimeMillis()
    println(twoSum.twoSum3(input, target).contentToString())
    val thirdEndTime = System.currentTimeMillis()

    println("First Solution execution time: ${firstEndTime - firstStartTime}")
    println("Second Solution execution time: ${secondEndTime - secondStartTime}")
    println("Third Solution execution time: ${thirdEndTime - thirdStartTime}")

}