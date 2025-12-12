package lodash

/**
 * Deep collection operations
 */

@Suppress("UNCHECKED_CAST")
fun <T> cloneDeep(value: T): T = when (value) {
    is List<*> -> value.map { cloneDeep(it) } as T
    is Array<*> -> value.map { cloneDeep(it) }.toTypedArray() as T
    is Map<*, *> -> value.mapValues { cloneDeep(it.value) } as T
    is Set<*> -> value.map { cloneDeep(it) }.toSet() as T
    else -> value
}