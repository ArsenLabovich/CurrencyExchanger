package com.example.currencyexchanger.repositories;

import com.example.currencyexchanger.Models.ExchangeRate;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRateRepo extends CrudRepository<ExchangeRate, Long> {
}
