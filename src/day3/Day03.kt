package day3

import day3.Rating.*
import readInput

private const val ZERO = '0'
private const val ONE = '1'

enum class Rating { OxygenGenerator, CO2Scrubber }

fun main() {
    val numbers = readInput("day3/input.txt")
    println("power consumption: " + part1(numbers))
    println("life support rating: " + part2(numbers))
}

fun part1(numbers: List<String>): Int {
    val gamma = numbers[0].mapIndexed { i, _ -> getGamma(numbers, i) }.joinToString(separator = "")
    val epsilon = invert(gamma)
    return toInt(gamma) * toInt(epsilon)
}

fun part2(numbers: List<String>): Int {
    return toInt(getRating(OxygenGenerator, numbers)) * toInt(getRating(CO2Scrubber, numbers))
}

fun getGamma(numbers: List<String>, n: Int): Char =
    if (numbers.map { s -> s[n] }.count { x -> x == ZERO } > numbers.size / 2) ZERO else ONE

fun getRating(r: Rating, numbers: List<String>): String {
    val nums = numbers.toMutableList()
    for (n in 0 until nums.size) {
        nums.removeIf { x -> (x[n] == getGamma(nums, n)) == (r == CO2Scrubber) }
        if (nums.size == 1) break
    }
    return nums[0]
}

fun invert(s: String): String = s.map { n -> if (n == ZERO) ONE else ZERO }.joinToString(separator = "")

fun toInt(s: String): Int = s.toInt(2)
