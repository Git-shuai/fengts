package tian.web;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

/**
 * @author tian
 * @date 2020/5/26
 */
public class SendMail {
    public static void sendText(String toAddr, String code) {

        try {
            sendText(
                    "smtp.163.com",
                    "fengtianshuai1013@163.com",
                    toAddr,
                    "NGYSFEZUQPFDRPCE",
                    "大帅的网站的登录验证码",
                    "您的登录验证码是:" + code + ",请不要交予其他的人"
            );
        } catch (Exception e) {
            throw new RuntimeException("发送验证码失败");
        }
    }


    public static void sendText(String smtp, String fromAddr, String toAddr, String password, String subject, String text) throws Exception {
//        //邮件发送服务器地址
//        smtp="smtp.163.com";
//        //发件人
//        fromAddr="fengtianshuai1013@163.com";
//        //收件人
//        toAddr="1873461847@qq.com";
//        //认证时需要的密码    //网易邮箱认证码:NGYSFEZUQPFDRPCE  //QQ邮箱认证码:evtrdazvxcbydbfa
//        password="NGYSFEZUQPFDRPCE";

        //初始化发送邮件对象
        Properties props = System.getProperties();
        // Setup mail server
        props.put("mail.smtp.host", smtp);

        //创建会话对象
        Session session = Session.getDefaultInstance(props);

        session.setDebug(true);

        //构建邮件内容对象
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress(fromAddr));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddr));

        message.setSubject(subject);

        message.setText(text);

        message.saveChanges();//保存邮件更改

        Transport transport = session.getTransport("smtp");
        transport.connect(smtp, fromAddr, password);

        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }


    public static void sendMultiMail(String smtp, String fromAddr, String toAddr, String password, String subject, String text, File file) throws Exception {
//        String smtp="smtp.163.com";//邮件发送服务器地址
//        String fromAddr="tigerboy_test@163.com";//发件人
//        String toAddr="youyuedai_fjl@163.com";//收件人
//        String password="AVNOFYTRVDUTNIGG";//认证时需要的密码

        //初始化发送邮件对象
        Properties props = System.getProperties();
        // Setup mail server
        props.put("mail.smtp.host", smtp);

        //创建会话对象
        Session session = Session.getDefaultInstance(props);

        session.setDebug(true);

        //构建邮件内容对象
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress(fromAddr));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddr));

        message.setSubject(subject);


        Multipart multipart = new MimeMultipart();

        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(text);

        multipart.addBodyPart(bodyPart);


        BodyPart dataBodyPart = new MimeBodyPart();

        DataSource source = new FileDataSource(file);

        dataBodyPart.setDataHandler(new DataHandler(source));

        //String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
        String filename = file.getName().substring(file.getName().lastIndexOf("\\") + 1);

        dataBodyPart.setFileName(filename);

        multipart.addBodyPart(dataBodyPart);


        message.setContent(multipart);


        message.saveChanges();//保存邮件更改

        Transport transport = session.getTransport("smtp");
        transport.connect(smtp, fromAddr, password);

        transport.sendMessage(message, message.getAllRecipients());
        transport.close();


    }
}
