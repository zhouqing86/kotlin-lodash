package lodash

/**
 * Array and List utility functions
 */

fun <T> chunk(list: List<T>, size: Int): List<List<T>> {
    require(size > 0) { "Size must be greater than 0" }
    return list.chunked(size)
}

fun <T> chunk(array: Array<T>, size: Int): List<List<T>> {
    require(size > 0) { "Size must be greater than 0" }
    return array.toList().chunked(size)
}

fun <T> compact(list: List<T?>): List<T> {
    return list.filterNotNull().filter {
        when (it) {
            is Boolean -> it
            is Number -> it.toDouble() != 0.0
            is String -> it.isNotEmpty()
            else -> true
        }
    }
}

 fun <T> concat(vararg elements: Any?): List<T> = elements.flatMap {
     when (it) {
         is Array<*> -> it.toList()
         is Collection<*> -> it
         else -> listOf(it)
     }
 } as List<T>

fun <T> difference(list: List<T>, vararg excludes: List<T>): List<T> {
    val excludeSet = excludes.flatMap { it }.toSet()
    return list.filter { it !in excludeSet }
}

fun <T> differenceBy(list: List<T>, vararg excludes: List<T>, selector: (T) -> Any?): List<T> {
    val excludeKeys = excludes.flatMap { it }.map(selector).toSet()
    return list.filter { selector(it) !in excludeKeys }
}

fun <T> drop(list: List<T>, n: Int = 1): List<T> = list.drop(n)

fun <T> dropRight(list: List<T>, n: Int = 1): List<T> = list.dropLast(n)

fun <T> take(list: List<T>, n: Int = 1): List<T> = list.take(n)

fun <T> takeRight(list: List<T>, n: Int = 1): List<T> = list.takeLast(n)

fun <T> uniq(list: List<T>): List<T> = list.distinct()

fun <T> uniqBy(list: List<T>, selector: (T) -> Any?): List<T> = list.distinctBy(selector)

@Suppress("UNCHECKED_CAST")
fun <T> flattenDeep(list: List<Any?>): List<T> = buildList {
    fun recurse(item: Any?) {
        when (item) {
            is List<*> -> item.forEach { recurse(it) }
            is Array<*> -> item.forEach { recurse(it) }
            else -> add(item as T)
        }
    }
    list.forEach { recurse(it) }
}
