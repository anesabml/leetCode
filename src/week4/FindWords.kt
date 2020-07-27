package week4

class FindWords {
    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val result = mutableListOf<String>()
        val root = buildTrie(words)

        for (i in board.indices) {
            for (j in board[0].indices) {
                dfs(board, root, result, i, j)
            }
        }
        return result
    }

    private fun dfs(board: Array<CharArray>, p: TrieNode, result: MutableList<String>, i: Int, j: Int) {
        if (i < 0 || i >= board.size || j < 0 || j >= board[0].size || board[i][j] == '#' || p.next[board[i][j] - 'a'] == null) {
            return
        }

        val currentChar = board[i][j]
        val _p = p.next[currentChar - 'a']!!
        if (_p.word != "") {
            result.add(_p.word)
            _p.word = ""
        }

        board[i][j] = '#'
        dfs(board, _p, result, i - 1, j)
        dfs(board, _p, result, i + 1, j)
        dfs(board, _p, result, i, j - 1)
        dfs(board, _p, result, i, j + 1)
        board[i][j] = currentChar
    }

    private fun buildTrie(words: Array<String>): TrieNode {
        val root = TrieNode()
        for (word in words) {
            var p = root
            for (c in word) {
                val i = c - 'a'
                if (p.next[i] == null) {
                    p.next[i] = TrieNode()
                }
                p = p.next[i]!!
            }
            p.word = word
        }
        return root
    }

    class TrieNode {
        val next = Array<TrieNode?>(26) { null }
        var word: String = ""
    }
}