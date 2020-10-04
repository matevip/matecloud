package vip.mate.core.mail.core;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ObjectUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


/**
 * JavaMail邮件发送者实现类
 *
 * @author xuzhanfu
 */
@Slf4j
@AllArgsConstructor
public class JavaMailTemplate implements MailTemplate {

	private final JavaMailSender mailSender;

	private final MailProperties mailProperties;

	@Override
	public void sendSimpleMail(String to, String subject, String content, String... cc) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailProperties.getUsername());
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		if (!ObjectUtils.isEmpty(cc)) {
			message.setCc(cc);
		}
		mailSender.send(message);
	}

	@Override
	public void sendHtmlMail(String to, String subject, String content, String... cc) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = buildHelper(to, subject, content, message, cc);
		mailSender.send(message);
	}

	@Override
	public void sendAttachmentsMail(String to, String subject, String content, String filePath, String... cc) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = buildHelper(to, subject, content, message, cc);
		FileSystemResource file = new FileSystemResource(new File(filePath));
		String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
		helper.addAttachment(fileName, file);
		mailSender.send(message);
	}

	@Override
	public void sendResourceMail(String to, String subject, String content, String rscPath, String rscId, String... cc) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = buildHelper(to, subject, content, message, cc);
		FileSystemResource res = new FileSystemResource(new File(rscPath));
		helper.addInline(rscId, res);
		mailSender.send(message);
	}

	/**
	 * 统一封装MimeMessageHelper
	 * @param to      收件人地址
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param message 消息对象
	 * @param cc      抄送地址
	 * @return MimeMessageHelper
	 * @throws MessagingException 异常
	 */
	private MimeMessageHelper buildHelper(String to, String subject, String content, MimeMessage message, String... cc) throws MessagingException {
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(mailProperties.getUsername());
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content, true);
		if (!ObjectUtils.isEmpty(cc)) {
			helper.setCc(cc);
		}
		return helper;
	}
}
