package lodash.utils

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class LodashTest {

    @Test
    fun `chunk - basic usage`() {
        assertEquals(
            listOf(listOf(1, 2), listOf(3, 4)),
            chunk(listOf(1, 2, 3, 4), 2)
        )
        assertEquals(
            listOf(listOf(1, 2, 3), listOf(4)),
            chunk(listOf(1, 2, 3, 4), 3)
        )
        assertEquals(emptyList(), chunk(emptyList<Int>(), 5))
    }

    @Test
    fun `compact - removes falsy values`() {
        assertEquals(
            listOf(1, 2, "hello", true),
            compact(listOf(0, 1, false, 2, "", "hello", null, true))
        )
    }

    @Test
    fun `concat - flattens arrays and values`() {
        val result = concat<Int>(listOf(1), 2, listOf(3, 4), arrayOf(5))
        assertEquals(listOf(1, 2, 3, 4, 5), result)
    }

    @Test
    fun `difference - excludes values from other lists`() {
        assertEquals(
            listOf(1, 2),
            difference(listOf(1, 2, 3), listOf(3, 4), listOf(5))
        )
        assertEquals(listOf(1, 2, 3), difference(listOf(1, 2, 3)))
    }

    @Test
    fun `differenceBy - basic usage with transform function`() {
        val list1 = listOf(2.1, 1.2, 3.3)
        val list2 = listOf(4.4, 2.5)
        assertEquals(
            listOf(1.2, 3.3),
            differenceBy(list1, list2) { kotlin.math.floor(it).toInt() }
        )
    }

    @Test
    fun `differenceBy - filters by property selector`() {
        data class User(val id: Int, val name: String)
        val users1 = listOf(User(1, "Alice"), User(2, "Bob"), User(3, "Charlie"))
        val users2 = listOf(User(2, "Robert"), User(4, "David"))

        assertEquals(
            listOf(User(1, "Alice"), User(3, "Charlie")),
            differenceBy(users1, users2) { it.id }
        )
    }

    @Test
    fun `differenceBy - multiple exclude lists`() {
        val base = listOf(1.1, 2.2, 3.3, 4.4, 5.5)
        val exclude1 = listOf(2.5, 3.5)
        val exclude2 = listOf(4.5)

        assertEquals(
            listOf(1.1, 5.5),
            differenceBy(base, exclude1, exclude2) { kotlin.math.floor(it).toInt() }
        )
    }

    @Test
    fun `differenceBy - returns all items when no exclude lists provided`() {
        val base = listOf("apple", "banana", "cherry")
        assertEquals(
            listOf("apple", "banana", "cherry"),
            differenceBy(base) { it.length }
        )
    }

    @Test
    fun `differenceBy - returns empty list when all items excluded`() {
        val base = listOf(1, 2, 3)
        val exclude = listOf(1, 2, 3)
        assertEquals(
            emptyList(),
            differenceBy(base, exclude) { it }
        )
    }

    @Test
    fun `differenceBy - handles empty base list`() {
        assertEquals(
            emptyList(),
            differenceBy(emptyList<Int>(), listOf(1, 2)) { it }
        )
    }

    @Test
    fun `differenceBy - handles empty exclude lists`() {
        val base = listOf(1, 2, 3)
        assertEquals(
            listOf(1, 2, 3),
            differenceBy(base, emptyList()) { it }
        )
    }

    @Test
    fun `differenceBy - works with string length comparison`() {
        val base = listOf("a", "bb", "ccc", "dddd")
        val exclude = listOf("xx", "yyy")

        assertEquals(
            listOf("a", "dddd"),
            differenceBy(base, exclude) { it.length }
        )
    }

    @Test
    fun `differenceBy - handles null transform results`() {
        data class Item(val value: Int?)
        val base = listOf(Item(1), Item(null), Item(2))
        val exclude = listOf(Item(null))

        assertEquals(
            listOf(Item(1), Item(2)),
            differenceBy(base, exclude) { it.value }
        )
    }

    @Test
    fun `differenceBy - preserves original order`() {
        val base = listOf(5, 3, 1, 4, 2)
        val exclude = listOf(2, 4)

        assertEquals(
            listOf(5, 3, 1),
            differenceBy(base, exclude) { it }
        )
    }

    @Test
    fun `differenceBy - handles duplicate values in base list`() {
        val base = listOf(1, 2, 2, 3, 3, 3)
        val exclude = listOf(2)

        assertEquals(
            listOf(1, 3, 3, 3),
            differenceBy(base, exclude) { it }
        )
    }

    @Test
    fun `drop and dropRight`() {
        assertEquals(listOf(3, 4, 5), drop(listOf(1, 2, 3, 4, 5), 2))
        assertEquals(listOf(1, 2, 3), dropRight(listOf(1, 2, 3, 4, 5), 2))
    }

    @Test
    fun `take and takeRight`() {
        assertEquals(listOf(1, 2), take(listOf(1, 2, 3, 4, 5), 2))
        assertEquals(listOf(4, 5), takeRight(listOf(1, 2, 3, 4, 5), 2))
    }

    @Test
    fun `uniq and uniqBy`() {
        assertEquals(listOf(1, 2), uniq(listOf(1, 1, 2, 2)))
        assertEquals(
            listOf("a", "aa", "aaa"),
            uniqBy(listOf("a", "aa", "de", "aaa"), String::length)
        )
    }

    @Test
    fun `flattenDeep`() {
        val nested = listOf(1, listOf(2, listOf(3, 4), 5), 6)
        assertEquals(listOf(1, 2, 3, 4, 5, 6), flattenDeep<Any>(nested))
    }

    @Test
    fun `get - map and list access`() {
        val data = mapOf(
            "user" to mapOf("name" to "张三", "age" to 18),
            "tags" to listOf("kotlin", "multiplatform")
        )

        assertEquals("张三", get(data, "user.name"))
        assertEquals(18, get(data, "user.age"))
        assertEquals("multiplatform", get(data, "tags[1]"))
        assertEquals("default", get(data, "not.exist", "default"))
        assertNull(get(data, "user.email"))
    }

    @Test
    fun `get - supports dot and bracket notation mixed`() {
        val obj = mapOf("a" to mapOf("b" to listOf(mapOf("c" to 42))))
        assertEquals(42, get(obj, "a.b[0].c"))
        assertEquals(42, get(obj, "a.b[0]['c']"))
    }

    @Test
    fun `camelCase, kebabCase, snakeCase`() {
        assertEquals("fooBar", camelCase("Foo Bar"))
        assertEquals("fooBar", camelCase("__FOO_BAR__"))
        assertEquals("foo-bar-baz", kebabCase("fooBarBaz"))
        assertEquals("foo_bar_baz", snakeCase("fooBarBaz"))
    }

    @Test
    fun `isEmpty and isNotEmpty`() {
        assertTrue(isEmpty(null))
        assertTrue(isEmpty(""))
        assertTrue(isEmpty(emptyList<Int>()))
        assertTrue(isEmpty(emptyMap<String, Int>()))
        assertFalse(isEmpty("hello"))
        assertFalse(isEmpty(listOf(1)))
        assertTrue(isNotEmpty(listOf(1)))
    }

    @Test
    fun `clamp`() {
        assertEquals(5, clamp(10, 0, 5))
        assertEquals(0, clamp(-5, 0, 10))
        assertEquals(3.5, clamp(3.5, 3.0, 4.0))
    }

    @Test
    fun `random and inRange`() {
        repeat(100) {
            val r = random(1, 10)
            assertTrue(r in 1..10)
        }
        assertTrue(inRange(5, 0, 10))
        assertFalse(inRange(10, 0, 10))
    }

    @Test
    fun `times - returns list`() {
        val list = times<Int>(3) { it * 2 }
        assertEquals(listOf(0, 2, 4), list)
    }

    @Test
    fun `range`() {
        assertEquals(listOf(0, 1, 2, 3), range(0, 4))
        assertEquals(listOf(0, 2, 4), range(0, 6, 2))
    }

    @Test
    fun `padStart and padEnd`() {
        assertEquals("   42", padStart("42", 5))
        assertEquals("42---", padEnd("42", 5, '-'))
    }

    @Test
    fun `truncate`() {
        assertEquals("Hello...", truncate("Hello world", 8))
        assertEquals("Hi", truncate("Hi", 10))
        assertEquals("Hello!!!", truncate("Hello world", 8, "!!!"))
    }

    @Test
    fun `cloneDeep - creates real deep copy`() {
        val original: Map<String, Any> = mapOf(
            "name" to "张三",
            "tags" to mutableListOf("A", "B"),
            "nested" to mapOf("x" to 1)
        )

        // 方案1：加类型注解（最推荐）
        val copy: Map<String, Any> = cloneDeep(original)

        // 修改副本
        (copy["tags"] as MutableList<String>) += "C"
        (copy["nested"] as MutableMap<String, Int>)["x"] = 999

        // 断言原数据完全不受影响
        assertEquals("张三", original["name"])
        assertEquals(listOf("A", "B"), original["tags"])
        assertEquals(1, (original["nested"] as Map<*, *>)["x"])

        // 副本被修改了
        assertEquals(listOf("A", "B", "C"), copy["tags"])
        assertEquals(999, (copy["nested"] as Map<*, *>)["x"])
    }

    @Test
    fun `trim - removes whitespace from both sides by default`() {
        assertEquals("hello", trim("   hello   "))
        assertEquals("hello", trim("\n\r\t hello \n"))
        assertEquals("hello", trim("hello"))
        assertEquals("", trim("     "))
        assertEquals("", trim(""))
    }

    @Test
    fun `trim - removes specified characters from both sides`() {
        assertEquals("hello", trim("###hello###", "#"))
        assertEquals("hello world", trim("___hello world___", "_"))
        assertEquals("abc", trim("xxxabcxxx", "x"))
        assertEquals("middle", trim("---middle---", "-"))
    }

    @Test
    fun `trim - handles mixed characters to trim`() {
        assertEquals("hello", trim("###   hello   ###", "# "))
        assertEquals("kotlin", trim("***kotlin!!!", "*!"))
        assertEquals("abc123", trim("xyzabc123xyz", "xyz"))
    }

    @Test
    fun `trim - returns empty string when all characters are trimmed`() {
        assertEquals("", trim("aaaaa", "a"))
        assertEquals("", trim("   ", " "))
        assertEquals("", trim("xxx", "x"))
        assertEquals("", trim("?!?", "?!"))
    }

    @Test
    fun `trim - works correctly when only one side needs trimming`() {
        assertEquals("hello   ", trim("###hello   ", "#"))
        assertEquals("   hello", trim("   hello###", "#"))
        assertEquals("abc", trim("abcxxx", "x"))
        assertEquals("abc", trim("yyyabc", "y"))
    }

    @Test
    fun `trim - handles null inputs gracefully`() {
        assertEquals("", trim(null))
        assertEquals("", trim(null, "#"))
        assertEquals("hello", trim("hello", null))
        assertEquals("hello", trim("   hello   ", null))
    }

    @Test
    fun `trim - works with empty or blank chars parameter`() {
        assertEquals("   hello   ", trim("   hello   ", ""))
        assertEquals("hello", trim("   hello   ", "   "))
        assertEquals("hello", trim("hello", " \t\n"))
    }

    @Test
    fun `trim - supports unicode characters`() {
        assertEquals("你好", trim("★★★你好★★★", "★"))
        assertEquals("こんにちは", trim("　　こんにちは　　"))
        assertEquals("abc", trim("¡¡¡abc¡¡¡", "¡"))
    }

    @Test
    fun `trim - no crash on extreme edge cases`() {
        assertEquals("", trim("", "x"))
        assertEquals("a", trim("a", ""))
        assertEquals("abc", trim("abc", "xyz"))
        assertEquals("   ", trim("   ", ""))
    }

    @Test
    fun `upperFirst - capitalizes first character`() {
        assertEquals("Hello", upperFirst("hello"))
        assertEquals("World", upperFirst("world"))
        assertEquals("A", upperFirst("a"))
    }

    @Test
    fun `upperFirst - handles already capitalized strings`() {
        assertEquals("Hello", upperFirst("Hello"))
        assertEquals("HELLO", upperFirst("HELLO"))
    }

    @Test
    fun `upperFirst - handles empty and single character strings`() {
        assertEquals("", upperFirst(""))
        assertEquals("A", upperFirst("A"))
    }

    @Test
    fun `upperFirst - preserves rest of string unchanged`() {
        assertEquals("HelloWorld", upperFirst("helloWorld"))
        assertEquals("Test123", upperFirst("test123"))
    }

    @Test
    fun `lowerFirst - lowercases first character`() {
        assertEquals("hello", lowerFirst("Hello"))
        assertEquals("world", lowerFirst("World"))
        assertEquals("a", lowerFirst("A"))
    }

    @Test
    fun `lowerFirst - handles already lowercased strings`() {
        assertEquals("hello", lowerFirst("hello"))
        assertEquals("hELLO", lowerFirst("HELLO"))
    }

    @Test
    fun `lowerFirst - handles empty and single character strings`() {
        assertEquals("", lowerFirst(""))
        assertEquals("a", lowerFirst("a"))
    }

    @Test
    fun `lowerFirst - preserves rest of string unchanged`() {
        assertEquals("helloWorld", lowerFirst("HelloWorld"))
        assertEquals("tEST123", lowerFirst("TEST123"))
    }
}
