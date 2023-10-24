package com.example.currencyexchanger.controllers;
import com.example.currencyexchanger.service.CurrenciesService;
import com.example.currencyexchanger.service.ExchangeRatesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/currencies")
    public String allCurrencies() {
        return CurrenciesService.getAllCurrencies();
    }

    @GetMapping("/currency/{code}")
    public String oneCurrency(@PathVariable(value = "code") String code) {
        return CurrenciesService.getOneCurrency(code);
    }

    @GetMapping("/exchangeRates")
    public String allExchangeRates() {
        return ExchangeRatesService.getAllRates();
    }

    @GetMapping("/exchangeRate/{codes}")
    public String oneExchangeRate(@PathVariable(value = "codes") String codes) {
        return ExchangeRatesService.getExchangeRate(codes);
    }

    @GetMapping("/exchange")
    public String Exchange(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam Double amount
    ) {
        System.out.println(ExchangeRatesService.findExchange(from, to, amount));
        return ExchangeRatesService.findExchange(from, to, amount);
    }

}
