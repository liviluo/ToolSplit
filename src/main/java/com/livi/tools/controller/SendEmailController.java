package com.livi.tools.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livi.tools.util.SendEmailUtil;

@RestController
public class SendEmailController {

	@Autowired
	SendEmailUtil send;

	private static final Logger log = LoggerFactory.getLogger("baseLog");

	private static final String emailFormat = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";

	@RequestMapping(value = "/sendEmail")
	public String function(String to, String subject, String content) {
		String msg = "";
		if (to.matches(emailFormat)) {
			send.sendSimpleMail(to, subject, content);
			msg = "发送成功";
			log.info("邮件发送成功，收件人：" + to + "主题：" + subject + "内容：" + content);
		} else {
			msg = "邮箱格式错误";
			log.warn("邮件发送s失败，收件人：" + to + "主题：" + subject + "内容：" + content);
		}
		return msg;
	}

	@RequestMapping(value = "/sendAttachment")
	public String sendAttachment() {
		String attanchment = "attanchment.txt";
		File file = new File(attanchment);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e.getMessage());
				return "fail:" + e.getMessage();
			}
		}
		try {
			send.sendAttachmentMail(Arrays.asList("1185872070@qq.com", "1027544912@qq.com"), "Send Attachment Test",
					"Test", attanchment);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return "fail:" + e.getMessage();
		}
		return "success";
	}

}
