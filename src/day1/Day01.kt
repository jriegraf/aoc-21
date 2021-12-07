package day1

import readInput

const val WINDOW_SIZE: Int = 3

fun main() {

    val testInput = toIntArray(readInput("day1/input.txt"))

    val solutionPartOne = part1(testInput)
    println("increased $solutionPartOne times")
    check(solutionPartOne == 1342)

    val solutionPartTwo = part2(testInput)
    println("increased $solutionPartTwo times")
    check(solutionPartOne == 1378)
}

private fun part1(testInput: IntArray): Int {
    var prevNumber = Int.MAX_VALUE
    var numberIncreased = 0

    testInput.forEach { currentNumber ->
        if (prevNumber < currentNumber) {
            numberIncreased++
        }
        prevNumber = currentNumber
    }
    return numberIncreased
}

private fun part2(input: IntArray): Int {
    var prevNumber = Int.MAX_VALUE
    var numberIncreased = 0

    for (i in WINDOW_SIZE..input.size) {
        val currentNumber = getWindowSum(i, input)
        if (prevNumber < currentNumber) {
            numberIncreased++
        }
        prevNumber = currentNumber
    }
    return numberIncreased
}

private fun toIntArray(strList: List<String>) = strList.map { s -> s.trim().toInt() }.toIntArray()

private fun getWindowSum(index: Int, array: IntArray): Int {
    if (index < WINDOW_SIZE) throw IllegalArgumentException("index must be greater than window size")
    if (array.size < WINDOW_SIZE) throw IllegalArgumentException("array length must be greater than window size")

    return (index - WINDOW_SIZE until index).sumOf { array.get(it) }
}
