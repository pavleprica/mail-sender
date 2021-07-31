package io.github.pavleprica.mail.sender.model

import io.github.pavleprica.mail.sender.TestNameGenerator
import io.github.pavleprica.mail.sender.exceptions.ContentEmpty
import io.github.pavleprica.mail.sender.exceptions.InvalidEmail
import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.io.File

@DisplayNameGeneration(TestNameGenerator::class)
class MailRequestTests {

    @Nested
    inner class ReceiverValidityTests {

        @Nested
        inner class WhenReceiverValid {

            @Test
            fun shouldPass() {
                MailRequest(
                    "keshonda_messner6@gmail.com",
                    "desktop",
                    "Outer generations rat textile bronze.",
                    emptyList()
                )
            }

        }

        @Nested
        inner class WhenReceiverInvalid {

            @Test
            fun shouldThrowInvalidEmailWhenEmailExtensionInvalid() {
                assertThrows<InvalidEmail> {
                    MailRequest(
                        "keshonda_messner6@gmail.c",
                        "desktop",
                        "Outer generations rat textile bronze.",
                        emptyList()
                    )
                }
            }

            @Test
            fun shouldThrowInvalidEmailWhenEmailPrefixInvalid() {
                assertThrows<InvalidEmail> {
                    MailRequest(
                        "@gmail.c",
                        "desktop",
                        "Outer generations rat textile bronze.",
                        emptyList()
                    )
                }
            }

            @Test
            fun shouldThrowInvalidEmailWhenEmailEmpty() {
                assertThrows<InvalidEmail> {
                    MailRequest(
                        "",
                        "desktop",
                        "Outer generations rat textile bronze.",
                        emptyList()
                    )
                }
            }

            @Test
            fun shouldThrowInvalidEmailWhenNoAt() {
                assertThrows<InvalidEmail> {
                    MailRequest(
                        "keshonda_messner6gmail.com",
                        "desktop",
                        "Outer generations rat textile bronze.",
                        emptyList()
                    )
                }
            }

        }

    }

    @Nested
    inner class TitleValidityTests {

        @Nested
        inner class WhenTitleValid {

            @Test
            fun shouldPass() {
                MailRequest(
                    "keshonda_messner6@gmail.com",
                    "desktop",
                    "Outer generations rat textile bronze.",
                    emptyList()
                )
            }

        }

        @Nested
        inner class WhenTitleInvalid {

            @Test
            fun shouldThrowContentEmptyWhenTitleBlank() {
                assertThrows<ContentEmpty> {
                    MailRequest(
                        "keshonda_messner6@gmail.com",
                        "",
                        "Outer generations rat textile bronze.",
                        emptyList()
                    )
                }
            }

            @Test
            fun shouldThrowContentEmptyWhenTitleOnlyWhitespace() {
                assertThrows<ContentEmpty> {
                    MailRequest(
                        "keshonda_messner6@gmail.com",
                        " ",
                        "Outer generations rat textile bronze.",
                        emptyList()
                    )
                }
            }

        }

    }

    @Nested
    inner class TextContentValidityTests {

        @Nested
        inner class WhenTextContentValid {

            @Test
            fun shouldPass() {
                MailRequest(
                    "keshonda_messner6@gmail.com",
                    "desktop",
                    "Outer generations rat textile bronze.",
                    emptyList()
                )
            }

            @ParameterizedTest(name = "when text content is {0}")
            @ValueSource(strings = ["", " ", "    ",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            ])
            fun shouldNotThrowException(textContent: String) {
                assertDoesNotThrow {
                    MailRequest(
                        "keshonda_messner6@gmail.com",
                        "desktop",
                        textContent,
                        emptyList()
                    )
                }
            }

        }

        /*
        Text content can pretty much be anything, so we shouldn't check blank invalidity
         */

    }

    @Nested
    inner class AttachmentPathsValidityTests {

        @Nested
        inner class WhenAttachmentPathsValid {

            @Test
            fun shouldPassWhenNoPaths() {
                MailRequest(
                    "keshonda_messner6@gmail.com",
                    "desktop",
                    "Outer generations rat textile bronze.",
                    emptyList()
                )
            }

            @Test
            fun shouldPassWhenPathsPresent() {
                val testFile = File("some_file.txt")
                if (!testFile.exists()) testFile.createNewFile()

                MailRequest(
                    "keshonda_messner6@gmail.com",
                    "desktop",
                    "Outer generations rat textile bronze.",
                    listOf(testFile.absolutePath)
                )

                testFile.delete()
            }

        }

        @Nested
        inner class WhenAttachmentPathsInvalid {

            @Test
            fun shouldThrowAttachmentFileNotFoundWhenFileNoOnSystem() {
                val testFile = File("some_file.txt")
                if (testFile.exists()) testFile.delete()

                MailRequest(
                    "keshonda_messner6@gmail.com",
                    "desktop",
                    "Outer generations rat textile bronze.",
                    listOf(testFile.absolutePath)
                )
            }

        }

    }


}