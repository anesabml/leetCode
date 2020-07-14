package week4

class TrieNode {
    private val links = Array<TrieNode?>(26) { null }
    private var isEnd = false

    fun containsKey(ch: Char): Boolean {
        return links[ch - 'a'] != null
    }

    fun get(ch: Char): TrieNode {
        return links[ch - 'a']!!
    }

    fun put(ch: Char, node: TrieNode) {
        links[ch - 'a'] = node
    }

    fun setEnd() {
        isEnd = true
    }

    fun isEnd(): Boolean = isEnd
}

class Trie {

    /** Initialize your data structure here. */
    val root: TrieNode = TrieNode()


    /** Inserts a word into the trie. */
    fun insert(word: String) {
        var node = root
        for (ch in word) {
            if (!node.containsKey(ch)) {
                node.put(ch, TrieNode())
            }
            node = node.get(ch)
        }
        node.setEnd()
    }

    /** Returns if the word is in the trie. */
    fun search(word: String): Boolean {
        var node = root
        for (ch in word) {
            if (node.containsKey(ch)) {
                node = node.get(ch)
            } else {
                return false
            }
        }
        return node.isEnd()
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    fun startsWith(prefix: String): Boolean {
        var node = root
        for (ch in prefix) {
            if (node.containsKey(ch)) {
                node = node.get(ch)
            } else {
                return false
            }
        }
        return true
    }

}

fun main() {
    val obj = Trie()
    obj.insert("anes")
    println(obj.search("anes"))
    println(obj.startsWith("an"))
}