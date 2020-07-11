package week3

import java.lang.Integer.max
import java.util.*

class MaxDepthBinaryTree {

    /** Recursive
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun maxDepth(root: TreeNode?): Int {
        return if (root == null) 0 else Math.max(maxDepth(root.left), maxDepth(root.right)) + 1
    }

    /** BFS
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun maxDepthBFS(root: TreeNode?): Int {
        if (root == null) return 0

        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)
        var count = 0
        while (queue.isNotEmpty()) {
            var size = queue.size
            while (size-- > 0) {
                val current = queue.remove()
                if (current.left != null) {
                    queue.add(current.left)
                }
                if (current.right != null) {
                    queue.add(current.right)
                }
            }
            count++
        }
        return count
    }

    /** DFS
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun maxDepthDFS(root: TreeNode?): Int {
        if (root == null) return 0

        val queue: Queue<Pair<TreeNode, Int>> = LinkedList()
        queue.add(Pair(root, 1))
        var max = 0
        while (queue.isNotEmpty()) {
            val (current, depth) = queue.remove()
            max = max(max, depth)
            if (current.left != null) {
                queue.add(Pair(current.left!!, depth + 1))
            }
            if (current.right != null) {
                queue.add(Pair(current.right!!, depth + 1))
            }
        }
        return max
    }
}