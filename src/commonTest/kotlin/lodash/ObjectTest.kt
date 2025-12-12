package lodash

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

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
}
