package com.example.AICrypto_trader.userTrading;

public class TradingResult {

    private TradingAction action;
    private double price;

    private double deposit;
    private double cashBalance;
    private double cryptoQuantity;

    private double portfolioValue;
    private double profit;
    private double profitPercent;

    private double targetBalance;
    private boolean targetReached;

    public TradingResult(
            TradingAction action,
            double price,
            double deposit,
            double cashBalance,
            double cryptoQuantity,
            double portfolioValue,
            double profit,
            double profitPercent,
            double targetBalance,
            boolean targetReached
    ) {

        this.action = action;
        this.price = price;
        this.deposit = deposit;
        this.cashBalance = cashBalance;
        this.cryptoQuantity = cryptoQuantity;
        this.portfolioValue = portfolioValue;
        this.profit = profit;
        this.profitPercent = profitPercent;
        this.targetBalance = targetBalance;
        this.targetReached = targetReached;
    }

    public TradingAction getAction() {
        return action;
    }

    public double getPrice() {
        return price;
    }

    public double getDeposit() {
        return deposit;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public double getCryptoQuantity() {
        return cryptoQuantity;
    }

    public double getPortfolioValue() {
        return portfolioValue;
    }

    public double getProfit() {
        return profit;
    }

    public double getProfitPercent() {
        return profitPercent;
    }

    public double getTargetBalance() {
        return targetBalance;
    }

    public boolean isTargetReached() {
        return targetReached;
    }
}