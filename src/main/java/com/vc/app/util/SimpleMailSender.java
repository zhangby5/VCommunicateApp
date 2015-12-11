package com.vc.app.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.vc.app.model.UserInfoVO;


public class SimpleMailSender {

    private static Logger logger = Logger.getLogger(SimpleMailSender.class);

    /**
     * send email
     * 
     * @param request
     * @param user
     * @param old
     * @return
     * @throws MessagingException
     */
    public static boolean sendEmail(HttpServletRequest request, UserInfoVO user,
            String old) throws MessagingException {

        MailInfo mailInfo = new MailInfo();
        mailInfo.setToAddress(user.getCemail());

        String activateFilePath = CommonUtil.getWebInf()
                + "email/activate_mail_cfg.properties";
        Properties prop = FileUtil.loadProperties(activateFilePath);
        mailInfo.setSubject(prop.getProperty("subject"));

        String url = request.getRequestURL().toString();
        String param = String.format("activate?uid=%s&email=%s",
                user.getCuserid(), user.getCemail());
        url = url.replace(old, param);
        String contentFilePath = CommonUtil.getWebInf()
                + "email/mail_content.cfg";

        String content = FileUtil.loadContent(contentFilePath);
        if (content != null) {
            content = content.replace("%%URL%%", url);
            content = content.replace("%%USER%%", user.getCemail());
            mailInfo.setContent(content);
        }
        return sendMail(mailInfo);
    }

    /**
     * 发送邮件
     * 
     * @param mailInfo
     *            待发送的邮件信息
     * @throws MessagingException
     */
    public static boolean sendMail(MailInfo mailInfo) throws MessagingException {
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = MailSenderInfo.getMailSession();
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(MailSenderInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            logger.error(ex.getMessage());
            throw ex;
        }
    }
}
