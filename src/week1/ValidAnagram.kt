package week1

import kotlin.system.measureNanoTime

class ValidAnagram {

    /** Sorting
     * Sort both strings and compare the results
     * Time complexity : O(n log n)
     * Space complexity : O(1)
     */
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val str1 = s.toCharArray().sortedArray()
        val str2 = t.toCharArray().sortedArray()
        return str1.contentEquals(str2)
    }

    /** Array
     * Counting the number of times the char appears in each string.
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    fun isAnagram2(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val counter = IntArray(26)
        for (i in s.indices) {
            counter[s[i] - 'a']++
            counter[t[i] - 'a']--
        }
        for (count in counter) {
            if (count != 0) {
                return false
            }
        }
        return true
    }

    /** HashMap
     * Counting the number of times the char appears in each string.
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    fun isAnagram3(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val counter = hashMapOf<Int, Int>()
        for (i in s.indices) {
            counter[s[i] - 'a'] = counter.getOrDefault(s[i] - 'a', 0) + 1
            counter[t[i] - 'a'] = counter.getOrDefault(t[i] - 'a', 0) - 1
        }
        for (count in counter.values) {
            if (count != 0) {
                return false
            }
        }
        return true
    }

    /**
     * Loop through all the alphabets and count how many times
     * it appears on each string and compare the results
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    fun isAnagram4(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        for (char in 'a'..'z') {
            if (t.count { it == char } != s.count { it == char }) {
                return false
            }
        }
        return true
    }
}

fun main() {
    val s =
        "anagrameinamdinebemeidlsoiwemkpswiemewiosnmcuhgoreonviremoiewnagaramfdsajkopliiepinmbcumiiewqmmsizoiesplmcjnmcbbdccymnmdienagaramfdsajkopliiepinmbcumiiewqmmsizoiesplmcjnmcbbdccymnmdieanagrameinamdinebemeidlsoiwemkpswiemewiosnmcuhgoreonviremoiew"
    val t =
        "nagaramfdsajkopliiepinmbcumiiewqmmsizoiesplmcjnmcbbdccymnmdieanagrameinamdinebemeidlsoiwemkpswiemewiosnmcuhgoreonviremoiewanagrameinamdinebemeidlsoiwemkpswiemewiosnmcuhgoreonviremoiewnagaramfdsajkopliiepinmbcumiiewqmmsizoiesplmcjnmcbbdccymnmdie"

    val validAnagram = ValidAnagram()

    val firstSolutionTime = measureNanoTime { println(validAnagram.isAnagram(s, t)) }
    val secondSolutionTime = measureNanoTime { println(validAnagram.isAnagram2(s, t)) }
    val thirdSolutionTime = measureNanoTime { println(validAnagram.isAnagram3(s, t)) }
    val forthSolutionTime = measureNanoTime { println(validAnagram.isAnagram4(s, t)) }

    println("First Solution execution time: $firstSolutionTime")
    println("Second Solution execution time: $secondSolutionTime")
    println("Third Solution execution time: $thirdSolutionTime")
    println("Forth Solution execution time: $forthSolutionTime")
}