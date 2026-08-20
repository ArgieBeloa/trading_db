package com.example.AICrypto_trader.market;

import com.example.AICrypto_trader.common.digitalMoney.CryptoTicker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final BinanceService binanceService;

    public CryptoController(BinanceService binanceService) {
        this.binanceService = binanceService;
    }

    @GetMapping
    public List<CryptoTicker> getCryptos() {

        List<String> symbols = List.of(
                "BTCUSDT",
                "ETHUSDT",
                "SOLUSDT",
                "BNBUSDT",
                "XRPUSDT"
        );

        return symbols.stream()
                .map(binanceService::getTicker)
                .toList();
    }

    @GetMapping("/{symbol}")
    public CryptoTicker getCrypto(
            @PathVariable String symbol
    ) {
        return binanceService.getTicker(
                symbol.toUpperCase()
        );
    }
}