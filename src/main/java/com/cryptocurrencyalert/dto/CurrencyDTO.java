package com.cryptocurrencyalert.dto;

public class CurrencyDTO {
    private String symbol;
    private float price;

    public CurrencyDTO(){}

    public CurrencyDTO(String symbol, float price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CurrencyDTO{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}
