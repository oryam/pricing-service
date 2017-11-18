package com.abb.demo.pricing.domain.common;

import java.util.Date;

public class Stock {

    public static final int STOCK_SIZE = 30;

    private final Long id;
    private final String code;
    private Double price;
    private Date date;

    private Stock(long id, String code, double price, Date date) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.date = date;
    }

    public static Stock of(long id, String code, double price, Date date) {
        return new Stock(id, code, price, date);
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public void updatePrice(double changedPrice) {
        this.price = changedPrice;
        this.date = new Date();
    }

}
