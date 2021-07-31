package io.github.pavleprica.mail.sender.model

import io.github.pavleprica.mail.sender.exceptions.AttachmentFileNotFound
import io.github.pavleprica.mail.sender.exceptions.Content
import io.github.pavleprica.mail.sender.exceptions.ContentEmpty
import io.github.pavleprica.mail.sender.exceptions.InvalidEmail
import org.apache.commons.validator.routines.EmailValidator
import java.io.File

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
) {

    init {
        validateReceiverEmail(receiver)
        validateTitle(title)
        validateAttachmentFiles(attachmentsPaths)
    }

    private fun validateReceiverEmail(receiver: String) {
        if (receiver.isEmailInvalid()) throw InvalidEmail(receiver)
    }

    private fun String.isEmailInvalid(): Boolean {
        return !EmailValidator.getInstance().isValid(this)
    }

    private fun validateTitle(title: String) {
        if (title.isBlank()) throw ContentEmpty(Content.TITLE)
    }

    private fun validateAttachmentFiles(attachmentsPaths: List<String>) {
        attachmentsPaths.forEach { if (!File(it).exists()) throw AttachmentFileNotFound(it) }
    }

}
