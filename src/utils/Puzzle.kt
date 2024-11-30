package utils

abstract class Puzzle (private val day: String){
    abstract fun part1(input: List<String>): String
    abstract fun part2(input: List<String>): String

    fun solvePuzzle(testOnly: Boolean = false) {
        println("[AoC - 2024/$day]")
        // Read inputs
        val testInput = readTestInput(day)
        val input = if (testOnly) null else readInput(day)

        // Helper function for running parts
        fun runPart(partName: String, solve: (List<String>) -> Any) {
            println("[$partName]")
            println(solve(testInput))
            if (!testOnly && input != null) {
                println()
                println(solve(input))
            }
            println()
        }

        // Solve parts
        runPart("PART 1", ::part1)
        runPart("PART 2", ::part2)
    }
}