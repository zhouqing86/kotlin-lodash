package lodash

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ObjectTest {
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
    fun `pick - basic usage`() {
        val data = mapOf("a" to 1, "b" to 2, "c" to 3)
        val result = pick(data, "a", "c")
        assertEquals(mapOf("a" to 1, "c" to 3), result)
    }

    @Test
    fun `pick - ignores missing keys`() {
        val data = mapOf("a" to 1, "b" to 2)
        val result = pick(data, "a", "c")
        assertEquals(mapOf("a" to 1), result)
    }

    @Test
    fun `pickBy - basic usage`() {
        val data = mapOf("a" to 1, "b" to 2, "c" to 3)
        val result = pickBy(data) { key, value -> value as Int > 1 }
        assertEquals(mapOf("b" to 2, "c" to 3), result)
    }

    @Test
    fun `omit - basic usage`() {
        val data = mapOf("a" to 1, "b" to 2, "c" to 3)
        val result = omit(data, "b")
        assertEquals(mapOf("a" to 1, "c" to 3), result)
    }

    @Test
    fun `omit - ignores missing keys`() {
        val data = mapOf("a" to 1, "b" to 2)
        val result = omit(data, "c")
        assertEquals(data, result)
    }

    @Test
    fun `omitBy - basic usage`() {
        val data = mapOf("a" to 1, "b" to 2, "c" to 3)
        val result = omitBy(data) { key, value -> (value as Int) < 3 }
        assertEquals(mapOf("c" to 3), result)
    }

    @Test
    fun `keys - returns keys from map`() {
        val data = mapOf("a" to 1, "b" to 2)
        val result = keys(data)
        assertEquals(listOf("a", "b"), result)
    }

    @Test
    fun `keys - returns empty list for non-map`() {
        val result = keys("not a map")
        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `values - returns values from map`() {
        val data = mapOf("a" to 1, "b" to 2)
        val result = values(data)
        assertEquals(listOf(1, 2), result)
    }

    @Test
    fun `values - returns empty list for non-map`() {
        val result = values("not a map")
        assertEquals(emptyList<Any?>(), result)
    }

    @Test
    fun `has - returns true for existing path`() {
        val data = mapOf("user" to mapOf("name" to "张三", "age" to 18))
        assertTrue(has(data, "user.name"))
        assertTrue(has(data, "user.age"))
        assertTrue(has(data, "user"))
    }

    @Test
    fun `has - returns false for non-existing path`() {
        val data = mapOf("user" to mapOf("name" to "张三"))
        assertFalse(has(data, "user.age"))
        assertFalse(has(data, "user.email"))
        assertFalse(has(data, "not.exist"))
    }

    @Test
    fun `mapValues - applies transformation to values`() {
        val data = mapOf("a" to 1, "b" to 2, "c" to 3)
        val result = mapValues(data) { _, value -> (value as Int) * 2 }
        assertEquals(mapOf("a" to 2, "b" to 4, "c" to 6), result)
    }

    @Test
    fun `mapKeys - applies transformation to keys`() {
        val data = mapOf("a" to 1, "b" to 2)
        val result = mapKeys(data) { key, _ -> key.toUpperCase() }
        assertEquals(mapOf("A" to 1, "B" to 2), result)
    }

    @Test
    fun `entries - returns key-value pairs from map`() {
        val data = mapOf("a" to 1, "b" to 2)
        val result = entries(data)
        assertEquals(listOf("a" to 1, "b" to 2), result)
    }

    @Test
    fun `entries - returns empty list for non-map`() {
        val result = entries("not a map")
        assertEquals(emptyList<Pair<String, Any?>>(), result)
    }

    @Test
    fun `invert - basic usage`() {
        val data = mapOf("a" to "x", "b" to "y", "c" to "z")
        val result = invert(data)
        assertEquals(mapOf("x" to "a", "y" to "b", "z" to "c"), result)
    }

    @Test
    fun `invert - empty map`() {
        val result = invert(emptyMap<String, Any?>())
        assertEquals(emptyMap<String, String>(), result)
    }

    @Test
    fun `invert - converts values to strings`() {
        val data = mapOf("a" to 1, "b" to 2, "c" to 3)
        val result = invert(data)
        assertEquals(mapOf("1" to "a", "2" to "b", "3" to "c"), result)
    }

    @Test
    fun `invert - last value wins on duplicate values`() {
        val data = mapOf("a" to "x", "b" to "x", "c" to "y")
        val result = invert(data)
        assertEquals(mapOf("x" to "b", "y" to "c"), result)
    }

    @Test
    fun `invertBy - basic usage`() {
        val data = mapOf("a" to "x", "b" to "y", "c" to "x")
        val result = invertBy(data)
        assertEquals(mapOf("x" to listOf("a", "c"), "y" to listOf("b")), result)
    }

    @Test
    fun `invertBy - empty map`() {
        val result = invertBy(emptyMap<String, Any?>())
        assertEquals(emptyMap<String, List<String>>(), result)
    }

    @Test
    fun `invertBy - converts values to strings`() {
        val data = mapOf("a" to 1, "b" to 2, "c" to 1)
        val result = invertBy(data)
        assertEquals(mapOf("1" to listOf("a", "c"), "2" to listOf("b")), result)
    }

    @Test
    fun `invertBy - groups all keys for each value`() {
        val data = mapOf("x" to "status", "y" to "status", "z" to "type", "w" to "status")
        val result = invertBy(data)
        assertEquals(mapOf("status" to listOf("x", "y", "w"), "type" to listOf("z")), result)
    }
}
