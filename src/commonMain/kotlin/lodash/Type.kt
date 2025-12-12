package lodash

/**
 * Type checking utility functions
 */

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