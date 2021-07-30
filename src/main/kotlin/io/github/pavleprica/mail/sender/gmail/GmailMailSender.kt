package io.github.pavleprica.mail.sender.gmail

import io.github.pavleprica.mail.sender.MailSender
import io.github.pavleprica.mail.sender.model.EmailCredentials
import io.github.pavleprica.mail.sender.model.MailRequest

class GmailMailSender(
    private val emailCredentials: EmailCredentials,
): MailSender {

    override fun sendMail(mailRequest: MailRequest) {
        TODO("Not yet implemented")
    }

    override fun sendMultipleMails(mailRequests: List<MailRequest>) {
        TODO("Not yet implemented")
    }


}