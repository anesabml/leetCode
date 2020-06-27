package week1

import kotlin.math.max
import kotlin.math.min
import kotlin.system.measureNanoTime

class MaxProfit {
    /** Brute force
     * Search for the max profit by calculating the profit between all the elements
     * Time complexity : O(n2)
     * Space complexity : O(1)
     */
    fun maxProfit(prices: IntArray): Int {
        var maxprofit = 0
        for (i in 0 until prices.size - 1) {
            for (j in i + 1 until prices.size) {
                val currentProfit = prices[j] - prices[i]
                if (currentProfit > maxprofit) maxprofit = currentProfit
            }
        }
        return maxprofit
    }

    /** Optimization
     * So we can get the min price and calculate the profit in the same time
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    fun maxProfit2(prices: IntArray): Int {
        var minPrice = Int.MAX_VALUE
        var maxProfit = 0
        prices.forEach { currentPrice ->
            minPrice = min(minPrice, currentPrice)
            maxProfit = max(currentPrice - minPrice, maxProfit)
        }

        return maxProfit
    }

}

fun main() {
    val input = intArrayOf(7, 1, 5, 3, 6, 4)

    val maxProfit = MaxProfit()

    val firstSolutionTime = measureNanoTime { println(maxProfit.maxProfit(input)) }
    val secondSolutionTime = measureNanoTime { println(maxProfit.maxProfit2(input)) }

    println("First Solution execution time: $firstSolutionTime")
    println("Second Solution execution time: $secondSolutionTime")
}