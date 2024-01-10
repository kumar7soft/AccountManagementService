package com.thrivefuse.ums.controller;

import com.thrivefuse.ums.dto.UserLogin;
import com.thrivefuse.ums.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    UserLoginService userLoginService;
    @PostMapping("/validate")
    public ResponseEntity<?> validateUserLogin(UserLogin user){
       return userLoginService.validateLogin(user);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> handleForgotPassword(UserLogin user){
        return userLoginService.handleForgotPassword(user);
    }

    @GetMapping("/userInfoByUserName")
    public ResponseEntity<?> userDetailsByUserName(@RequestParam String userName){
        return userLoginService.fetchTheUserInfoByUserName(userName);
    }

}
