package com.vc.app.util;

import java.util.Properties;

import javax.mail.Session;

public class MailSenderInfo {

    /**
     * 邮件的配置信息
     */
    private static final Properties mailCfg;

    // 登陆邮件发送服务器的用户名和密码
    private static final String userName;
    private static final String password;

    // 邮件发送者的地址
    private static final String fromAddress;

    static {
        String filePath = CommonUtil.getWebInf() + "email/mail.properties";
        mailCfg = FileUtil.loadProperties(filePath);

        userName = mailCfg.getProperty("mail.sender.name");
        password = mailCfg.getProperty("mail.sender.password");
        fromAddress = mailCfg.getProperty("mail.sender.address");
    }

    private MailSenderInfo() {
    }

    public static Session getMailSession() {
        // 判断是否需要身份认证
        PasswordAuthenticator authenticator = new PasswordAuthenticator(
                userName, password);

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(mailCfg,
                authenticator);
        return sendMailSession;
    }

    public static String getFromAddress() {
        return fromAddress;
    }
}
