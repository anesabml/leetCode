package week3

import java.util.*

class Codec {
    // Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String {
        if (root == null) return ""

        val stringBuilder = StringBuilder()
        stringBuilder.append("${root.`val`},")
        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val currentNode = queue.poll()
            if (currentNode.left == null) {
                stringBuilder.append("null,")
            } else {
                stringBuilder.append("${currentNode.left?.`val`},")
            }
            if (currentNode.right == null) {
                stringBuilder.append("null,")
            } else {
                stringBuilder.append("${currentNode.right?.`val`},")
            }
            currentNode.left?.let {
                queue.add(it)
            }
            currentNode.right?.let {
                queue.add(it)
            }
        }

        return stringBuilder.toString()
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        if (data.isEmpty()) return null
        val nodes = data.split(",")
        val root = TreeNode(nodes[0].toInt())
        var i = 1
        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val currentNode = queue.poll()
            if (nodes[i] == "null") {
                currentNode.left = null
            } else {
                currentNode.left = TreeNode(nodes[i].toInt())
                queue.add(currentNode.left)
            }
            i++
            if (nodes[i] == "null") {
                currentNode.right = null
            } else {
                currentNode.right = TreeNode(nodes[i].toInt())
                queue.add(currentNode.right)
            }
            i++
        }
        return root
    }
}