package week1

import java.util.*
import kotlin.system.measureNanoTime


class ValidParentheses {

    /** Using Stack
     * Initialize a stack S.
     * Process each bracket of the expression one at a time.
     * If we encounter an opening bracket, we simply push it onto the stack.
     * This means we will process it later, let us simply move onto the sub-expression ahead.
     * If we encounter a closing bracket, then we check the element on top of the stack.
     * If the element at the top of the stack is an opening bracket of the same type, then we pop it off the stack and continue processing.
     * Else, this implies an invalid expression.
     * In the end, if we are left with a stack still having elements, then this implies an invalid expression.
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun isValid(s: String): Boolean {
        val mappings =
            hashMapOf(
                ')' to '(',
                '}' to '{',
                ']' to '['
            )
        val stack = Stack<Char>()
        for (c in s) {
            if (mappings.containsKey(c)) {
                val element = if (stack.empty()) '#' else stack.pop()
                if (mappings[c] != element) return false
            } else {
                stack.push(c)
            }
        }
        return stack.empty()
    }

    /** Brute force
     * Iterate over the array and calculate the multiply resutl
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun isValid2(s: String): Boolean {
        val map = hashMapOf(
            '(' to ')',
            '{' to '}',
            '[' to ']'
        )
        val stack = Stack<Char>()
        for (c in s) {
            if (c in map.keys) {
                stack.push(map[c])
            } else if (stack.empty() || stack.pop() != c) {
                return false
            }
        }
        return stack.empty()
    }
}

fun main() {
    val input = "{[]}"

    val validParentheses = ValidParentheses()

    val firstSolutionTime = measureNanoTime { println(validParentheses.isValid(input)) }
    val secondSolutionTime = measureNanoTime { println(validParentheses.isValid2(input)) }

    println("First Solution execution time: $firstSolutionTime")
    println("Second Solution execution time: $secondSolutionTime")
}