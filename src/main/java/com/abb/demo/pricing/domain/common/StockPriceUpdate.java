package com.abb.demo.pricing.domain.common;

public class StockPriceUpdate {

    private final long id;
    private final double price;

    public StockPriceUpdate(long id, double price) {
        this.id = id;
        this.price = price;
    }

    public static StockPriceUpdate of(long id, double price) {
        return new StockPriceUpdate(id, price);
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

}
