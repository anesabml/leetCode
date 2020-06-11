package week1

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

    val firstStartTime = System.currentTimeMillis()
    println(validAnagram.isAnagram(s, t))
    val firstEndTime = System.currentTimeMillis()

    val secondStartTime = System.currentTimeMillis()
    println(validAnagram.isAnagram2(s, t))
    val secondEndTime = System.currentTimeMillis()

    val thirdStartTime = System.currentTimeMillis()
    println(validAnagram.isAnagram3(s, t))
    val thirdEndTime = System.currentTimeMillis()

    val forthStartTime = System.currentTimeMillis()
    println(validAnagram.isAnagram4(s, t))
    val forthEndTime = System.currentTimeMillis()

    println("First Solution execution time: ${firstEndTime - firstStartTime}")
    println("Second Solution execution time: ${secondEndTime - secondStartTime}")
    println("Third Solution execution time: ${thirdEndTime - thirdStartTime}")
    println("Forth Solution execution time: ${forthEndTime - forthStartTime}")
}