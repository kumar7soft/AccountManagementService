package com.thrivefuse.ums.controller;

import com.thrivefuse.ums.service.NotificationService;
import com.thrivefuse.ums.service.OtpGenerationService;
import com.thrivefuse.ums.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserValidatoinController {

    @Autowired
    UserValidationService validationService;

    @Autowired
    OtpGenerationService otpService;

    @Autowired
    @Qualifier("EmailNotification")
    NotificationService notificationService;

    @GetMapping("/validate/adhar")
    public ResponseEntity<?> validateUserAdhar(@RequestParam String adharNumber){

        if(validationService.validateUserAdharCard(adharNumber)){
            return ResponseEntity.ok("Adhar authentication is successfully completed");
        }else{
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Adhar authentication is failed");
        }
    }

    @GetMapping("/validate/pan")
    public ResponseEntity<?> validateUserPanDetails(@RequestParam String panCardNumebr){

        if(validationService.validateUserPanCard(panCardNumebr)){
            return ResponseEntity.ok("Pan card authentication is successfully completed");
        }else{
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pan card authentication is failed");
        }
    }

    @GetMapping("/sendOtpUsingMobile")
    public ResponseEntity<?> sendOtpThroughByMobile(String mobileNumber){

        return ResponseEntity.ok("Otp has been sent to mentioned mobile number");
    }
    @GetMapping("/validateMobileNumber")
    public ResponseEntity<?> valiateUserMobilePhone(String otp,String mobileNumebr){
        return ResponseEntity.ok("Otp Verified ");
    }
    @GetMapping("/sendOtpUsingEmail")
    public ResponseEntity<?> sendOtpThroughEmail(String email){
        return notificationService.sendNotification(email);
    }

    public ResponseEntity<?> validateEmailOtp(String otp,String email){

        if(otpService.validateUserOtp(otp,"Email",email)){
           return ResponseEntity.ok("Email Verified");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email verification failed");
        }

    }

}
