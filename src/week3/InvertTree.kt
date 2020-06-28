package week3

import java.util.*

class InvertTree {

    /** Recursive
     * Time complexity = O(n)
     * Space complexity = O(n)
     */
    fun invertTreeRec(root: TreeNode?): TreeNode? {
        if (root == null) return null

        val left = invertTreeRec(root.left)
        val right = invertTreeRec(root.right)
        root.left = right
        root.right = left

        return root
    }

    /** Iterative Breadth first search
     * Time complexity = O(n)
     * Space complexity = O(n)
     */
    fun invertTreeIterative(root: TreeNode?): TreeNode? {
        if (root == null) return null
        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val currentNode = queue.poll()
            val temp = currentNode.left
            currentNode.left = currentNode.right
            currentNode.right = temp

            if (currentNode.left != null) queue.add(currentNode.left)
            if (currentNode.right != null) queue.add(currentNode.right)
        }
        return root
    }
}