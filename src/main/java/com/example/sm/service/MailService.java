package com.example.sm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final ExcelService excelService;

    public void sendEmail() throws MessagingException {

        log.info("Sending email has started now");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo("springboot.apa@gmail.com");
        helper.setSubject("List of UMS Users");
        helper.setText("Please check the attachment");

        excelService.exportToExcel();
        helper.addAttachment("export.xlsx", new ClassPathResource("export.xlsx"));

        javaMailSender.send(mimeMessage);

        log.info("Email sent successfully!");
    }
}
