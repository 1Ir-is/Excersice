package com.example.server.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Basic configuration for user 1Ir-is at 2025-08-06 06:09:07 UTC
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.getConfiguration().setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STRICT);

        return mapper;
    }
}