package week3

import java.util.*
import kotlin.system.measureNanoTime


class CourseSchedule {

    /** BFS */
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val graph = Array<MutableList<Int>>(numCourses) { mutableListOf() }
        val inDegree = IntArray(numCourses)

        for (pre in prerequisites) {
            inDegree[pre[1]]++
            graph[pre[0]].add(pre[1])
        }

        val queue: Queue<Int> = LinkedList<Int>()
        for (i in inDegree.indices) {
            if (inDegree[i] == 0) {
                queue.add(i)
            }
        }

        var count = 0
        while (queue.isNotEmpty()) {
            val course = queue.poll()
            count++
            for (pointer in graph[course]) {
                inDegree[pointer]--
                if (inDegree[pointer] == 0) {
                    queue.add(pointer)
                }
            }
        }

        return count == numCourses
    }

    /** DFS */
    fun canFinish2(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val graph = Array<MutableList<Int>>(numCourses) { mutableListOf() }
        for (pre in prerequisites) {
            graph[pre[0]].add(pre[1])
        }

        val visited = BooleanArray(numCourses)
        val dp = BooleanArray(numCourses)
        for (i in 0 until numCourses) {
            if (!dfs(i, graph, visited, dp)) return false
        }

        return true
    }

    private fun dfs(course: Int, graph: Array<MutableList<Int>>, visited: BooleanArray, dp: BooleanArray): Boolean {
        if (visited[course]) {
            return dp[course]
        }

        visited[course] = true

        for (i in graph[course]) {
            if (!dfs(i, graph, visited, dp)) {
                dp[course] = false
                return false
            }
        }

        dp[course] = true
        return true
    }
}

fun main() {
    val numCourses = 2
    val prerequisites = arrayOf(intArrayOf(1, 0))
    val courseSchedule = CourseSchedule()

    val firstSolutionTime = measureNanoTime { println(courseSchedule.canFinish(numCourses, prerequisites)) }
    val secondSolutionTime = measureNanoTime { println(courseSchedule.canFinish2(numCourses, prerequisites)) }

    println("BFS Solution execution time: $firstSolutionTime")
    println("DFS Solution execution time: $secondSolutionTime")
}