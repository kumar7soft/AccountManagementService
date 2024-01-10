package com.thrivefuse.ums.service;

import org.springframework.http.ResponseEntity;

public class MessageNotification implements NotificationService{
    @Override
    public ResponseEntity<?> sendNotification(String destination) {
        return null;
    }
}
