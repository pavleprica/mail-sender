package io.github.pavleprica.mail.sender.gmail

import io.github.pavleprica.mail.sender.exceptions.Content
import io.github.pavleprica.mail.sender.exceptions.ContentEmpty
import io.github.pavleprica.mail.sender.model.EmailCredentials
import javax.mail.Session
import java.util.*
import javax.mail.Authenticator
import javax.mail.PasswordAuthentication

object GmailUtils {

    fun formGmailSession(emailCredentials: EmailCredentials): Session {
        return Session.getInstance(formGmailProperties(),
            object: Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(
                        emailCredentials.email,
                        String(emailCredentials.getPassword() ?: throw ContentEmpty(Content.PASSWORD))
                    )
                }
            })
    }

    private fun formGmailProperties(): Properties {
        val properties = Properties()

        properties["mail.smtp.host"] = "smtp.gmail.com"
        properties["mail.smtp.port"] = "587"
        properties["mail.smtp.auth"] = "true"
        properties["mail.smtp.starttls.enable"] = "true"

        return properties
    }

}