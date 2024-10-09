package com.triton.notification.service.impl;


import com.triton.mscommons.enums.ApplicationEnvironment;
import com.triton.notification.dto.helper.Mail;
import com.triton.notification.service.EmailService;
import com.triton.notification.service.MessageService;
import com.triton.notification.service.NotificationService;
import com.triton.notification.service.WhatsappService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.triton.mscommons.utils.CommonUtils.isTargetEnvironment;
import static com.triton.notification.utils.Constants.EMAIL_ID;
import static com.triton.notification.utils.Constants.MAIL_OTP_SUBJECT;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    private final Environment environment;
    private final EmailService emailService;
    private final MessageService messageService;
    private final WhatsappService whatsappService;

    @Autowired
    public NotificationServiceImpl(Environment environment, EmailService emailService, MessageService messageService, WhatsappService whatsappService) {
        this.environment = environment;
        this.emailService = emailService;
        this.messageService = messageService;
        this.whatsappService = whatsappService;
    }

    @Override
    public String sendEmailOtp(String email, String otp) {
        if (isTargetEnvironment(environment, ApplicationEnvironment.DEV)) {
            log.info("The OTP is => {}", otp);
            return String.format("The OTP is => %s", otp);
        } else {
            Map<String, Object> variable = new HashMap<>();
            variable.put("code", otp);
            Mail mail = Mail.getBuilder()
                    .from(EMAIL_ID)
                    .to(email)
                    .subject(MAIL_OTP_SUBJECT)
                    .containsTemplate(Boolean.TRUE)
                    .templateVariable(variable)
                    .build();
            return sendEmail(mail);
        }
    }

    /*    public String sendEmail(Mail mail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = templateEngine.process(Constants.OTP_TEMPLATE, context);
            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
            emailSender.send(message);
        } catch (Exception e) {
            log.error("Error sending email.", e);
            throw new RuntimeException(e);
        }
        return Constants.EMAIL_SENT_SUCCESS_MSG;
    }*/


    @Override
    public String sendEmail(Mail mail) {
        return emailService.sendEmail(mail);
    }
}
