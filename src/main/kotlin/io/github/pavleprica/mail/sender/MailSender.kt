package io.github.pavleprica.mail.sender

import io.github.pavleprica.mail.sender.model.MailRequest

sealed interface MailSender {

    fun sendMail(mailRequest: MailRequest)

    fun sendMultipleMails(mailRequests: List<MailRequest>)

}