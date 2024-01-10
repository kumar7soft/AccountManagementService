package com.thrivefuse.ums.service;

import org.springframework.http.ResponseEntity;

public interface NotificationService {

    public ResponseEntity<?> sendNotification(String destination);

}
