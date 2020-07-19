package week4

class LowestComonAncestor {

    /** Iterative
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || p == null || q == null) return null

        val pVal = p.`val`
        val qVal = q.`val`

        var current = root
        while (current != null) {
            val rootVal = current.`val`
            if (pVal > rootVal && qVal > rootVal) {
                current = current.right
            } else if (pVal < rootVal && qVal < rootVal) {
                current = current.left
            } else {
                return root
            }
        }
        return null
    }

    /** Recursive
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    fun lowestCommonAncestorRec(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || p == null || q == null) return null

        val rootVal = root.`val`
        val pVal = p.`val`
        val qVal = q.`val`

        if (pVal > rootVal && qVal > rootVal) {
            return lowestCommonAncestor(root.right, p, q)
        }

        if (pVal < rootVal && qVal < rootVal) {
            return lowestCommonAncestor(root.left, p, q)
        }

        return root
    }


    /** Recursive one liner (not recommended)
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    fun lowestCommonAncestorRec2(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || p == null || q == null) return null
        return if ((p.`val` - root.`val`) * (q.`val` - root.`val`) > 0)
            lowestCommonAncestorRec2(
                if (p.`val` < root.`val`) root.left else root.right,
                p,
                q
            )
        else root
    }
}