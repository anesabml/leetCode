package week2

import kotlin.math.max


class LongestNonRepeatingSubstring {

    /** HashSet
     * We keep track of the window in a set
     * In each iteration:
     * If the char is in the window we slide the start index to the right
     * Else we slide the end index to the right
     * We update the max count
     * Time complexity : O(n)
     * Space complexity : O(m)
     */
    fun lengthOfLongestSubstring(s: String): Int {
        val length = s.length
        var start = 0
        var end = 0
        var maxCount = 0
        val set = hashSetOf<Char>()
        while (start < length && end < length) {
            if (set.contains(s[end])) {
                set.remove(s[start++])
            } else {
                set.add(s[end++])
                maxCount = max(maxCount, end - start)
            }
        }
        return maxCount
    }

    /** Array
     * We keep track of chars indexes in an Array
     * In each iteration we update the start
     * If there's a duplicate, start will be updated to the right of the same char index
     * Then we update the index of the character
     * Then we update the max count
     * Time complexity : O(n)
     * Space complexity : O(m)
     * */
    fun lengthOfLongestSubstring2(s: String): Int {
        var start = 0
        var maxCount = 0
        val charsCount = IntArray(128)
        for (end in s.indices) {
            start = max(charsCount[s[end].toInt()], start)
            charsCount[s[end].toInt()] = end + 1
            maxCount = max(maxCount, end - start + 1)
        }
        return maxCount
    }

    /** HashMap
     * We keep track of chars indexes in an hash map
     * In each iteration we update the start
     * If there's a duplicate, start will be updated to the right of the same char index
     * Then we update the index of the character
     * Then we update the max count
     * Time complexity : O(n)
     * Space complexity : O(m)
     * */
    fun lengthOfLongestSubstring3(s: String): Int {
        var start = 0
        var maxCount = 0
        val map = hashMapOf<Char, Int>()
        for (end in s.indices) {
            if (map.containsKey(s[end])) {
                start = max(map[s[end]]!!, start)
            }
            map[s[end]] = end + 1
            maxCount = max(maxCount, end - start + 1)
        }
        return maxCount
    }
}

fun main() {
    val input = "abcabcbb"

    val longestNonRepeatingSubstring = LongestNonRepeatingSubstring()

    println(longestNonRepeatingSubstring.lengthOfLongestSubstring3(input))
}