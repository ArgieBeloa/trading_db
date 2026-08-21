package com.example.AICrypto_trader.market;

import com.example.AICrypto_trader.ai.MarketFeatures;
import com.example.AICrypto_trader.ai.TradingAiService;
import com.example.AICrypto_trader.userTrading.TradingAction;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trading")
public class TradingController {

    private final MarketAnalysisService marketAnalysisService;
    private final TradingAiService tradingAiService;

    public TradingController(
            MarketAnalysisService marketAnalysisService,
            TradingAiService tradingAiService
    ) {
        this.marketAnalysisService = marketAnalysisService;
        this.tradingAiService = tradingAiService;
    }

    @GetMapping("/signal/{symbol}")
    public TradingAction getSignal(
            @PathVariable String symbol
    ) {

        MarketFeatures features =
                marketAnalysisService.analyzeMarket(
                        symbol.toUpperCase()
                );

        return tradingAiService.analyze(features);
    }
}