package week2

class CharactersReplacement {

    /** Sliding window
     * We create a window and keep track of the max count char in the array.
     * Then to calculate the number of change operations in the array,
     * we subtract the window size (end - start + 1) form the max count.
     * (
     *  eg: AABAB
     *  max count = 3,
     *  window size = 5,
     *  number of changes = 2
     * )
     * If number of changes is bigger then K we shrink the Array by incrementing the start point
     * and decrementing the count of the removed character from the array (because we removed it).
     * In the end we are keeping track of the max length of the window.
     */
    fun characterReplacement(s: String, k: Int): Int {
        var start = 0
        var maxCount = 0
        var maxLength = 0
        val characterCounts = IntArray(26)
        for (end in s.indices) {
            characterCounts[s[end] - 'A'] += 1
            maxCount = maxCount.coerceAtLeast(characterCounts[s[end] - 'A'])
            while (end - start + 1 - maxCount > k) {
                characterCounts[s[start] - 'A'] -= 1
                start += 1
            }
            maxLength = maxLength.coerceAtLeast(end - start + 1)
        }
        return maxLength
    }
}

fun main() {
    val input = "AABABBA"

    val charactersReplacement = CharactersReplacement()
    println(charactersReplacement.characterReplacement(input, 1))
}