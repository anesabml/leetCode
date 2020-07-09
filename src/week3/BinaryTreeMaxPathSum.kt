package week3

import kotlin.math.max

class BinaryTreeMaxPathSum {

    private var max = Int.MIN_VALUE

    fun maxPathSum(root: TreeNode?): Int {
        calculateMax(root)
        return max
    }

    private fun calculateMax(root: TreeNode?): Int {
        if (root == null) return 0

        // Calculate the max of left and right
        val left = calculateMax(root.left)
        val right = calculateMax(root.right)

        val maxRightLeft = max(left, right)
        // Calculate the max at the current root.
        val maxNodeRoot = max(root.`val`, root.`val` + maxRightLeft)
        // Calculate the max
        val currentMax = max(maxNodeRoot, root.`val` + left + right)

        max = max(currentMax, max)

        return maxNodeRoot
    }
}