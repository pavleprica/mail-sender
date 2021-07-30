package io.github.pavleprica.mail.sender.exceptions

import io.github.pavleprica.mail.sender.model.EmailCredentials

/**
 * Thrown by [EmailCredentials] when the password is invalid.
 */
class InvalidPassword(reason: String): Exception("Password provided is invalid. $reason")