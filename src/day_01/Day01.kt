package day_01

import utils.Puzzle
import kotlin.math.abs

object Day01: Puzzle("01") {

    private fun parseInput(input: List<String>): List<Pair<Long, Long>> {
        return input.map { line ->
            line.trim()
                .split("\\s+".toRegex())
                .let { (a, b) -> a.toLong() to b.toLong() }
        }
    }

    override fun part1(input: List<String>): String {
        val pairs = parseInput(input)

        val left = pairs.map { it.first }.sorted()
        val right = pairs.map { it.second }.sorted()

        return left.zip(right)
            .sumOf { abs(it.second - it.first) }
            .toString()
    }

    override fun part2(input: List<String>): String {
        val pairs = parseInput(input)

        val left = pairs.map { it.first }
        val right = pairs.map { it.second }

        return left.sumOf { leftValue ->
            leftValue * right.count { rightValue ->
                rightValue == leftValue
            }
        }.toString()
    }
}

fun main() {
    Day01.solvePuzzle(testOnly = false)
}