package week2

import kotlin.math.min
import kotlin.system.measureNanoTime

class PalindromicSubstrings {

    /** Center expanding
     * Iterate over all the palindromic centers and expand
     * Time complexity : O(n2)
     * Space complexity : O(1)
     */
    fun countSubstrings(s: String): Int {
        val length = s.length
        var ans = 0
        for (center in 0 until 2 * length) {
            var left = center / 2
            var right = left + center % 2
            while (left >= 0 && right < length && s[left] == s[right]) {
                ans++
                left--
                right++
            }
        }
        return ans
    }

    /** Dynamic programming
     * Time complexity : O(n2)
     * Space complexity : O(1)
     */
    fun countSubstringsDP(s: String): Int {
        val length = s.length
        var ans = 0
        val dp = Array(length) { BooleanArray(length) }
        for (i in s.indices) {
            for (j in i downTo 0) {
                dp[i][j] = s[i] == s[j] && (i - j < 3 || dp[i - 1][j + 1])
                if (dp[i][j]) {
                    ans++
                }
            }
        }
        return ans
    }

    /** Manacher's Algorithm
     * Time complexity : O(n2)
     * Space complexity : O(1)
     */
    fun countSubstringsManacher(s: String): Int {
        var chars = CharArray(2 * s.length + 3)
        chars[0] = '@'
        chars[1] = '#'
        chars[chars.size - 1] = '$'
        var t = 2
        s.forEachIndexed { index, c ->
            chars[t++] = c
            chars[t++] = '#'
        }

        /*val stringBuilder = StringBuilder()
        stringBuilder.append('@')
        stringBuilder.append('#')
        s.forEach { c ->
            stringBuilder.append(c)
            stringBuilder.append('#')
        }
        stringBuilder.append('$')
        val str = stringBuilder.toString()*/

        var center = 0
        var right = 0
        val length = chars.size
        val plen = IntArray(length)
        for (i in 1 until length - 1) {
            val mirror = 2 * center - i

            if (i < right) {
                plen[i] = min(right - i, plen[mirror])
            }

            while (chars[i - (1 + plen[i])] == chars[i + (1 + plen[i])]) {
                plen[i]++
            }

            if (i + plen[i] > right) {
                center = i
                right = i + plen[i]
            }
        }

        return plen.reduce { acc, i -> acc + (i + 1) / 2 }
    }
}

fun main() {
    val input = "aaaa"
    val palindromicSubstrings = PalindromicSubstrings()

    val firstSolutionTime = measureNanoTime { println(palindromicSubstrings.countSubstrings(input)) }
    val secondSolutionTime = measureNanoTime { println(palindromicSubstrings.countSubstringsDP(input)) }
    val thirdSolutionTime = measureNanoTime { println(palindromicSubstrings.countSubstringsManacher(input)) }


    println("First Solution execution time: $firstSolutionTime")
    println("Second Solution execution time: $secondSolutionTime")
    println("Third Solution execution time: $thirdSolutionTime")
}