package lodash

/**
 * Functional programming utilities
 */

fun <T> times(n: Int, iteratee: (Int) -> T): List<T> {
    require(n >= 0) { "n must be non-negative" }
    return List(n) { iteratee(it) }
}

fun times(n: Int, iteratee: (Int) -> Unit) {
    require(n >= 0) { "n must be non-negative" }
    repeat(n) { iteratee(it) }
}

fun range(start: Int, end: Int, step: Int = 1): List<Int> {
    require(step != 0) { "Step must not be zero" }
    return if (step > 0) {
        (start until end step step).toList()
    } else {
        (start downTo end + 1 step -step).toList()
    }
}