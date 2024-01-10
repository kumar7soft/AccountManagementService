package com.thrivefuse.ums.service;

import com.thrivefuse.ums.dto.UserLogin;
import com.thrivefuse.ums.entity.User;
import com.thrivefuse.ums.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginService {

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    public ResponseEntity<?> validateLogin(UserLogin userLogin){

        Optional<User> user = userRegistrationRepository.findByUsername(userLogin.getUserName());

        User userDetails = user.get();

        String password = userDetails.getUserCredentials().getPassword();

        if(userLogin.getPassword().equals(password)){
            return ResponseEntity.ok("Login Successful");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Access Denied . Invalid credentials");

        }
    }

    public ResponseEntity<?>  handleForgotPassword(UserLogin userLogin){
        try {
            Optional<User> optionalUseruser = userRegistrationRepository.findByUsername(userLogin.getUserName());
            User user = optionalUseruser.get();
            user.getUserCredentials().setPassword(userLogin.getPassword());
            userRegistrationRepository.save(user);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to update the password");
        }
        return ResponseEntity.ok("Password updated successfully");
    }

    public ResponseEntity<?> fetchTheUserInfoByUserName(String userName){

        Optional<User> optionalUseruser = userRegistrationRepository.findByUsername(userName);
        User user = optionalUseruser.get();
        if(user == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User is not available with respective UserName");
        }else{
           return ResponseEntity.ok("User details retrived successfully ");
        }
    }

}
