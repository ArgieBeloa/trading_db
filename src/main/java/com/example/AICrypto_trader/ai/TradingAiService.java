package com.example.AICrypto_trader.ai;

import com.example.AICrypto_trader.userTrading.TradingAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TradingAiService {

    private static final Logger log =
            LoggerFactory.getLogger(TradingAiService.class);

    public TradingAction analyze(MarketFeatures market) {

        double rsi = market.getRsi();
        double ema20 = market.getEma20();
        double ema50 = market.getEma50();
        double macd = market.getMacd();
        double signal = market.getSignalLine();

        TradingAction decision;

        // ==========================
        // BUY CONDITIONS
        // ==========================

        if (rsi < 30 &&
                macd > signal &&
                ema20 > ema50) {

            decision = TradingAction.BUY;

            // ==========================
            // SELL CONDITIONS
            // ==========================

        } else if (rsi > 70 &&
                macd < signal &&
                ema20 < ema50) {

            decision = TradingAction.SELL;

            // ==========================
            // HOLD
            // ==========================

        } else {

            decision = TradingAction.HOLD;
        }

        // ==========================
        // LOG ANALYSIS
        // ==========================

        log.info("");
        log.info("========== AI TRADING ANALYSIS ==========");
        log.info("Symbol: {}", market.getSymbol());
        log.info("Price: ${}", market.getPrice());
        log.info("RSI: {}", rsi);
        log.info("EMA20: {}", ema20);
        log.info("EMA50: {}", ema50);
        log.info("MACD: {}", macd);
        log.info("Signal: {}", signal);

        log.info("");
        log.info("AI DECISION: {}", decision);

        log.info("");
        log.info("Reason:");

        if (rsi < 30) {
            log.info("RSI < 30");
        } else if (rsi > 70) {
            log.info("RSI > 70");
        } else {
            log.info("RSI is between 30 and 70");
        }

        if (macd > signal) {
            log.info("MACD > Signal");
        } else if (macd < signal) {
            log.info("MACD < Signal");
        } else {
            log.info("MACD = Signal");
        }

        if (ema20 > ema50) {
            log.info("EMA20 > EMA50");
        } else if (ema20 < ema50) {
            log.info("EMA20 < EMA50");
        } else {
            log.info("EMA20 = EMA50");
        }

        log.info("");
        log.info("ACTION: {}", decision);
        log.info("==========================================");

        return decision;
    }
}