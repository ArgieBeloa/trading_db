package com.example.AICrypto_trader.market;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndicatorService {

    // =========================================================
    // EMA
    // =========================================================

    public double calculateEMA(
            List<Double> prices,
            int period
    ) {

        if (prices.size() < period) {
            throw new IllegalArgumentException(
                    "Not enough prices to calculate EMA"
            );
        }

        double multiplier =
                2.0 / (period + 1);

        // Initial SMA
        double ema = prices
                .subList(0, period)
                .stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);

        // EMA calculation
        for (int i = period; i < prices.size(); i++) {

            ema =
                    ((prices.get(i) - ema)
                            * multiplier)
                            + ema;
        }

        return ema;
    }


    // =========================================================
    // RSI
    // =========================================================

    public double calculateRSI(
            List<Double> prices,
            int period
    ) {

        if (prices.size() <= period) {
            throw new IllegalArgumentException(
                    "Not enough prices to calculate RSI"
            );
        }

        double gain = 0;
        double loss = 0;

        for (int i = 1; i <= period; i++) {

            double change =
                    prices.get(i)
                            - prices.get(i - 1);

            if (change > 0) {

                gain += change;

            } else {

                loss -= change;
            }
        }

        double averageGain =
                gain / period;

        double averageLoss =
                loss / period;

        if (averageLoss == 0) {
            return 100;
        }

        double rs =
                averageGain / averageLoss;

        return 100 - (100 / (1 + rs));
    }


    // =========================================================
    // MACD
    // =========================================================

    public double calculateMACD(
            List<Double> prices
    ) {

        double ema12 =
                calculateEMA(prices, 12);

        double ema26 =
                calculateEMA(prices, 26);

        return ema12 - ema26;
    }


    // =========================================================
    // MACD SIGNAL LINE
    // =========================================================

    public double calculateMACDSignal(
            List<Double> prices
    ) {

        List<Double> macdValues =
                calculateMACDValues(prices);

        return calculateEMA(
                macdValues,
                9
        );
    }


    // =========================================================
    // Calculate MACD for every candle
    // =========================================================

    private List<Double> calculateMACDValues(
            List<Double> prices
    ) {

        List<Double> macdValues =
                new ArrayList<>();

        for (int i = 26; i < prices.size(); i++) {

            List<Double> subset =
                    prices.subList(0, i + 1);

            double ema12 =
                    calculateEMA(subset, 12);

            double ema26 =
                    calculateEMA(subset, 26);

            double macd =
                    ema12 - ema26;

            macdValues.add(macd);
        }

        return macdValues;
    }
}