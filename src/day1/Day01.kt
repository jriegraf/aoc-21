package day1

import readInput

fun main() {

    val testInput = readInput("day1/input.txt")
    var numberIncreased = 0
    var prevNumber = Int.MAX_VALUE

    testInput.forEach { line ->
        val currentNumber = line.trim().toInt()
        if (prevNumber < currentNumber) {
            numberIncreased++
        }
        prevNumber = currentNumber
    }

    println("increased $numberIncreased times")
    check(numberIncreased == 1342)
}
