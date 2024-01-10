package com.thrivefuse.ums.controller;


import com.thrivefuse.ums.dto.UserDetails;
import com.thrivefuse.ums.entity.User;
import com.thrivefuse.ums.service.UserRegistrationService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @PostMapping("/createOrUpdateUser")
    public ResponseEntity<String> createNewUser(@RequestBody UserDetails user){

         if(userRegistrationService.userRegistration(user)){
             return ResponseEntity.ok("User successfully  created");
         }else{
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Faced issue while creating the User .");
         }

    }
    @GetMapping("/disable")
    public ResponseEntity<?> disabledTheUser(@RequestParam String userName){
        return userRegistrationService.disableUser(userName);
    }
    @GetMapping("/delete")
    public  ResponseEntity<?> deleteUser(@RequestParam String userName){
        return userRegistrationService.deleteUser(userName);
    }

}
