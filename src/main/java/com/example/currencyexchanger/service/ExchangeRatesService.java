package com.example.currencyexchanger.service;

import com.example.currencyexchanger.Models.Exchange;
import com.example.currencyexchanger.Models.ExchangeRate;
import com.example.currencyexchanger.repositories.ExchangeRateRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExchangeRatesService {

    private static ExchangeRateRepo exchangeRateRepo;

    @Autowired
    public ExchangeRatesService(ExchangeRateRepo exchangeRateRepo) {
        ExchangeRatesService.exchangeRateRepo = exchangeRateRepo;
    }

    public static String getAllRates() {
        Iterable<ExchangeRate> exchangeRateIterable = exchangeRateRepo.findAll();
        List<ExchangeRate> exchangeRateList = StreamSupport.stream(exchangeRateIterable.spliterator(), false)
                .collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(exchangeRateList);
        } catch (JsonProcessingException e) {
            return "Error converting to JSON";
        }
    }

    public static String getExchangeRate(String codes) {
        String baseCurrencyCode = splitCodes(codes).get(0);
        String targetCurrencyCode = splitCodes(codes).get(1);


        ExchangeRate exchangeRate = findExchangeRate(baseCurrencyCode, targetCurrencyCode);


        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(Objects.requireNonNullElse(exchangeRate, "NO SUCH EXCHANGE RATE HAD BEEN FOUNDED"));
        } catch (JsonProcessingException e) {
            return "Error converting to JSON";
        }
    }

    public static String findExchange(String baseCurrencyCode, String targetCurrencyCode,double amount) {
        Exchange exchange = null;
        Iterable<ExchangeRate> exchangeRateIterable = exchangeRateRepo.findAll();
        List<ExchangeRate> exchangeRateList = StreamSupport.stream(exchangeRateIterable.spliterator(), false).toList();
        List<ExchangeRate> exchangeRateFilteredList = exchangeRateList.stream().filter(exchangeRatefil -> exchangeRatefil.getBase_currency().getCode().equals(baseCurrencyCode)).filter(exchangeRatefil -> exchangeRatefil.getTarget_currency().getCode().equals(targetCurrencyCode)).toList();
        if (!exchangeRateFilteredList.isEmpty()) {
            exchange = new Exchange(CurrenciesService.findByCode(baseCurrencyCode),CurrenciesService.findByCode(targetCurrencyCode),exchangeRateFilteredList.get(0).getExchange_rate(),amount,exchangeRateFilteredList.get(0).getExchange_rate()*amount);
        } else {
            exchangeRateFilteredList = exchangeRateList.stream().filter(exchangeRatefil -> exchangeRatefil.getBase_currency().getCode().equals(targetCurrencyCode)).filter(exchangeRatefil -> exchangeRatefil.getTarget_currency().getCode().equals(baseCurrencyCode)).toList();
            if (!exchangeRateFilteredList.isEmpty()) {
                exchange =new Exchange(CurrenciesService.findByCode(baseCurrencyCode),CurrenciesService.findByCode(targetCurrencyCode),1/exchangeRateFilteredList.get(0).getExchange_rate(),amount,exchangeRateFilteredList.get(0).getExchange_rate()*amount);
            } else {
                List<ExchangeRate> baseCurrencyToUSD = exchangeRateList.stream().filter(exchangeRatefil -> exchangeRatefil.getBase_currency().getCode().equals(baseCurrencyCode)).filter(exchangeRatefil -> exchangeRatefil.getTarget_currency().getCode().equals("USD")).toList();
                List<ExchangeRate> targetCurrencyToUSD = exchangeRateList.stream().filter(exchangeRatefil -> exchangeRatefil.getBase_currency().getCode().equals(targetCurrencyCode)).filter(exchangeRatefil -> exchangeRatefil.getTarget_currency().getCode().equals("USD")).toList();
                if (!baseCurrencyToUSD.isEmpty() && !targetCurrencyToUSD.isEmpty()) {
                    ExchangeRate baseCurrncyToUSDRate = baseCurrencyToUSD.get(0);
                    ExchangeRate targetCurrncyToUSDRate = targetCurrencyToUSD.get(0);
                    double rate = baseCurrncyToUSDRate.getExchange_rate()/targetCurrncyToUSDRate.getExchange_rate();
                    exchange =  new Exchange(CurrenciesService.findByCode(baseCurrencyCode),CurrenciesService.findByCode(targetCurrencyCode),rate,amount,rate*amount);
                }
            }
        }
        if(exchange!=null){
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                return objectMapper.writeValueAsString(exchange);
            } catch (JsonProcessingException e) {
                return "Error converting to JSON"+e.getMessage();
            }
        }
        return null;
    }

    private static ExchangeRate findExchangeRate(String baseCurrencyCode, String targetCurrencyCode) {
        Iterable<ExchangeRate> exchangeRateIterable = exchangeRateRepo.findAll();
        List<ExchangeRate> exchangeRateList = StreamSupport.stream(exchangeRateIterable.spliterator(), false).toList();
        List<ExchangeRate> exchangeRateFilteredList = exchangeRateList.stream().filter(exchangeRate -> exchangeRate.getBase_currency().getCode().equals(baseCurrencyCode)).filter(exchangeRate -> exchangeRate.getTarget_currency().getCode().equals(targetCurrencyCode)).toList();
        return !exchangeRateFilteredList.isEmpty() ? exchangeRateFilteredList.get(0) : null;
    }

    private static ArrayList<String> splitCodes(String str) {
        return new ArrayList<>(Arrays.asList(str.substring(0, 3), str.substring(3)));
    }


}
