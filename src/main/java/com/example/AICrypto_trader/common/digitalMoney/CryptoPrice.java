package com.example.AICrypto_trader.common.digitalMoney;

public class CryptoPrice {

    private String symbol;
    private String price;

    public CryptoPrice() {
    }

    public CryptoPrice(String symbol, String price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
