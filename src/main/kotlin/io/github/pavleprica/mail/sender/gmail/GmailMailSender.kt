package io.github.pavleprica.mail.sender.gmail

import io.github.pavleprica.mail.sender.MailSender
import io.github.pavleprica.mail.sender.exceptions.AttachmentFileNotFound
import io.github.pavleprica.mail.sender.model.EmailCredentials
import io.github.pavleprica.mail.sender.model.MailRequest
import java.io.File
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class GmailMailSender(
    private val emailCredentials: EmailCredentials,
): MailSender {

    private val session: Session = GmailUtils.formGmailSession(emailCredentials)

    override fun sendMail(mailRequest: MailRequest) {
        val attachmentFiles = getAttachmentFiles(mailRequest.attachmentsPaths)

        val message = MimeMessage(session)
        message.setFrom(InternetAddress(emailCredentials.email))
        message.setRecipients(
            Message.RecipientType.TO,
            InternetAddress.parse(mailRequest.receiver)
        )
        message.subject = mailRequest.title

        val multiPart = MimeMultipart()

        val text = MimeBodyPart()
        text.setText(mailRequest.textContent)
        multiPart.addBodyPart(text)

        attachmentFiles.forEach { file ->
            val attachment = MimeBodyPart()
            attachment.attachFile(file)
            multiPart.addBodyPart(attachment)
        }

        message.setContent(multiPart)

        Transport.send(message)
    }

    override fun sendMultipleMails(mailRequests: List<MailRequest>) {
        mailRequests.forEach {
            val attachmentFiles = getAttachmentFiles(it.attachmentsPaths)

            val message = MimeMessage(session)
            message.setFrom(InternetAddress(emailCredentials.email))
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(it.receiver)
            )
            message.subject = it.title

            val multiPart = MimeMultipart()

            val text = MimeBodyPart()
            text.setText(it.textContent)
            multiPart.addBodyPart(text)

            attachmentFiles.forEach { file ->
                val attachment = MimeBodyPart()
                attachment.attachFile(file)
                multiPart.addBodyPart(attachment)
            }

            message.setContent(multiPart)

            Transport.send(message)
        }
    }

    private fun getAttachmentFiles(pathToFiles: List<String>): List<File> {
        val files = mutableListOf<File>()

        pathToFiles.forEach {
            val file = File(it)

            if (file.exists()) {
                files.add(file)
            } else {
                throw AttachmentFileNotFound(it)
            }
        }

        return files
    }

}