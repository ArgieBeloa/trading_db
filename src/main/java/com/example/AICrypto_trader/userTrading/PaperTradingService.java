package com.example.AICrypto_trader.userTrading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class PaperTradingService {

    private static final Logger log =
            LoggerFactory.getLogger(PaperTradingService.class);

    private static final double PROFIT_TARGET = 0.30;

    private double deposit;
    private double cashBalance;
    private double cryptoQuantity;
    private double entryPrice;

    // ==========================
    // START AUTOMATICALLY
    // ==========================

    @PostConstruct
    public void initializePaperAccount() {

        start(10_000);

        log.info("========================================");
        log.info("PAPER TRADING ACCOUNT STARTED");
        log.info("Initial Deposit: $10,000");
        log.info("Profit Target: 30%");
        log.info("Target Balance: $13,000");
        log.info("========================================");
    }

    // ==========================
    // START ACCOUNT
    // ==========================

    public void start(double deposit) {

        if (deposit <= 0) {
            throw new IllegalArgumentException(
                    "Deposit must be greater than 0"
            );
        }

        this.deposit = deposit;
        this.cashBalance = deposit;
        this.cryptoQuantity = 0;
        this.entryPrice = 0;
    }

    // ==========================
    // EXECUTE TRADE
    // ==========================

    public TradingResult execute(
            TradingAction action,
            double currentPrice
    ) {

        if (deposit <= 0) {
            throw new IllegalStateException(
                    "Paper trading account has not been started"
            );
        }

        if (currentPrice <= 0) {
            throw new IllegalArgumentException(
                    "Price must be greater than 0"
            );
        }

        log.info("========================================");
        log.info("TRADING ACTION: {}", action);
        log.info("Current Price: ${}", currentPrice);

        switch (action) {

            case BUY:

                log.info("🟢 BUY SIGNAL");

                buy(currentPrice);

                break;

            case SELL:

                log.info("🔴 SELL SIGNAL");

                sell(currentPrice);

                break;

            case HOLD:

                log.info("🟡 HOLD SIGNAL");
                log.info("No trade executed.");

                break;
        }

        double portfolioValue =
                cashBalance +
                        (cryptoQuantity * currentPrice);

        double profit =
                portfolioValue - deposit;

        double profitPercent =
                (profit / deposit) * 100;

        double targetBalance =
                deposit * (1 + PROFIT_TARGET);

        boolean targetReached =
                portfolioValue >= targetBalance;

        log.info("Cash Balance: ${}", cashBalance);
        log.info("Crypto Quantity: {}", cryptoQuantity);
        log.info("Portfolio Value: ${}", portfolioValue);
        log.info("Profit: ${}", profit);
        log.info("Profit: {}%", profitPercent);
        log.info("Target Balance: ${}", targetBalance);

        if (targetReached) {

            log.warn("🎯 30% PROFIT TARGET REACHED");
            log.warn("Current Portfolio: ${}",
                    portfolioValue);
        }

        log.info("========================================");

        return new TradingResult(
                action,
                currentPrice,
                deposit,
                cashBalance,
                cryptoQuantity,
                portfolioValue,
                profit,
                profitPercent,
                targetBalance,
                targetReached
        );
    }

    // ==========================
    // BUY
    // ==========================

    private void buy(double currentPrice) {

        if (cryptoQuantity > 0) {

            log.info("Already holding crypto.");
            return;
        }

        if (cashBalance <= 0) {

            log.warn("No cash available to BUY.");
            return;
        }

        double amountToBuy = cashBalance;

        cryptoQuantity =
                amountToBuy / currentPrice;

        entryPrice = currentPrice;

        cashBalance = 0;

        log.info("Bought: {} crypto",
                cryptoQuantity);

        log.info("Entry Price: ${}",
                entryPrice);
    }

    // ==========================
    // SELL
    // ==========================

    private void sell(double currentPrice) {

        if (cryptoQuantity <= 0) {

            log.info("No crypto position to SELL.");
            return;
        }

        double sellValue =
                cryptoQuantity * currentPrice;

        cashBalance += sellValue;

        log.info("Sold: {} crypto",
                cryptoQuantity);

        log.info("Sell Value: ${}",
                sellValue);

        cryptoQuantity = 0;
        entryPrice = 0;
    }
}