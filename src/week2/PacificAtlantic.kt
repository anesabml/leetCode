package week2

import kotlin.system.measureNanoTime

class PacificAtlantic {

    /**
     * Using two boolean matrix to keep track of the grid elements that each ocean can be reached from
     * in the end if the grid element can be reached from both oceans we add it to the list.
     * Time complexity: O(n*m)
     * Space complexity: O(n*m)
     */
    fun pacificAtlantic(matrix: Array<IntArray>): List<List<Int>> {
        if (matrix.isEmpty()) return emptyList()

        val output = mutableListOf<List<Int>>()
        val pacificVisited = Array(matrix.size) { BooleanArray(matrix[0].size) }
        val atlanticVisited = Array(matrix.size) { BooleanArray(matrix[0].size) }

        // Loop through the matrix to check the visited elements from both sides (right, left)
        for (i in matrix.indices) {
            dfs(matrix, pacificVisited, Int.MIN_VALUE, i, 0)
            dfs(matrix, atlanticVisited, Int.MIN_VALUE, i, matrix[0].size - 1)
        }

        // Loop through the matrix to check the visited elements from both sides (up, bottom)
        for (i in matrix[0].indices) {
            dfs(matrix, pacificVisited, Int.MIN_VALUE, 0, i)
            dfs(matrix, atlanticVisited, Int.MIN_VALUE, matrix.size - 1, i)
        }

        for (i in pacificVisited.indices) {
            for (j in pacificVisited[i].indices) {
                if (pacificVisited[i][j] && atlanticVisited[i][j]) {
                    output.add(listOf(i, j))
                }
            }
        }

        return output
    }

    private fun dfs(matrix: Array<IntArray>, visited: Array<BooleanArray>, height: Int, i: Int, j: Int) {
        if (i < 0 || i >= matrix.size || j < 0 || j >= matrix[0].size || matrix[i][j] < height || visited[i][j]) {
            return
        }

        visited[i][j] = true

        dfs(matrix, visited, matrix[i][j], i - 1, j)
        dfs(matrix, visited, matrix[i][j], i + 1, j)
        dfs(matrix, visited, matrix[i][j], i, j - 1)
        dfs(matrix, visited, matrix[i][j], i, j + 1)

    }

    /**
     * Same as the solution before but instead of two boolean matrix we use on integer matrix
     * Time complexity: O(n*m)
     * Space complexity: O(n*m)
     */
    private val PACIFIC = 1
    private val ATLANTIC = 2
    private val BOTH = 3
    fun pacificAtlantic2(matrix: Array<IntArray>): List<List<Int>> {
        if (matrix.isEmpty()) return emptyList()

        val output = mutableListOf<List<Int>>()
        val visited = Array(matrix.size) { IntArray(matrix[0].size) }

        // Loop through the matrix to check the visited elements from both sides (right, left)
        for (i in matrix.indices) {
            dfs(matrix, visited, Int.MIN_VALUE, i, 0, PACIFIC)
            dfs(matrix, visited, Int.MIN_VALUE, i, matrix[0].size - 1, ATLANTIC)
        }

        // Loop through the matrix to check the visited elements from both sides (up, bottom)
        for (i in matrix[0].indices) {
            dfs(matrix, visited, Int.MIN_VALUE, 0, i, PACIFIC)
            dfs(matrix, visited, Int.MIN_VALUE, matrix.size - 1, i, ATLANTIC)
        }

        for (i in visited.indices) {
            for (j in visited[i].indices) {
                if (visited[i][j] == 3) {
                    output.add(listOf(i, j))
                }
            }
        }

        return output
    }

    private fun dfs(matrix: Array<IntArray>, visited: Array<IntArray>, height: Int, i: Int, j: Int, ocean: Int) {
        if (i < 0 || i >= matrix.size || j < 0 || j >= matrix[0].size || matrix[i][j] < height || visited[i][j] == ocean || visited[i][j] == BOTH) {
            return
        }

        visited[i][j] = if (visited[i][j] > 0) BOTH else ocean

        dfs(matrix, visited, matrix[i][j], i - 1, j, ocean)
        dfs(matrix, visited, matrix[i][j], i + 1, j, ocean)
        dfs(matrix, visited, matrix[i][j], i, j - 1, ocean)
        dfs(matrix, visited, matrix[i][j], i, j + 1, ocean)

    }
}

fun main() {
    val input = arrayOf(
        intArrayOf(1, 2, 2, 3, 5),
        intArrayOf(3, 2, 3, 4, 4),
        intArrayOf(2, 4, 5, 3, 1),
        intArrayOf(6, 7, 1, 4, 5),
        intArrayOf(5, 1, 1, 2, 4)
    )

    val pacificAtlantic = PacificAtlantic()

    val firstSolutionTime = measureNanoTime { println(pacificAtlantic.pacificAtlantic(input)) }
    val secondSolutionTime = measureNanoTime { println(pacificAtlantic.pacificAtlantic2(input)) }

    println("First Solution execution time: $firstSolutionTime")
    println("Second Solution execution time: $secondSolutionTime")
}