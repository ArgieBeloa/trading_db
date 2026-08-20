package com.example.AICrypto_trader.market;

import com.example.AICrypto_trader.common.digitalMoney.CryptoTicker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BinanceService {

    private final RestClient restClient;

    public BinanceService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.binance.com")
                .build();
    }

    public CryptoTicker getTicker(String symbol) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v3/ticker/24hr")
                        .queryParam("symbol", symbol)
                        .build())
                .retrieve()
                .body(CryptoTicker.class);
    }
}