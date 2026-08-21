package com.example.AICrypto_trader.market;



import com.example.AICrypto_trader.common.digitalMoney.CryptoCandle;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class BinanceKlineService {

    private final RestClient restClient;

    public BinanceKlineService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.binance.com")
                .build();
    }

    public List<CryptoCandle> getKlines(
            String symbol,
            String interval,
            int limit
    ) {

        List<List<Object>> response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v3/klines")
                        .queryParam("symbol", symbol)
                        .queryParam("interval", interval)
                        .queryParam("limit", limit)
                        .build())
                .retrieve()
                .body(List.class);

        List<CryptoCandle> candles = new ArrayList<>();

        if (response != null) {

            for (List<Object> candle : response) {

                CryptoCandle cryptoCandle =
                        new CryptoCandle();

                cryptoCandle.setOpenTime(
                        ((Number) candle.get(0)).longValue()
                );

                cryptoCandle.setOpen(
                        Double.parseDouble(
                                candle.get(1).toString()
                        )
                );

                cryptoCandle.setHigh(
                        Double.parseDouble(
                                candle.get(2).toString()
                        )
                );

                cryptoCandle.setLow(
                        Double.parseDouble(
                                candle.get(3).toString()
                        )
                );

                cryptoCandle.setClose(
                        Double.parseDouble(
                                candle.get(4).toString()
                        )
                );

                cryptoCandle.setVolume(
                        Double.parseDouble(
                                candle.get(5).toString()
                        )
                );

                candles.add(cryptoCandle);
            }
        }

        return candles;
    }
}