package week2


class MinimumWindowSubstring {

    /** Sliding window
     * n = length of the String s
     * k = length of the String t
     * Time complexity: O(n+k)
     * Space complexity : O(n+k)
     */
    fun minWindow(s: String, t: String): String {
        if (s.isEmpty() || t.isEmpty()) {
            return ""
        }

        val dictT: MutableMap<Char, Int> = mutableMapOf()
        t.forEach {
            dictT[it] = dictT.getOrDefault(it, 0) + 1
        }

        val filtered: MutableList<Pair<Char, Int>> = mutableListOf()
        s.forEachIndexed { index, c ->
            if (dictT.containsKey(c)) filtered.add(Pair(c, index))
        }

        var left = 0
        var right = 0
        var formed = 0
        val windowCount: MutableMap<Char, Int> = mutableMapOf()
        val ans = intArrayOf(-1, 0, 0)

        while (right < filtered.size) {
            var currentChar = filtered[right].first
            windowCount[currentChar] = windowCount.getOrDefault(currentChar, 0) + 1
            if (dictT.containsKey(currentChar) && windowCount[currentChar]!! == dictT[currentChar]!!) {
                formed += 1
            }

            while (left <= right && formed == dictT.size) {
                val start = filtered[left].second
                val end = filtered[right].second
                val windowLength = end - start + 1
                if (ans[0] == -1 || windowLength < ans[0]) {
                    ans[0] = windowLength
                    ans[1] = start
                    ans[2] = end
                }
                currentChar = filtered[left].first
                windowCount[currentChar] = windowCount[currentChar]!! - 1
                if (dictT.containsKey(currentChar) && windowCount[currentChar]!! < dictT[currentChar]!!) {
                    formed -= 1
                }
                left += 1
            }
            right += 1
        }

        return if (ans[0] == -1) "" else s.substring(ans[1], ans[2] + 1)
    }
}

fun main() {
    val s = "a"
    val t = "a"

    val minimumWindowSubstring = MinimumWindowSubstring()
    println(minimumWindowSubstring.minWindow(s, t))
}