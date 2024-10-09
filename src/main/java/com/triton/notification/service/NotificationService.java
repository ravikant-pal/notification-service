package com.triton.notification.service;


import com.triton.notification.dto.helper.Mail;

public interface NotificationService {

    String sendEmailOtp(String email, String otp);

    String sendEmail(Mail mail);
}
