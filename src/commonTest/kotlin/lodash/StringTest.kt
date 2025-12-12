package lodash

import kotlin.test.Test
import kotlin.test.assertEquals

class StringTest {
    @Test
    fun `camelCase, kebabCase, snakeCase`() {
        assertEquals("fooBar", camelCase("Foo Bar"))
        assertEquals("fooBar", camelCase("__FOO_BAR__"))
        assertEquals("foo-bar-baz", kebabCase("fooBarBaz"))
        assertEquals("foo_bar_baz", snakeCase("fooBarBaz"))
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
