package com.thrivefuse.ums.service;

import com.thrivefuse.ums.entity.OtpVerification;
import com.thrivefuse.ums.repository.UserOtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpGenerationService {

    @Autowired
    UserOtpRepository userOtpRepository;

    public  String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otpValue = 100000 + random.nextInt(900000); // Generates a 6-digit random number
        return String.valueOf(otpValue);
    }

    public void persistTheOtpInDatabase(String otp,String type,String identifier){

        OtpVerification otpVerification = new OtpVerification();
        otpVerification.setOtp(otp);
        otpVerification.setType(type);
        otpVerification.setIdentifier(identifier);
        otpVerification.setCreatedAt(LocalDateTime.now());
        otpVerification.setExpiresAt(LocalDateTime.now().plusMinutes(30));
        userOtpRepository.save(otpVerification);

    }

    public boolean validateUserOtp(String otp,String type,String identifier){
        Optional<OtpVerification> userOtpDetails = userOtpRepository.findTopByIdentifierAndTypeOrderByCreatedAtDesc(identifier, type);
        if(userOtpDetails.isEmpty()){
            return false;
        }
        String userOtp = userOtpDetails.get().getOtp();
        LocalDateTime otpExpiredTime = userOtpDetails.get().getExpiresAt();
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(otpExpiredTime, currentTime);
        long minutes = duration.toMinutes();

        if(userOtp.equals(otp) && minutes <=30){
            return true;
        }else{
            return false;
        }


    }
}
