package com.example.spring_hom_thu_dien_tu.repository;

import com.example.spring_hom_thu_dien_tu.model.EmailConfig;

public interface IEmailRepository {
    EmailConfig getConfig();
    void save(EmailConfig config);
}
