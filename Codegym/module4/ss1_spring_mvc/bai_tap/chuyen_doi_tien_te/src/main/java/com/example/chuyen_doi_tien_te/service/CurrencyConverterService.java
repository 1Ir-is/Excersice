package com.example.chuyen_doi_tien_te.service;

import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterService {

    public double convertUsdToVnd(double exchangeRate, double usdAmount) {
        return exchangeRate * usdAmount;
    }

    public double convertVndToUsd(double exchangeRate, double vndAmount) {
        return vndAmount / exchangeRate;
    }
}