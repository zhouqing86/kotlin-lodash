package lodash

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TypeTest {
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
    fun `isNull - returns true for null values`() {
        assertTrue(isNull(null))
    }
    
    @Test
    fun `isNull - returns false for non-null values`() {
        assertFalse(isNull(""))
        assertFalse(isNull("hello"))
        assertFalse(isNull(0))
        assertFalse(isNull(42))
        assertFalse(isNull(emptyList<Int>()))
        assertFalse(isNull(listOf(1, 2, 3)))
        assertFalse(isNull(emptyMap<String, Int>()))
        assertFalse(isNull(mapOf("key" to "value")))
        assertFalse(isNull(false))
        assertFalse(isNull(true))
    }
    
    @Test
    fun `isNotNull - returns false for null values`() {
        assertFalse(isNotNull(null))
    }
    
    @Test
    fun `isNotNull - returns true for non-null values`() {
        assertTrue(isNotNull(""))
        assertTrue(isNotNull("hello"))
        assertTrue(isNotNull(0))
        assertTrue(isNotNull(42))
        assertTrue(isNotNull(emptyList<Int>()))
        assertTrue(isNotNull(listOf(1, 2, 3)))
        assertTrue(isNotNull(emptyMap<String, Int>()))
        assertTrue(isNotNull(mapOf("key" to "value")))
        assertTrue(isNotNull(false))
        assertTrue(isNotNull(true))
    }
}
