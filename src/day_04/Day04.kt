package day_04

import utils.Puzzle

typealias Coordinate = Pair<Int, Int>

object Day04: Puzzle("04") {

    private fun Coordinate.range(length: Int): List<Coordinate> {
        return listOf(
            this.plus(length, 0),
            this.plus(0, length),
            this.plus(-length, 0),
            this.plus(0, -length),
            this.plus(length, length),
            this.plus(length, -length),
            this.plus(-length, length),
            this.plus(-length, -length),
        )
    }

    private fun Coordinate.adjacentCoordinates(): List<Pair<Coordinate, Coordinate>> {
        return listOf(
            this.plus(-1,-1) to this.plus(1,1), // Top Left coordinate
            this.plus(1,-1) to this.plus(-1,1), // Top Right coordinate
        )
    }

    private fun Coordinate.indicesTo(other: Coordinate, endInclusive: Boolean = false): List<Coordinate> {
        val (x1, y1) = this
        val (x2, y2) = other

        val dx = (x2 - x1).coerceIn(-1, 1)
        val dy = (y2 - y1).coerceIn(-1, 1)

        val result = mutableListOf<Coordinate>()
        var current = this

        while (current != other) {
            result.add(current)
            current = Coordinate(current.first + dx, current.second + dy)
        }

        if(endInclusive) {
            // Include the final coordinate
            result.add(other)
        }

        return result
    }

    fun Coordinate.plus(x: Int, y: Int): Coordinate = Pair(this.first + x, this.second + y)

    fun Coordinate.minus(other: Coordinate): Coordinate = Pair(this.first - other.first, this.second - other.second)

    private fun String.check(value: String): Boolean = this == value || this.reversed() == value

    private fun findXMAS(input: List<String>): Int {
        var founded = 0

        val rows = input.size
        val cols = input[0].length

        input.forEachIndexed { row, line ->
            line.forEachIndexed { col, character ->
                if (character == 'X') {
                    val position: Coordinate = row to col
                    // Get 8 directions coordinate
                    position.range(4).forEach { coordinate ->
                        // The coordinates to the current
                        val indices = position.indicesTo(coordinate)
                        // Get the value according to coords
                        val value = buildString {
                            indices.forEach { (x,y) ->
                                if(x in 0..rows - 1 && y in 0..cols - 1) {
                                    append(input[x][y].toString())
                                }
                            }
                        }
                        // Check is the values is "XMAS"
                        if(value.check("XMAS")) {
                            founded++
                        }
                    }
                }
            }
        }
        return founded
    }

    private fun findMAS(input: List<String>): Int {
        var founded = 0

        val rows = input.size
        val cols = input[0].length

        input.forEachIndexed { row, line ->
            line.forEachIndexed { col, character ->
                if (character == 'A') {
                    val position: Coordinate = row to col
                    var diagonal = 0
                    // Get cross coordinates
                    position.adjacentCoordinates().forEach { (c1,c2) ->
                        val indices = c1.indicesTo(c2,true)
                        // Get the value according to coords
                        val value = buildString {
                            indices.forEach { (x,y) ->
                                if(x in 0..rows - 1 && y in 0..cols - 1) {
                                    append(input[x][y].toString())
                                }
                            }
                        }
                        // Check is the values is "MAS"
                        if(value.check("MAS")) {
                            diagonal++
                        }
                    }
                    // If X-MAS found
                    if(diagonal == 2) {
                        founded++
                    }
                }
            }
        }

        return founded
    }

    override fun part1(input: List<String>): String {
        return findXMAS(input).toString()
    }

    override fun part2(input: List<String>): String {
        return findMAS(input).toString()
    }
}

fun main() {
    Day04.solvePuzzle(testOnly = false)
}