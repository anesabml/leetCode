package week5

class NextGreaterElement {
    fun nextGreaterElement(n: Int): Int {
        val numbers = n.toString().toCharArray()
        var index = 0
        // If the number is in descending order then there's no possible solution
        for (i in (numbers.size - 1) downTo 1) {
            if (numbers[i - 1] < numbers[i]) {
                index = i
                break
            }
        }

        if (index == 0) {
            return -1
        }

        // Find the smallest digit on the right side
        val x = numbers[index - 1]
        var smallest = index
        for (j in index + 1 until numbers.size) {
            if (numbers[j] > x && numbers[j] <= numbers[smallest]) {
                smallest = j
            }
        }

        // Swap the smallest digit with numbers[i-1]
        val temp = numbers[index - 1]
        numbers[index - 1] = numbers[smallest]
        numbers[smallest] = temp

        // Sort the right side of the number
        numbers.sort(index, numbers.size)

        val nextGreaterElement = numbers.joinToString(separator = "").toLong()
        return if (nextGreaterElement <= Int.MAX_VALUE) nextGreaterElement.toInt() else -1
    }
}

fun main() {
    val nextGreaterElement = NextGreaterElement()
    println(nextGreaterElement.nextGreaterElement(21))
}