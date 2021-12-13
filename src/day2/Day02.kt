package day2

import day2.Direction.*
import readInput

fun main() {
    val movements = readInput("day2/input.txt").map { x -> Movement.fromString(x) }
    println("part1: " + part1(movements))
    println("part2: " + part2(movements))
}

fun part1(movements: List<Movement>): Int {
    var x = 0
    var y = 0

    movements.forEach { movement ->
        when (movement.direction) {
            FORWARD -> x += movement.amount
            UP -> y -= movement.amount
            DOWN -> y += movement.amount
        }
    }
    return x * y
}

fun part2(movements: List<Movement>): Int {
    var x = 0
    var y = 0
    var aim = 0

    movements.forEach { movement ->
        when (movement.direction) {
            FORWARD -> {
                x += movement.amount;
                y += aim * movement.amount
            }
            UP -> aim -= movement.amount
            DOWN -> aim += movement.amount
        }
    }
    return x * y
}


enum class Direction {
    FORWARD, UP, DOWN;
}

data class Movement(val direction: Direction, val amount: Int) {
    companion object {
        fun fromString(movement: String): Movement {
            return when (movement.substringBefore(" ")) {
                "forward" -> Movement(FORWARD, movement.substringAfter(" ").toInt())
                "up" -> Movement(UP, movement.substringAfter(" ").toInt())
                "down" -> Movement(DOWN, movement.substringAfter(" ").toInt())
                else -> throw IllegalArgumentException("Can not parse movement")
            }
        }
    }
}
