package com.thrivefuse.ums.service;

import com.thrivefuse.ums.dto.UserDetails;
import com.thrivefuse.ums.entity.User;
import com.thrivefuse.ums.entity.UserCredentials;
import com.thrivefuse.ums.entity.UserPanCardAndAdharDetailsEntity;
import com.thrivefuse.ums.exception.UsernameNotFoundException;
import com.thrivefuse.ums.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserRegistrationService {

    @Autowired
    UserRegistrationRepository userRegistrationRepository;


    public boolean userRegistration(UserDetails userDetails){
        User user = loadUserEntity(userDetails);
        try {
             userRegistrationRepository.save(user);
        }catch (Exception ex){

            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public ResponseEntity<?> disableUser(String userName){

        try {
            User user = userRegistrationRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userName));
            user.setActive(false);
            userRegistrationRepository.save(user);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Unable to disable the user ");
        }
        return ResponseEntity.ok("Disabled the user");

    }

    public ResponseEntity<?> deleteUser(String userName){

        try {
            User user = userRegistrationRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userName));
            userRegistrationRepository.delete(user);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to disable the user ");
        }
        return ResponseEntity.ok("User  deleted successfully  ");

    }

    private User loadUserEntity(UserDetails userDetails){
        User userEntity = new User();

        userEntity.setFirstName(userDetails.getFirstName());
        userEntity.setLastName(userDetails.getLastName());
        userEntity.setMiddleName(userDetails.getMiddleName());
        userEntity.setPrimaryPhone(userDetails.getPrimaryPhone());
        userEntity.setSecondaryPhone(userDetails.getSecondaryPhone());
        userEntity.setAddress(userDetails.getAddress());
        userEntity.setState(userDetails.getState());
        userEntity.setCountry(userDetails.getCountry());
        userEntity.setCity(userDetails.getCity());
        userEntity.setZip(userDetails.getZip());
        userEntity.setEmail(userDetails.getEmail());

        if (userDetails.getUserPanAndAdharDetails() != null) {
            UserPanCardAndAdharDetailsEntity panAdharEntity = new UserPanCardAndAdharDetailsEntity();
            panAdharEntity.setAdharCardNumber(userDetails.getUserPanAndAdharDetails().getAdharCardNumber());
            panAdharEntity.setPanCardNumber(userDetails.getUserPanAndAdharDetails().getPanCardNumber());
            userEntity.setUserPanAndAdharDetails(panAdharEntity);
        }
        if(userDetails.getUserLogin() != null){
            UserCredentials userCredentials = new UserCredentials();
            userCredentials.setUsername(userDetails.getUserLogin().getUserName());
            userCredentials.setPassword(userDetails.getUserLogin().getPassword());
            userEntity.setUserCredentials(userCredentials);
        }
        userEntity.setCreatedDate(LocalDateTime.now());
        userEntity.setVerified(true);
        userEntity.setActive(false);
        return userEntity;


    }




}
