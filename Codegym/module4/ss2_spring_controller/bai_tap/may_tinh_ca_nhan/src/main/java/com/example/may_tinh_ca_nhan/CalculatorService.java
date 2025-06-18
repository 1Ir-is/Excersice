package com.example.may_tinh_ca_nhan;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public double calculate(double num1, double num2, String operation) {
        double result = 0;
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                result = num2 != 0 ? num1 / num2 : 0;
                break;
        }
        return result;
    }
}
