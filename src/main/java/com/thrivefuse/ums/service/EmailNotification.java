package com.thrivefuse.ums.service;

import com.thrivefuse.ums.dto.EmailInfo;
import com.thrivefuse.ums.entity.OtpVerification;
import com.thrivefuse.ums.intgration.NotificationServiceClient;
import feign.codec.DecodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service("EmailNotification")
public class EmailNotification implements NotificationService{


    @Autowired
    private OtpGenerationService otpGenerationService;

    @Autowired(required = true)
    NotificationServiceClient notificationServiceClient;
    @Override
    public ResponseEntity<?> sendNotification(String destination) {


        try {
            String userGeneratedOtp = otpGenerationService.generateOTP();
            otpGenerationService.persistTheOtpInDatabase(userGeneratedOtp,"Email",destination);
            sendSimpleMessage(destination, "ThriveFuse Bank Otp ", "Please find your otp " + userGeneratedOtp + " .Please do not share the otp to anyone");
            return ResponseEntity.ok("Otp has been sent to mentioned email Id");
        }catch (DecodeException ex){
            return ResponseEntity.ok("Otp has been sent to mentioned email Id");
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Exception occurred . Please try some time later");
        }
    }

    public void sendSimpleMessage(String toMailAddress, String subject, String text) {
        EmailInfo emailInfo = new EmailInfo();
        emailInfo.setMessage(text);
        emailInfo.setSubject(subject);
        emailInfo.setToMailAddress(Arrays.asList(toMailAddress));
        emailInfo.setAttachment(null);
        notificationServiceClient.sendSingleEmail(emailInfo);
    }
}
