package com.example.currencyexchanger.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Currency base_currency;
    @OneToOne
    private Currency target_currency;
    private double exchange_rate;


    public long getId() {
        return id;
    }

    public Currency getBase_currency() {
        return base_currency;
    }

    public void setBase_currency(Currency base_currency) {
        this.base_currency = base_currency;
    }

    public Currency getTarget_currency() {
        return target_currency;
    }

    public void setTarget_currency(Currency target_currency) {
        this.target_currency = target_currency;
    }

    public double getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public ExchangeRate(Currency base_currency, Currency target_currency, double exchange_rate) {
        this.base_currency = base_currency;
        this.target_currency = target_currency;
        this.exchange_rate = exchange_rate;
    }

    public ExchangeRate() {
    }
}
