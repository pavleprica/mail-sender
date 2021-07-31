package io.github.pavleprica.mail.sender.exceptions

import io.github.pavleprica.mail.sender.model.EmailCredentials
import io.github.pavleprica.mail.sender.model.MailRequest

/**
 * Thrown by [MailRequest] or [EmailCredentials] when the is invalid.
 */
class InvalidEmail(email: String, reason: String? = null): Exception("$email provided is not valid. $reason")