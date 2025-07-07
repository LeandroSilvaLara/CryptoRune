import org.junit.Assert.assertEquals
import org.junit.Test

class FormatVolumeTest {
    @Test
    fun `formatVolume handles trillions`() {
        // For 1,200,000,000,000 should display 1.2T after fix
        val result = formatVolume("1200000000000")
        assertEquals("1.2T", result)
    }

    @Test
    fun `formatVolume returns raw value for invalid input`() {
        val result = formatVolume("abc")
        assertEquals("abc", result)
    }
}
