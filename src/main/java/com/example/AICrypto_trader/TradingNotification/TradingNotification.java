package com.example.AICrypto_trader.TradingNotification;


import com.example.AICrypto_trader.userTrading.TradingAction;

public class TradingNotification {

    private String symbol;
    private double price;
    private TradingAction action;
    private String message;

    public TradingNotification(
            String symbol,
            double price,
            TradingAction action,
            String message
    ) {
        this.symbol = symbol;
        this.price = price;
        this.action = action;
        this.message = message;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public TradingAction getAction() {
        return action;
    }

    public String getMessage() {
        return message;
    }
}