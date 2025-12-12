package lodash

import kotlin.math.floor
import kotlin.test.Test
import kotlin.test.assertEquals

class ListTest {
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
            differenceBy(list1, list2) { floor(it).toInt() }
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
            differenceBy(base, exclude1, exclude2) { floor(it).toInt() }
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
}
