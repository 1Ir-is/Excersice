package com.example.chuyen_doi_tien_te.controller;

import com.example.chuyen_doi_tien_te.service.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyConverterController {

    @Autowired
    private CurrencyConverterService currencyConverterService;

    @PostMapping("/convert")
    public String convertCurrency(@RequestParam("exchangeRate") double exchangeRate,
                                  @RequestParam("amount") double amount,
                                  @RequestParam("conversionType") String conversionType,
                                  Model model) {
        double resultAmount;
        if ("usdToVnd".equals(conversionType)) {
            resultAmount = currencyConverterService.convertUsdToVnd(exchangeRate, amount);
            model.addAttribute("usdAmount", amount);
            model.addAttribute("vndAmount", resultAmount);
        } else if ("vndToUsd".equals(conversionType)) {
            resultAmount = currencyConverterService.convertVndToUsd(exchangeRate, amount);
            model.addAttribute("vndAmount", amount);
            model.addAttribute("usdAmount", resultAmount);
        }
        model.addAttribute("exchangeRate", exchangeRate);
        model.addAttribute("conversionType", conversionType);
        return "result";
    }
}