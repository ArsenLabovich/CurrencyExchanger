package com.example.currencyexchanger.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;

public class Exchange {
    Currency base_Currency;
    Currency target_Currency;
    double exchange_rate;
    double amount;
    double convertedAmount;

    public Currency getBase_Currency() {
        return base_Currency;
    }

    public void setBase_Currency(Currency base_Currency) {
        this.base_Currency = base_Currency;
    }

    public Currency getTarget_Currency() {
        return target_Currency;
    }

    public void setTarget_Currency(Currency target_Currency) {
        this.target_Currency = target_Currency;
    }

    public double getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public Exchange(Currency base_Currency, Currency target_Currency, double exchange_rate, double amount, double convertedAmount) {
        this.base_Currency = base_Currency;
        this.target_Currency = target_Currency;
        this.exchange_rate = exchange_rate;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
    }
}
