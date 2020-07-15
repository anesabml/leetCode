package week4

import java.util.*

class Subtree {
    fun isSubtree(s: TreeNode?, t: TreeNode?): Boolean {
        return isIdentical(s, t)
    }

    fun isIdentical(s: TreeNode?, t: TreeNode?): Boolean {
        return s != null && (isEquals(s, t) || isIdentical(s.left, t) || isIdentical(s.right, t))
    }

    fun isEquals(s: TreeNode?, t: TreeNode?): Boolean {
        if (s == null && t == null) {
            return true
        }
        if (s == null || t == null) {
            return false
        }

        return s.`val` == t.`val` && isEquals(s.left, t.left) && isEquals(s.right, t.right)
    }

    fun isSubtree2(s: TreeNode?, t: TreeNode?): Boolean {
        val sPreorder = getPreorderTraversal(s)
        val tPreorder = getPreorderTraversal(t)

        return sPreorder.contains(tPreorder)
    }

    private fun getPreorderTraversal(root: TreeNode?): String {
        return buildString {
            val stack: Stack<TreeNode?> = Stack()
            stack.push(root)
            while (stack.isNotEmpty()) {
                val current = stack.pop()
                if (current == null) {
                    append(",#")
                } else {
                    append(",${current.`val`}")
                    stack.push(current.right)
                    stack.push(current.left)
                }
            }
        }
    }
}