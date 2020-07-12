package week3

class SameTree {

    /** Recursive
     * Time complexity : O(n)
     * Space complexity : O(n)
     */
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null)
            return true
        if (p == null)
            return false
        if (q == null)
            return false

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right) && p.`val` == q.`val`
    }
}