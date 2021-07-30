package io.github.pavleprica.mail.sender.exceptions

import io.github.pavleprica.mail.sender.model.MailRequest

/**
 * Thrown by [MailRequest] when the content set is empty.
 */
class ContentEmpty(content: Content): Exception("$content is empty")

enum class Content {
    TITLE, TEXT
}