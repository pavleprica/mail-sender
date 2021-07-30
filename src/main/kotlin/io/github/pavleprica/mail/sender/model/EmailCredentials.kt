package io.github.pavleprica.mail.sender.model

/**
 * Used as credentials to auth to the email provider.
 */
data class EmailCredentials(
    /**
     * Email of the account that is used to send an email.
     */
    val email: String,

    /**
     * Password for auth to the email provider.
     * CharArray is used for improved security.
     */
    @Suppress("ArrayInDataClass") val password: CharArray
)
