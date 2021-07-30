package io.github.pavleprica.mail.sender.exceptions

/**
 * Thrown when the file desired to be attached is not found under the given path.
 */
class AttachmentFileNotFound(fileName: String): Exception("$fileName not found")