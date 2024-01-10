package com.thrivefuse.ums.service;

import com.thrivefuse.ums.entity.OtpVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("EmailNotification")
public class EmailNotification implements NotificationService{

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private OtpGenerationService otpGenerationService;
    @Override
    public ResponseEntity<?> sendNotification(String destination) {

        String userGeneratedOtp = otpGenerationService.generateOTP();
        otpGenerationService.persistTheOtpInDatabase(userGeneratedOtp,"Email",destination);
        try {
            sendSimpleMessage(destination, "ThriveFuse Bank Otp ", "Please find your otp " + userGeneratedOtp + " .Please do not share the otp to anyone");
            return ResponseEntity.ok("Otp has been sent to mentioned email Id");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Exception occurred . Please try some time later");
        }
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kumar7.soft@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
