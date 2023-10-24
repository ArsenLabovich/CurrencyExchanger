package com.example.currencyexchanger.repositories;

import com.example.currencyexchanger.Models.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepo extends CrudRepository<Currency, Long> {
}
