package com.example.AICrypto_trader.ai;

import com.example.AICrypto_trader.ai.MarketFeatures;
import com.example.AICrypto_trader.ai.TradingAiService;
import com.example.AICrypto_trader.market.MarketAnalysisService;

import com.example.AICrypto_trader.userTrading.PaperTradingService;
import com.example.AICrypto_trader.userTrading.TradingAction;
import com.example.AICrypto_trader.userTrading.TradingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TradingMonitorService {

    private static final Logger log =
            LoggerFactory.getLogger(TradingMonitorService.class);

    private final MarketAnalysisService marketAnalysisService;
    private final TradingAiService tradingAiService;
    private final PaperTradingService paperTradingService;

    public TradingMonitorService(
            MarketAnalysisService marketAnalysisService,
            TradingAiService tradingAiService,
            PaperTradingService paperTradingService
    ) {
        this.marketAnalysisService = marketAnalysisService;
        this.tradingAiService = tradingAiService;
        this.paperTradingService = paperTradingService;
    }


    @Scheduled(fixedRate = 30000)
    public void monitorBtc() {

        try {

            String symbol = "BTCUSDT";

            MarketFeatures features =
                    marketAnalysisService.analyzeMarket(symbol);

            TradingAction action =
                    tradingAiService.analyze(features);

            double price = features.getPrice();

            log.info("");
            log.info("========== BTC TRADING MONITOR ==========");
            log.info("Symbol: {}", symbol);
            log.info("Price: ${}", price);
            log.info("AI ACTION: {}", action);

            TradingResult result =
                    paperTradingService.execute(
                            action,
                            price
                    );

            if (action == TradingAction.BUY) {
                log.info("🟢 BTC BUY SIGNAL");
            }

            if (action == TradingAction.HOLD) {
                log.info("🟡 BTC HOLD - waiting...");
            }

            if (action == TradingAction.SELL) {
                log.warn("🔴🔴🔴 BTC SELL SIGNAL 🔴🔴🔴");
                log.warn("BTC Price: ${}", price);
                log.warn("Profit: ${}", result.getProfit());
                log.warn("Profit: {}%", result.getProfitPercent());
            }

            if (result.isTargetReached()) {
                log.warn("🎯 BTC 30% PROFIT TARGET REACHED");
                log.warn("Portfolio: ${}", result.getPortfolioValue());
            }

        } catch (Exception e) {
            log.error("Error monitoring BTCUSDT", e);
        }
    }



}