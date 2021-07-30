package io.github.pavleprica.mail.sender.model

import io.github.pavleprica.mail.sender.exceptions.InvalidEmail
import io.github.pavleprica.mail.sender.exceptions.InvalidPassword

/**
 * Used as credentials to auth to the email provider.
 */
data class EmailCredentials(
    /**
     * An enum representing the email provider. It will be used for verifying the email.
     */
    val emailProvider: EmailProvider,

    /**
     * Email of the account that is used to send an email.
     */
    val email: String,

    /**
     * Password for auth to the email provider.
     * CharArray is used for improved security.
     *
     * Important note is that after the first access to the password it will be cleared from memory.
     */
    @Suppress("ArrayInDataClass") private var password: CharArray?
) {

    init {
        validateEmail(email, emailProvider)
        validatePassword(password)
    }

    /**
     * Access the password of the email account. Clearing the password from memory afterwards.
     */
    fun getPassword(): CharArray? {
        val currentPassword = password

        password = null

        return currentPassword
    }

    private fun validateEmail(email: String, emailProvider: EmailProvider) {
        if (email.isBlank()) throw InvalidEmail(email, "Email cannot be blank.")
        if (!email.contains("@")) throw InvalidEmail(email)
        if (email.substringBefore("@").isBlank()) throw InvalidEmail(email, "Prefix shouldn't be blank.")

        validateEmailExtension(email, emailProvider)
    }

    private fun validateEmailExtension(email: String, emailProvider: EmailProvider) {
        when (emailProvider) {
            EmailProvider.GMAIL -> {
                if (email.substringAfter("@") != "gmail.com") throw InvalidEmail(email, "Only supporting @gmail.com.")
            }
        }
    }

    private fun validatePassword(password: CharArray?) {
        password ?: throw InvalidPassword("Password cannot be null.")

        if (password.isEmpty()) throw InvalidPassword("Password cannot be empty")
    }

}
