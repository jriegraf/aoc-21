package day3

import day3.Rating.*
import readInput

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
    if (numbers.map { s -> s[n] }.count { x -> x == '0' } > numbers.size / 2) '0' else '1'

fun getRating(r: Rating, numbers: List<String>): String {
    val nums = numbers.toMutableList()
    for (n in 0 until nums.size) {
        val gamma = getGamma(nums, n)
        nums.removeIf { x -> (x[n] == gamma) == (r == CO2Scrubber) }
        if (nums.size == 1) return nums[0]
    }
    return nums[0]
}

fun invert(s: String): String = s.replace('0', 'X').replace('1', '0').replace('X', '1')

fun toInt(s: String): Int = s.toInt(2)

enum class Rating { OxygenGenerator, CO2Scrubber }
