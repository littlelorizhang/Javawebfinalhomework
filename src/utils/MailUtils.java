package utils;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {
    public MailUtils( String emailadd){
        //参数设置
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host","smtp.qq.com");//发送邮箱服务器
        properties.setProperty("mail.smtp.port","465");//发送端口
        properties.setProperty("mail.smtp.auth","true");//是否开启权限控制
        properties.setProperty("mail.debug","true");//true 打印信息到控制台
        properties.setProperty("mail.transport.protocol","smtp");//发送的协议是简单的邮件传输协议
        properties.setProperty("mail.smtp.ssl.enable","true");
        //建立连接
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1198697941@qq.com","sxdtmwphhzfdbafh");
            }
        });
        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        try {
            message.setFrom(new InternetAddress("1198697941@qq.com"));

            //设置收件人
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(emailadd));//收件人
            //System.out.println(user.getUseremail());
            //设置主题
            message.setSubject("购买成功");
            //设置邮件正文  第二个参数是邮件发送的类型
            message.setContent("您购买的商品将在24小时内发货","text/html;charset=UTF-8");
            //发送一封邮件
            Transport transport = session.getTransport();
            transport.connect("119697941@qq.com","sxdtmwphhzfdbafh");
            Transport.send(message);
            //System.out.println("执行了");

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

}
