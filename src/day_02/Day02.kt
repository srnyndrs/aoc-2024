package day_02

import utils.Puzzle
import kotlin.math.abs

typealias Report = List<Int>

object Day02: Puzzle("02") {

    private fun Report.isSafe(): Boolean {
        // The levels are either all increasing or all decreasing.
        if(this.sorted() == this || this.sortedDescending() == this) {
            for(index in 0..<this.size - 1) {
                val current = this[index]
                val next = this[index+1]

                // Any two adjacent levels differ by at least one and at most three.
                if(abs(next - current) !in 1..3) {
                    return false
                }
            }
            return true
        }

        return false
    }

    private fun Report.isSafeWithToleration(): Boolean {
        // Check if already safe
        if(this.isSafe()) return true

        // Find out if it can be tolerated
        var tolerable = false
        for(index in indices) {
            // Create a sublist without the current element
            val subList = this.subList(0, index) + this.subList(index+1, this.size)

            // If the sublist is safe -> bad level found
            if(subList.isSafe()) {
                tolerable = true
                break
            }
        }

        return tolerable
    }

    private fun parseInput(input: List<String>): List<Report> {
        return input.map { line ->
            line.split(" ")
                .map { it.toInt() }
                .toList()
        }
    }

    override fun part1(input: List<String>): String {
        return parseInput(input)
            .count { it.isSafe() }
            .toString()
    }

    override fun part2(input: List<String>): String {
        return parseInput(input)
            .count { it.isSafeWithToleration() }
            .toString()
    }

}

fun main() {
    Day02.solvePuzzle(testOnly = false)
}