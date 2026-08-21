package com.example.AICrypto_trader.market;


import com.example.AICrypto_trader.ai.MarketFeatures;
import com.example.AICrypto_trader.common.digitalMoney.CryptoCandle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketAnalysisService {

    private final BinanceKlineService binanceKlineService;

    private final IndicatorService indicatorService;

    public MarketAnalysisService(
            BinanceKlineService binanceKlineService,
            IndicatorService indicatorService
    ) {
        this.binanceKlineService =
                binanceKlineService;

        this.indicatorService =
                indicatorService;
    }

    public MarketFeatures analyzeMarket(
            String symbol
    ) {

        // Get historical candles
        List<CryptoCandle> candles =
                binanceKlineService.getKlines(
                        symbol,
                        "1m",
                        200
                );

        if (candles.size() < 50) {

            throw new IllegalStateException(
                    "Not enough candle data"
            );
        }

        // Extract closing prices
        List<Double> closes =
                candles.stream()
                        .map(CryptoCandle::getClose)
                        .toList();

        // =====================================================
        // Calculate indicators
        // =====================================================

        double price =
                closes.get(closes.size() - 1);

        double rsi =
                indicatorService.calculateRSI(
                        closes,
                        14
                );

        double ema20 =
                indicatorService.calculateEMA(
                        closes,
                        20
                );

        double ema50 =
                indicatorService.calculateEMA(
                        closes,
                        50
                );

        double macd =
                indicatorService.calculateMACD(
                        closes
                );

        double signalLine =
                indicatorService.calculateMACDSignal(
                        closes
                );

        double volume =
                candles.get(candles.size() - 1)
                        .getVolume();

        // =====================================================
        // Create MarketFeatures
        // =====================================================

        MarketFeatures features =
                new MarketFeatures();

        features.setSymbol(symbol);

        features.setPrice(price);

        features.setRsi(rsi);

        features.setEma20(ema20);

        features.setEma50(ema50);

        features.setMacd(macd);

        features.setSignalLine(signalLine);

        features.setVolume(volume);

        return features;
    }
}