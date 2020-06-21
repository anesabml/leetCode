package week2

class NumberOfIslands {

    fun numIslands(grid: Array<CharArray>): Int {
        var numberOfIslands = 0
        grid.forEachIndexed { i, chars ->
            chars.forEachIndexed { j, c ->
                if (c == '1')
                    numberOfIslands += 1
                bfs(grid, i, j)
            }
        }
        return numberOfIslands
    }

    private fun bfs(grid: Array<CharArray>, i: Int, j: Int) {
        if (i < 0 || i >= grid.size || j < 0 || j >= grid[i].size || grid[i][j] == '0') {
            return
        }

        grid[i][j] = '0'

        bfs(grid, i - 1, j)
        bfs(grid, i + 1, j)
        bfs(grid, i, j - 1)
        bfs(grid, i, j + 1)
    }
}

fun main() {
    val input = arrayOf(
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '1', '0', '0'),
        charArrayOf('0', '0', '0', '1', '1')
    )

    val numberOfIslands = NumberOfIslands()

    println(numberOfIslands.numIslands(input))
}