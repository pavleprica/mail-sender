package io.github.pavleprica.mail.sender.model

import io.github.pavleprica.mail.sender.TestNameGenerator
import io.github.pavleprica.mail.sender.exceptions.InvalidEmail
import io.github.pavleprica.mail.sender.exceptions.InvalidPassword
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNull

@DisplayNameGeneration(TestNameGenerator::class)
class GmailEmailCredentialsTests {

    companion object {
        private val emailProvider = EmailProvider.GMAIL
    }

    @Nested
    inner class EmailValidityTests {

        val testPassword = "wkWRWpqRedJj50sW".toCharArray()

        @Nested
        inner class WhenEmailValid {

            @Test
            fun shouldPass() {
                val email = "pavle.prica5@gmail.com"

                EmailCredentials(
                    emailProvider,
                    email,
                    testPassword
                )
            }

        }

        @Nested
        inner class WhenEmailInvalid {

            @Test
            fun shouldThrowInvalidEmailWhenEmailExtensionInvalid() {
                val email = "pavle.prica5@yahoo.com"

                assertThrows<InvalidEmail> {
                    EmailCredentials(
                        emailProvider,
                        email,
                        testPassword
                    )
                }
            }

            @Test
            fun shouldThrowInvalidEmailWhenEmailPrefixInvalid() {
                val email = "@gmail.com"

                assertThrows<InvalidEmail> {
                    EmailCredentials(
                        emailProvider,
                        email,
                        testPassword
                    )
                }
            }

            @Test
            fun shouldThrowInvalidEmailWhenEmailEmpty() {
                val email = ""

                assertThrows<InvalidEmail> {
                    EmailCredentials(
                        emailProvider,
                        email,
                        testPassword
                    )
                }
            }

            @Test
            fun shouldThrowInvalidEmailWhenNoAt() {
                val email = "pavle.prica5gmail.com"

                assertThrows<InvalidEmail> {
                    EmailCredentials(
                        emailProvider,
                        email,
                        testPassword
                    )
                }
            }

        }


    }

    @Nested
    inner class PasswordValidityTests {

        val testEmail = "artia_munguiavod@gmail.com"

        @Nested
        inner class WhenPasswordValid {

            @Test
            fun shouldPass() {
                EmailCredentials(
                    emailProvider,
                    testEmail,
                    "c5hTkNh5amHotzbyL".toCharArray()
                )
            }

        }

        @Nested
        inner class WhenPasswordInvalid {

            @Test
            fun shouldThrowInvalidPasswordWhenPasswordBlank() {
                assertThrows<InvalidPassword> {
                    EmailCredentials(
                        emailProvider,
                        testEmail,
                        charArrayOf()
                    )
                }
            }

        }

    }

    @Nested
    inner class PasswordClearingTests {

        @Test
        fun shouldAccessPasswordAndClear() {
            val testingPassword = "OHUJQXA0Nk8LtajlNiN".toCharArray()
            val credentials = EmailCredentials(
                emailProvider,
                "caitlin_putneyo@gmail.com",
                testingPassword
            )

            val password = credentials.getPassword()

            assertEquals(testingPassword, password)

            assertNull(credentials.getPassword())
        }

    }

}