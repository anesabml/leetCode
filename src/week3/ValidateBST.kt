package week3

import java.util.*


class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class ValidateBST {

    fun isValidBST(root: TreeNode?): Boolean {
        var current = root
        val stack = Stack<TreeNode>()
        var min = Double.MIN_VALUE
        while (stack.isNotEmpty() || current != null) {
            while (current != null) {
                stack.push(current)
                current = current.left
            }

            current = stack.pop()
            if (current.`val` <= min) return false
            min = current.`val`.toDouble()
            current = current.right
        }
        return true
    }

    fun isValidBSTRec(root: TreeNode?): Boolean {
        return isBSTValid(root, Int.MIN_VALUE, Int.MAX_VALUE)
    }

    private fun isBSTValid(root: TreeNode?, minValue: Int, maxValue: Int): Boolean {
        if (root == null) return true

        val value = root.`val`
        if (value <= minValue || value >= maxValue) return false

        if (!isBSTValid(root.left, minValue, value) || !isBSTValid(root.right, value, maxValue)) return false

        return true
    }
}