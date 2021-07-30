package io.github.pavleprica.mail.sender

import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)

@DisplayNameGeneration(TestNameGenerator::class)
class BaseTest {

    @Test
    fun basicTest() {
        assertEquals(1, 1)
    }

}