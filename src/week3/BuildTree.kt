package week3

class BuildTree {

    /** Recursive
     * Preorder -> root, left, right
     * Inorder -> left, root, right
     * We get the root from the preorder traversal and the left / right from inorder traversal
     * We find the root index in inoder traversal array, we know that inorder traverses left before right
     * So : left = what is before root index
     * right : what is after root index
     */
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        return buildTreeRec(0, 0, inorder.size - 1, preorder, inorder)
    }

    private fun buildTreeRec(
        preStart: Int,
        inStart: Int,
        inEnd: Int,
        preorder: IntArray,
        inorder: IntArray
    ): TreeNode? {
        if (preStart >= preorder.size || inStart > inEnd) {
            return null
        }

        val root = TreeNode(preorder[preStart])
        var inRoot = 0
        for (i in inStart..inEnd) {
            if (root.`val` == inorder[i]) {
                inRoot = i
            }
        }

        root.left = buildTreeRec(preStart + 1, inStart, inRoot - 1, preorder, inorder)
        root.right = buildTreeRec(preStart + inRoot - inStart + 1, inRoot + 1, inEnd, preorder, inorder)

        return root
    }

    /** Recursive
     * Same as before but we ruduce the search time by using a hashmap
     */
    fun buildTree2(preorder: IntArray, inorder: IntArray): TreeNode? {
        val inorderMap = hashMapOf<Int, Int>()
        inorder.forEachIndexed { index, i -> inorderMap[i] = index }

        return buildTreeRec(0, 0, inorder.size - 1, preorder, inorder, inorderMap)
    }

    private fun buildTreeRec(
        preStart: Int,
        inStart: Int,
        inEnd: Int,
        preorder: IntArray,
        inorder: IntArray,
        inorderMap: HashMap<Int, Int>
    ): TreeNode? {
        if (preStart >= preorder.size || inStart > inEnd) {
            return null
        }

        val root = TreeNode(preorder[preStart])
        val inRoot = inorderMap[root.`val`]!!

        root.left = buildTreeRec(preStart + 1, inStart, inRoot - 1, preorder, inorder, inorderMap)
        root.right = buildTreeRec(preStart + inRoot - inStart + 1, inRoot + 1, inEnd, preorder, inorder, inorderMap)

        return root
    }
}