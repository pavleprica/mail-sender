package io.github.pavleprica.mail.sender.exceptions

import io.github.pavleprica.mail.sender.model.MailRequest
 import io.github.pavleprica.mail.sender.model.EmailCredentials

/**
 * Thrown by [MailRequest] or [EmailCredentials] when the is invalid.
 */
class InvalidEmail(email: String, reason: String? = null): Exception("$email provided is not valid. $reason")