package com.example.spring_hom_thu_dien_tu.repository;

import com.example.spring_hom_thu_dien_tu.model.EmailConfig;
import org.springframework.stereotype.Repository;

@Repository
public class EmailRepository implements IEmailRepository {
    private EmailConfig config = new EmailConfig();

    @Override
    public EmailConfig getConfig() {
        return config;
    }

    @Override
    public void save(EmailConfig config) {
        this.config = config;
    }
}
