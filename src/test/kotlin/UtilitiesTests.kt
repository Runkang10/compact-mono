import io.github.runkang10.compactmono.utilities.strings
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

object UtilitiesTests {
    @Test
    fun testStringUtility() {
        val sample = listOf("Hello, ", "world!")
        val separator = "\n"
        val expected = sample.joinToString(separator)

        assertEquals(expected, strings(separator, sample))
        assertEquals(expected, strings(separator, *sample.toTypedArray()))
        assertEquals(expected, sample.strings(separator))
    }
}