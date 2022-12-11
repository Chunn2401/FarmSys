/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmsys.Helper;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author trieu Hướng dẫn sử dụng: Gửi mail văn bản: MailHelper.sendText("địa
 * chỉ mail", "tiêu đề", "nội dung"); Gửi mail chứa file:
 * MailHelper.sendFile("địa chỉ mail", "tiêu đề", "đường dẫn file");
 */
public class MailHelper {

    static String accountName = "farmsys.contact@gmail.com";
    static String accountPassword = "FarmSys@123456";

    public static void sendText(String email, String subject, String body) {
        try {
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", 587);
            Session s = Session.getInstance(p,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(accountName, accountPassword);
                }
            });
            String from = accountName;
            String to = email;

            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(body);
            msg.setSentDate(new Date());

            Transport.send(msg);
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
    }

    public static void sendFile(String email, String subject, String body, String file) throws IOException {
        try {
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", 587);
            Session s = Session.getInstance(p,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(accountName, accountPassword);
                }
            });

            String from = accountName;
            String to = email;

            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(body);
            msg.setSentDate(new Date());

            MimeMultipart multipart = new MimeMultipart();

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(new File(file));

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachment);

            msg.setContent(multipart);
            Transport.send(msg);
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
    }

}
