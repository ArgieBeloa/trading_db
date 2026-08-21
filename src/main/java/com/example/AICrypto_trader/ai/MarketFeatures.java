package com.example.AICrypto_trader.ai;


public class MarketFeatures {

    private String symbol;

    private double price;

    private double rsi;

    private double ema20;

    private double ema50;

    private double macd;

    private double signalLine;

    private double volume;

    public MarketFeatures() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRsi() {
        return rsi;
    }

    public void setRsi(double rsi) {
        this.rsi = rsi;
    }

    public double getEma20() {
        return ema20;
    }

    public void setEma20(double ema20) {
        this.ema20 = ema20;
    }

    public double getEma50() {
        return ema50;
    }

    public void setEma50(double ema50) {
        this.ema50 = ema50;
    }

    public double getMacd() {
        return macd;
    }

    public void setMacd(double macd) {
        this.macd = macd;
    }

    public double getSignalLine() {
        return signalLine;
    }

    public void setSignalLine(double signalLine) {
        this.signalLine = signalLine;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}