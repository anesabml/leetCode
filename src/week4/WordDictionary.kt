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

class WordDictionary {

    /** Initialize your data structure here. */
    val root: TrieNode = TrieNode()

    /** Adds a word into the data structure. */
    fun addWord(word: String) {
        var node = root
        for (ch in word) {
            if (!node.containsKey(ch)) {
                node.put(ch, TrieNode())
            }
            node = node.get(ch)
        }
        node.setEnd()
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    fun search(word: String): Boolean {
        return matches(word, root, 0)
    }

    fun matches(word: String, node: TrieNode, index: Int): Boolean {
        if (index == word.length) return node.isEnd()
        if (word[index] != '.') {
            return node.containsKey(word[index]) && matches(word, node.get(word[index]), index + 1)
        }
        for (c in 'a'..'z') {
            if (node.containsKey(c) && matches(word, node.get(c), index + 1)) {
                return true
            }
        }
        return false
    }
}

fun main() {
    val word = "Anes"
    var obj = WordDictionary()
    obj.addWord(word)
    var param_2 = obj.search(word)
}