package lodash

/**
 * Object utility functions
 */

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
