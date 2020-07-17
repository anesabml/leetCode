package week4

import java.util.*

class KthSmallestElementBST {

    fun kthSmallest(root: TreeNode?, k: Int): Int {
        if (root == null || k == 0) return 0

        val stack: Stack<TreeNode> = Stack()
        var currentNode = root
        var j = k
        while (true) {
            while (currentNode != null) {
                stack.add(currentNode)
                currentNode = currentNode.left
            }
            currentNode = stack.pop()
            if (--j == 0) return currentNode.`val`
            currentNode = currentNode.right
        }
    }

    fun kthSmallest2(root: TreeNode?, k: Int): Int {
        val inorder: MutableList<Int> = mutableListOf()
        inorder(root, inorder)
        return inorder[k - 1]
    }

    private fun inorder(root: TreeNode?, inorder: MutableList<Int>): MutableList<Int> {
        if (root == null) return inorder

        inorder(root.left, inorder)
        inorder.add(root.`val`)
        inorder(root.right, inorder)

        return inorder
    }
}