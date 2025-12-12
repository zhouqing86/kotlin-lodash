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

fun pick(obj: Map<*, Any?>, vararg keys: String): Map<String, Any?> {
    @Suppress("UNCHECKED_CAST")
    val stringMap = obj as Map<String, Any?>
    return buildMap {
        for (key in keys) {
            if (stringMap.containsKey(key)) {
                put(key, stringMap[key])
            }
        }
    }
}

fun pickBy(obj: Map<*, Any?>, predicate: (String, Any?) -> Boolean): Map<String, Any?> {
    @Suppress("UNCHECKED_CAST")
    val stringMap = obj as Map<String, Any?>
    return buildMap {
        for ((key, value) in stringMap) {
            if (predicate(key.toString(), value)) {
                put(key.toString(), value)
            }
        }
    }
}

fun omit(obj: Map<*, Any?>, vararg keys: String): Map<String, Any?> {
    @Suppress("UNCHECKED_CAST")
    val stringMap = obj as Map<String, Any?>
    val keysToOmit = keys.toSet()
    return buildMap {
        for ((key, value) in stringMap) {
            if (key !in keysToOmit) {
                put(key.toString(), value)
            }
        }
    }
}

fun omitBy(obj: Map<*, Any?>, predicate: (String, Any?) -> Boolean): Map<String, Any?> {
    @Suppress("UNCHECKED_CAST")
    val stringMap = obj as Map<String, Any?>
    return buildMap {
        for ((key, value) in stringMap) {
            if (!predicate(key.toString(), value)) {
                put(key.toString(), value)
            }
        }
    }
}

fun keys(obj: Any?): List<String> = when (obj) {
    is Map<*, *> -> obj.keys.map { it.toString() }
    else -> emptyList()
}

fun values(obj: Any?): List<Any?> = when (obj) {
    is Map<*, *> -> obj.values.toList()
    else -> emptyList()
}

fun entries(obj: Any?): List<Pair<String, Any?>> = when (obj) {
    is Map<*, *> -> obj.map { it.key.toString() to it.value }
    else -> emptyList()
}

fun mapValues(
    obj: Map<*, Any?>,
    transform: (String, Any?) -> Any?
): Map<String, Any?> {
    @Suppress("UNCHECKED_CAST")
    val stringMap = obj as Map<String, Any?>
    return buildMap {
        for ((key, value) in stringMap) {
            put(key.toString(), transform(key.toString(), value))
        }
    }
}

fun mapKeys(
    obj: Map<*, Any?>,
    transform: (String, Any?) -> String
): Map<String, Any?> {
    @Suppress("UNCHECKED_CAST")
    val stringMap = obj as Map<String, Any?>
    return buildMap {
        for ((key, value) in stringMap) {
            put(transform(key.toString(), value), value)
        }
    }
}

fun has(obj: Any?, path: String): Boolean {
    if (obj == null || path.isEmpty()) return false

    val keys = parsePath(path)
    var current: Any? = obj

    for (key in keys) {
        current = when {
            current == null -> return false
            current is Map<*, *> -> {
                if (current.containsKey(key)) {
                    current[key]
                } else {
                    return false
                }
            }
            current is List<*> -> {
                val index = key.toIntOrNull()
                if (index != null && index >= 0 && index < current.size) {
                    current[index]
                } else {
                    return false
                }
            }
            else -> return false
        }
    }

    return true
}

fun invert(obj: Map<*, Any?>): Map<String, String> {
    @Suppress("UNCHECKED_CAST")
    val stringMap = obj as Map<String, Any?>
    return buildMap {
        for ((key, value) in stringMap) {
            put(value.toString(), key)
        }
    }
}

fun invertBy(obj: Map<*, Any?>): Map<String, List<String>> {
    @Suppress("UNCHECKED_CAST")
    val stringMap = obj as Map<String, Any?>
    val grouped = mutableMapOf<String, MutableList<String>>()
        for ((key, value) in stringMap) {
            val valueKey = value.toString()
        grouped.getOrPut(valueKey) { mutableListOf() }.add(key)
        }
    return grouped.mapValues { it.value.toList() }
}

