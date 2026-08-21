package com.example.AICrypto_trader.TradingNotification;

import com.example.AICrypto_trader.ai.MarketFeatures;
import com.example.AICrypto_trader.userTrading.TradingAction;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TradingNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public TradingNotificationService(
            SimpMessagingTemplate messagingTemplate
    ) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifySignal(
            MarketFeatures market,
            TradingAction action
    ) {

        TradingNotification notification =
                new TradingNotification(
                        market.getSymbol(),
                        market.getPrice(),
                        action,
                        action == TradingAction.SELL
                                ? "SELL SIGNAL"
                                : action.toString()
                );

        messagingTemplate.convertAndSend(
                "/topic/trading",
                notification
        );
    }
}