package com.api.wissenapi.services;

import com.api.wissenapi.models.MailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    MailManager mailManager;
    public void sendMessageUser(String email, String message) {
        mailManager.sendMessage(email, message);
    }
}
