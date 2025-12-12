package lodash

import kotlin.test.Test
import kotlin.test.assertEquals

class DeepTest {
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
}
