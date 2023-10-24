package com.example.currencyexchanger.controllers;

import com.example.currencyexchanger.Models.Currency;
import com.example.currencyexchanger.service.CurrenciesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddController {
    @GetMapping("/addcurrency")
    public String addCCurrency(
            @RequestParam String code,
            @RequestParam String name,
            @RequestParam String sign
    ){
        CurrenciesService.addCurrency(new Currency(code,name,sign));
        return "redirect:/currencies";
    }
    @GetMapping("delete/currency/{code}")
    public String deleteCCurrency(
            @PathVariable String code
    ){
        CurrenciesService.deleteCurrency(code);
        return "redirect:/currencies";
    }
}
