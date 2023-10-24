package com.example.currencyexchanger.Models;

import jakarta.persistence.*;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String code;
    private String full_name;
    private String sign;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Currency() {
    }

    public long getId() {
        return id;
    }

    public Currency(String code, String full_name, String sign) {
        this.code = code;
        this.full_name = full_name;
        this.sign = sign;
    }

}