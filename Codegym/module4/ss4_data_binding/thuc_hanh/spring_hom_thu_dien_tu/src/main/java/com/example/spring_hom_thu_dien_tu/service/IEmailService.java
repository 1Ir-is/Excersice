package com.example.spring_hom_thu_dien_tu.service;

import com.example.spring_hom_thu_dien_tu.model.EmailConfig;

public interface IEmailService {
    EmailConfig getCurrentConfig();
    void update(EmailConfig config);
}
