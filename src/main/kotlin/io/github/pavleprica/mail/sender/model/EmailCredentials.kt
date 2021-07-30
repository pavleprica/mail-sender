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
     *
     * Important note is that after the first access to the password it will be cleared from memory.
     */
    @Suppress("ArrayInDataClass") private var password: CharArray?
) {

    /**
     * Access the password of the email account. Clearing the password from memory afterwards.
     */
    fun getPassword(): CharArray? {
        val currentPassword = password

        password = null

        return currentPassword
    }

}
