package lodash

import kotlin.collections.get

fun <T> chunk(list: List<T>, size: Int): List<List<T>> {
    require(size > 0) { "Size must be positive" }
    if (list.isEmpty()) return emptyList()

    return (0..list.lastIndex step size).map { start ->
        val end = (start + size).coerceAtMost(list.size)
        list.subList(start, end)
    }
}

fun <T> chunk(array: Array<T>, size: Int): List<List<T>> = chunk(array.toList(), size)

fun <T> compact(list: List<T?>): List<T> = list.filterNotNull().filter {
    when (it) {
        is Number -> it.toDouble() != 0.0
        is CharSequence -> it.isNotEmpty()
        is Boolean -> it
        else -> true
    }
}

fun <T> concat(vararg elements: Any?): List<T> = elements.flatMap {
    when (it) {
        is Array<*> -> it.toList()
        is Collection<*> -> it
        else -> listOf(it)
    }
} as List<T>

fun <T> difference(base: List<T>, vararg exclude: List<T>): List<T> {
    if (exclude.isEmpty()) return base.toList()
    val excludedSet = exclude.flatMap { it }.toSet()
    return base.filter { it !in excludedSet }
}

fun <T> differenceBy(
    base: List<T>,
    vararg exclude: List<T>,
    transform: (T) -> Any?
): List<T> = base.filter { item ->
    val key = transform(item)
    exclude.none { list -> list.any { transform(it) == key } }
}

fun <T> drop(list: List<T>, n: Int = 1): List<T> = list.drop(n)
fun <T> dropRight(list: List<T>, n: Int = 1): List<T> = list.dropLast(n)
fun <T> take(list: List<T>, n: Int = 1): List<T> = list.take(n)
fun <T> takeRight(list: List<T>, n: Int = 1): List<T> = list.takeLast(n)

fun <T> uniq(list: List<T>): List<T> = list.distinct()
fun <T> uniqBy(list: List<T>, selector: (T) -> Any?): List<T> = list.distinctBy(selector)

fun <T> flattenDeep(list: List<Any?>): List<T> = buildList {
    fun recurse(item: Any?) {
        when (item) {
            is List<*> -> item.forEach { recurse(it) }
            is Array<*> -> item.forEach { recurse(it) }
            null -> Unit
            else -> add(item as T)
        }
    }
    list.forEach { recurse(it) }
}

fun camelCase(str: String): String =
    str.split(Regex("[^a-zA-Z0-9]+"))
        .filter { it.isNotEmpty() }
        .joinToString("") { it.lowercase().replaceFirstChar { c -> c.uppercase() } }
        .replaceFirstChar { it.lowercase() }

fun kebabCase(str: String): String =
    str.replace(Regex("([a-z0-9])([A-Z])"), "$1-$2")
        .replace(Regex("\\s+"), "-")
        .lowercase()

fun snakeCase(str: String): String =
    str.replace(Regex("([a-z0-9])([A-Z])"), "$1_$2")
        .replace(Regex("\\s+"), "_")
        .lowercase()

fun upperFirst(str: String): String =
    if (str.isEmpty()) str else str.first().uppercase() + str.drop(1)

fun lowerFirst(str: String): String =
    if (str.isEmpty()) str else str.first().lowercase() + str.drop(1)

fun isEmpty(value: Any?): Boolean = when (value) {
    null -> true
    is String -> value.isEmpty()
    is Collection<*> -> value.isEmpty()
    is Map<*, *> -> value.isEmpty()
    is Array<*> -> value.isEmpty()
    else -> false
}

fun isNotEmpty(value: Any?): Boolean = !isEmpty(value)

fun isNull(value: Any?): Boolean = value == null
fun isNotNull(value: Any?): Boolean = value != null

fun clamp(number: Int, lower: Int, upper: Int): Int = number.coerceIn(lower, upper)
fun clamp(number: Long, lower: Long, upper: Long): Long = number.coerceIn(lower, upper)
fun clamp(number: Double, lower: Double, upper: Double): Double = number.coerceIn(lower, upper)

fun inRange(number: Int, start: Int, end: Int): Boolean = number in start until end
fun random(lower: Int = 0, upper: Int = 1): Int = (lower..upper).random()

fun times(n: Int, block: (Int) -> Unit) {
    repeat(n) { block(it) }
}

inline fun <T> times(n: Int, block: (Int) -> T): List<T> =
    List(n) { block(it) }

fun range(start: Int, end: Int): List<Int> = (start until end).toList()
fun range(start: Int, endExclusive: Int, step: Int): List<Int> =
    generateSequence(start) { it + step }.takeWhile { it < endExclusive }.toList()

fun padEnd(string: String, length: Int, padChar: Char = ' '): String =
    string.padEnd(length, padChar)

fun padStart(string: String, length: Int, padChar: Char = ' '): String =
    string.padStart(length, padChar)

fun truncate(string: String, length: Int = 30, omission: String = "..."): String {
    return if (string.length <= length) string else string.take(length - omission.length) + omission
}

fun <T> cloneDeep(value: T): T {
    @Suppress("UNCHECKED_CAST")
    return when (value) {
        is List<*> -> value.map { cloneDeep(it) } as T
        is Array<*> -> value.map { cloneDeep(it) }.toTypedArray() as T
        is Map<*, *> -> value.mapValues { cloneDeep(it.value) } as T
        is Set<*> -> value.map { cloneDeep(it) }.toSet() as T
        else -> value
    }
}

fun trim(s: String?, chars: String? = null): String {
    if (s.isNullOrEmpty()) return ""
    val actualChars = chars ?: " \t\n\r\u000C\u0020\u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007F\u2028\u2029\u202F\u205F\u3000"
    val set = actualChars.toSet()

    var start = 0
    var end = s.lastIndex

    while (start <= end && s[start] in set) start++
    while (end >= start && s[end] in set) end--

    return if (start > end) "" else s.substring(start, end + 1)
}

fun get(obj: Any?, path: String, defaultValue: Any? = null): Any? {
    if (obj == null || path.isEmpty()) return defaultValue

    val keys = parsePath(path)
    var current: Any? = obj

    for (key in keys) {
        current = when {
            current == null -> return defaultValue
            current is Map<*, *> -> current[key]
            current is List<*> -> {
                val index = key.toIntOrNull()
                if (index != null && index >= 0 && index < current.size) {
                    current[index]
                } else {
                    return defaultValue
                }
            }
            else -> return defaultValue
        }
    }

    return current ?: defaultValue
}

private fun parsePath(path: String): List<String> {
    val result = mutableListOf<String>()
    val regex = Regex("""[^.\[\]]+|\[(\d+)\]|\['([^']+)'\]|\["([^"]+)"\]""")

    regex.findAll(path).forEach { match ->
        val value = match.value
        when {
            value.startsWith("[") && value.endsWith("]") && !value.contains("'") && !value.contains("\"") -> {
                result.add(value.substring(1, value.length - 1))
            }
            value.startsWith("['") && value.endsWith("']") -> {
                result.add(value.substring(2, value.length - 2))
            }
            value.startsWith("[\"") && value.endsWith("\"]") -> {
                result.add(value.substring(2, value.length - 2))
            }
            value.isNotEmpty() -> {
                result.add(value)
            }
        }
    }

    return result
}


