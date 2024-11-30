package utils

import kotlin.io.path.Path
import kotlin.io.path.readLines

private fun getPathFrom(textFileName: String, day: String) = Path("src/day_$day/$textFileName.txt")

fun readInput(day: String) = getPathFrom("input", day).readLines()

fun readTestInput(day: String) = getPathFrom("test", day).readLines()
