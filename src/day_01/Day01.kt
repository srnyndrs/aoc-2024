package day_01

import utils.Puzzle

object Day01: Puzzle("01") {
    override fun part1(input: List<String>): String {
        return input.joinToString(" ")
    }

    override fun part2(input: List<String>): String {
        return input.map{ it .toInt() * 10 }.joinToString(" ")
    }
}

fun main() {
    Day01.solvePuzzle(testOnly = false)
}