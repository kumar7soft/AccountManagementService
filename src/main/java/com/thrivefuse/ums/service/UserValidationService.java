package com.thrivefuse.ums.service;

import org.springframework.stereotype.Service;

@Service
public class UserValidationService {


    public boolean validateUserAdharCard(String adharCardNumber){

        return true;
    }

    public boolean validateUserPanCard(String panCardNumber){

        return true;
    }
}
