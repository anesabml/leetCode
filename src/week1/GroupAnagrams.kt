package week1

import java.util.*


class GroupAnagrams {

    /** Group By
     * Time complexity : O(log n)
     * Space complexity : O(1)
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        return strs.groupBy { it.toCharArray().sorted() }.map { it.value }
    }

    /** Sort and Group By
     * Time complexity : O(k log n)
     * Space complexity : O(kn)
     */
    fun groupAnagrams2(strs: Array<String>): List<List<String>?> {
        if (strs.isEmpty()) return listOf()
        val output: MutableMap<String, MutableList<String>> = HashMap()
        for (s in strs) {
            val key = String(s.toCharArray().sortedArray())
            if (!output.containsKey(key))
                output[key] = mutableListOf()

            output[key]!!.add(s)
        }
        return output.values.toList()
    }

    /** Create a key and Group By
     * Time complexity : O(kn)
     * Space complexity : O(kn)
     */
    fun groupAnagrams3(strs: Array<String>): List<List<String>?> {
        if (strs.isEmpty()) return listOf()
        val output: MutableMap<String, MutableList<String>> = HashMap()
        for (s in strs) {
            val count = IntArray(26)
            for (c in s.toCharArray()) {
                count[c - 'a']++
            }
            val key = count.joinToString("#")
            if (!output.containsKey(key))
                output[key] = mutableListOf()

            output[key]!!.add(s)
        }
        return output.values.toList()
    }
}

fun main() {
    val input = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
    val groupAnagrams = GroupAnagrams()

    val firstStartTime = System.currentTimeMillis()
    println(groupAnagrams.groupAnagrams(input))
    val firstEndTime = System.currentTimeMillis()

    val secondStartTime = System.currentTimeMillis()
    println(groupAnagrams.groupAnagrams2(input))
    val secondEndTime = System.currentTimeMillis()

    val thirdStartTime = System.currentTimeMillis()
    println(groupAnagrams.groupAnagrams3(input))
    val thirdEndTime = System.currentTimeMillis()

    println("First Solution execution time: ${firstEndTime - firstStartTime}")
    println("Second Solution execution time: ${secondEndTime - secondStartTime}")
    println("Third Solution execution time: ${thirdEndTime - thirdStartTime}")

}