package com.logines.schedule.service;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    public void sendEmail() throws Exception{
        Properties properties=new Properties();
//fill all the information like host name etc.
        Session session= Session.getInstance(properties,null);
        MimeMessage message=new MimeMessage(session);
        message.setFrom(new InternetAddress("andreasplado@gmail.com"));
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress("andreasplado@gmail.com"));
        message.setHeader("", "Tere!");
        message.setText("Loo loginesi uus kasutaja");
    }
}
