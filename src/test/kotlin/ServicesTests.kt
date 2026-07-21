import io.github.runkang10.compactmono.services.KeyedRegistry
import io.github.runkang10.compactmono.services.ServiceRegistry
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

object ServicesTests {
    @Test
    fun testServiceRegistry() {
        val registry = ServiceRegistry(true)
        registry.add("a string")
        registry.add(2)

        assertEquals(2, registry.getAll().size)
        assertEquals(true, registry.getOrNull<String>() != null)
        assertEquals(true, registry.getOrNull<Int>() != null)

        registry.remove<Int>()

        assertEquals(1, registry.getAll().size)
        assertNull(registry.getOrNull<Int>())

        registry.clear()

        assertEquals(0, registry.getAll().size)
        assertNull(registry.getOrNull<String>())
    }

    @Test
    fun testKeyedRegistry() {
        val registry = KeyedRegistry(true)
        registry.add(1, "primary")
        registry.add(2, "secondary")

        assertEquals(2, registry.getAll().size)
        assertEquals(true, registry.getOrNull<String>() == null)
        assertEquals(1, registry.get<Int>("primary"))
        assertEquals(2, registry.get<Int>("secondary"))

        registry.remove<Int>("primary")

        assertEquals(1, registry.getAll().size)
        assertNull(registry.getOrNull<Int>("primary"))

        registry.clear()

        assertEquals(0, registry.getAll().size)
        assertNull(registry.getOrNull<Int>("secondary"))
    }
}