# Mail sender

Ever found yourself in need to send an e-mail from your code? Usually it contains of a bit of googling and
setup up of your project. The idea from this library is to make it easier for you. 

Will gradually work on expanding support for email providers.

## How to use it?

### Maven import

To import it, you can just copy and paste the dependency and put it into your pom.xml file.

```xml
<dependency>
    <groupId>io.github.pavleprica</groupId>
    <artifactId>mail-sender</artifactId>
    <version>0.0.1</version>
</dependency>
```

### Ready to send the email?

```kotlin
/*
Instance the e-mail sender
 */
val mailSender: MailSender = GmailMailSender(
    EmailCredentials(
        EmailProvider.GMAIL,
        "your-email@gmail.com",
        charArrayOf('p', 'a', 's', 's')
    )
)

/*
Compose and send
 */
mailSender.sendMail(
    MailRequest("pavle.prica5@gmail.com", "Title for new things", "Cool content", emptyList())
)
```

#### Notes
Be aware that access to your account could be restricted. Please do check out the security configurations of yhe account.

It is not recommended using your primary account for sending e-mails from the code.