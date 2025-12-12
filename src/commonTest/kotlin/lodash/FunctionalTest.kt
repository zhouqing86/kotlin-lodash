package lodash

import kotlin.test.Test
import kotlin.test.assertEquals

class FunctionalTest {
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
}
