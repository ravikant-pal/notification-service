package com.triton.notification.service;


import com.triton.notification.dto.helper.Mail;

public interface EmailService {
    String sendEmail(Mail mail);
}
