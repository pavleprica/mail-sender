package io.github.pavleprica.mail.sender.model

/**
 * MailRequests represents a single email to be sent.
 */
data class MailRequest(
    /**
     * Receiver of the email. Has to be a valid email.
     */
    val receiver: String,

    /**
     * Email title
     */
    val title: String,

    /**
     * Email text content
     */
    val textContent: String,

    /**
     * Paths to files to be attached to the email.
     */
    val attachmentsPaths: List<String>,
)
