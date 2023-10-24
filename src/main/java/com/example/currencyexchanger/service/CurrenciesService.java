package com.example.currencyexchanger.service;

import com.example.currencyexchanger.Models.Currency;
import com.example.currencyexchanger.repositories.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CurrenciesService {
    private static CurrencyRepo currencyRepo;

    @Autowired
    public CurrenciesService(CurrencyRepo currencyRepo) {
        CurrenciesService.currencyRepo = currencyRepo;
    }

    public static String getAllCurrencies() {
        Iterable<Currency> currencyIterable = currencyRepo.findAll();
        List<Currency> currencyList = StreamSupport.stream(currencyIterable.spliterator(), false)
                .collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(currencyList);
        } catch (JsonProcessingException e) {
            return "Error converting to JSON"+e.toString();
        }
    }

    protected static Currency findByCode(String code) {
        Iterable<Currency> currencyIterable = currencyRepo.findAll();
        List<Currency> currencyList = StreamSupport.stream(currencyIterable.spliterator(), false)
                .toList();
        for (Currency curr : currencyList) {
            if (curr.getCode().equals(code))
                return curr;
        }
        return null;
    }

    public static String getOneCurrency(String code) {


        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(findByCode(code));
        } catch (JsonProcessingException e) {
            return "Error converting to JSON";
        }
    }

    public static void addCurrency(Currency curr) {
        if (findByCode(curr.getCode()) != null){
            return;
        }
        currencyRepo.save(curr);
    }

    public static void deleteCurrency(String code) {
        currencyRepo.deleteById(Objects.requireNonNull(findByCode(code)).getId());
    }

}
