package com.example.sm.scheduler;

import com.example.sm.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Slf4j
@Component
@AllArgsConstructor
public class ScheduledTasks {
    MailService mailService;

    @Scheduled(cron = "0 0 9 * * Wed")
    public void sendEmail() throws MessagingException {

        mailService.sendEmail();
        log.info("Scheduled message done.");
    }
}