package com.example.spring_hom_thu_dien_tu.service;

import com.example.spring_hom_thu_dien_tu.model.EmailConfig;
import com.example.spring_hom_thu_dien_tu.repository.IEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private IEmailRepository emailRepository;

    @Override
    public EmailConfig getCurrentConfig() {
        return emailRepository.getConfig();
    }

    @Override
    public void update(EmailConfig config) {
        emailRepository.save(config);
    }
}
