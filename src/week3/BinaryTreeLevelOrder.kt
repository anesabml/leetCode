package week3

import java.util.*

class BinaryTreeLevelOrder {

    /** BFS
     * Time complexity : O(n)
     * Space complexity : O(n) */
    fun levelOrderBFS(root: TreeNode?): List<List<Int>> {
        if (root == null) return mutableListOf()
        val output: MutableList<MutableList<Int>> = mutableListOf()
        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val list = mutableListOf<Int>()
            var size = queue.size
            while (size-- > 0) {
                val current = queue.remove()
                list.add(current.`val`)
                if (current.left != null) {
                    queue.add(current.left)
                }
                if (current.right != null) {
                    queue.add(current.right)
                }
            }
            output.add(list)

        }
        return output
    }

    /** DFS
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun levelOrderDFS(root: TreeNode?): List<List<Int>> {
        val output: MutableList<MutableList<Int>> = mutableListOf()
        getLevelOrder(output, root, 0)
        return output
    }

    private fun getLevelOrder(list: MutableList<MutableList<Int>>, root: TreeNode?, level: Int) {
        if (root == null) {
            return
        }

        if (level >= list.size) {
            list.add(mutableListOf())
        }
        list[level].add(root.`val`)
        getLevelOrder(list, root.left, level + 1)
        getLevelOrder(list, root.right, level + 1)
    }
}