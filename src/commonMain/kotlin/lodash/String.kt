package lodash

/**
 * String utility functions
 */

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

fun upperFirst(str: String): String {
    if (str.isEmpty()) return str
    return str.replaceFirstChar { it.uppercase() }
}

fun lowerFirst(str: String): String {
    if (str.isEmpty()) return str
    return str.replaceFirstChar { it.lowercase() }
}

fun padStart(str: String, length: Int, char: Char = ' '): String {
    return str.padStart(length, char)
}

fun padEnd(str: String, length: Int, char: Char = ' '): String {
    return str.padEnd(length, char)
}

fun truncate(str: String, length: Int = 30, omission: String = "..."): String {
    if (str.length <= length) return str
    val end = maxOf(0, length - omission.length)
    return str.take(end) + omission
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
