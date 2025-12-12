import lodash.inRange
import lodash.random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NumberTest {
    @Test
    fun `clamp`() {
        assertEquals(5, lodash.clamp(10, 0, 5))
        assertEquals(0, lodash.clamp(-5, 0, 10))
        assertEquals(3.5, lodash.clamp(3.5, 3.0, 4.0))
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
}
