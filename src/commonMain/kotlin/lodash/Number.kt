package lodash

import kotlin.random.Random

/**
 * Number utility functions
 */

fun clamp(number: Int, lower: Int, upper: Int): Int {
    require(lower <= upper) { "Lower bound must be less than or equal to upper bound" }
    return when {
        number < lower -> lower
        number > upper -> upper
        else -> number
    }
}

fun clamp(number: Long, lower: Long, upper: Long): Long {
    require(lower <= upper) { "Lower bound must be less than or equal to upper bound" }
    return when {
        number < lower -> lower
        number > upper -> upper
        else -> number
    }
}

fun clamp(number: Double, lower: Double, upper: Double): Double {
    require(lower <= upper) { "Lower bound must be less than or equal to upper bound" }
    return when {
        number < lower -> lower
        number > upper -> upper
        else -> number
    }
}

fun inRange(number: Int, start: Int, end: Int): Boolean {
    val (lower, upper) = if (start < end) start to end else end to start
    return number >= lower && number < upper
}

fun random(min: Int = 0, max: Int = 1): Int {
    return Random.nextInt(min, max + 1)
}