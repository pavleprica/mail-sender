package io.github.pavleprica.mail.sender

import io.github.pavleprica.mail.sender.exceptions.AttachmentFileNotFound
import io.github.pavleprica.mail.sender.model.MailRequest

/**
 * Implementations of this will depend on the mail provider. Mail sender is the interface that is
 * aimed to be used to send mails to the desired destinations.
 */
interface MailSender {

    /**
     * Sends a single email that is described with the mailRequest
     * @param mailRequest a single email request. Containing the destination
     * @throws [AttachmentFileNotFound] if the file is not present on the machine
     */
    @Throws(AttachmentFileNotFound::class)
    fun sendMail(mailRequest: MailRequest)

    /**
     * Sends a list of emails provided by mail requests
     * @param mailRequests list of emails to be sent
     * @throws [AttachmentFileNotFound] if the file is not present on the machine
     */
    @Throws(AttachmentFileNotFound::class)
    fun sendMultipleMails(mailRequests: List<MailRequest>)

}