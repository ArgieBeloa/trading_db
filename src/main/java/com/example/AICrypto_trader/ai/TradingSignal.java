package com.example.AICrypto_trader.ai;

public class TradingSignal {

    private String symbol;

    private String decision;

    private double confidence;

    private String reason;

    public TradingSignal() {
    }

    public TradingSignal(
            String symbol,
            String decision,
            double confidence,
            String reason
    ) {
        this.symbol = symbol;
        this.decision = decision;
        this.confidence = confidence;
        this.reason = reason;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDecision() {
        return decision;
    }

    public double getConfidence() {
        return confidence;
    }

    public String getReason() {
        return reason;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
