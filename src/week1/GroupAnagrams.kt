package week1

import java.util.*
import kotlin.system.measureNanoTime


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

    val firstSolutionTime = measureNanoTime { println(groupAnagrams.groupAnagrams(input)) }
    val secondSolutionTime = measureNanoTime { println(groupAnagrams.groupAnagrams2(input)) }
    val thirdSolutionTime = measureNanoTime { println(groupAnagrams.groupAnagrams3(input)) }

    println("First Solution execution time: $firstSolutionTime")
    println("Second Solution execution time: $secondSolutionTime")
    println("Third Solution execution time: $thirdSolutionTime")

}