package com.example.may_tinh_ca_nhan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @RequestMapping("/calculate")
    public String calculate(@RequestParam("num1") double num1,
                            @RequestParam("num2") double num2,
                            @RequestParam("operation") String operation,
                            Model model) {
        double result = calculatorService.calculate(num1, num2, operation);
        model.addAttribute("result", result);
        return "result";

    }
}
