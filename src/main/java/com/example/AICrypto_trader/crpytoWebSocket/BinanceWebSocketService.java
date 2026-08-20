package com.example.AICrypto_trader.crpytoWebSocket;

import jakarta.annotation.PostConstruct;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.*;

@Service
public class BinanceWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public BinanceWebSocketService(
            SimpMessagingTemplate messagingTemplate
    ) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostConstruct
    public void connect() {

        WebSocketClient client =
                new StandardWebSocketClient();

        String url =
                "wss://stream.binance.com:9443/ws/ethusdt@ticker";

        client.execute(
                new WebSocketHandler() {

                    @Override
                    public void afterConnectionEstablished(
                            WebSocketSession session
                    ) {
                        System.out.println(
                                "Connected to Binance"
                        );
                    }

                    @Override
                    public void handleMessage(
                            WebSocketSession session,
                            WebSocketMessage<?> message
                    ) {

                        String data =
                                message.getPayload().toString();

                        System.out.println(data);

                        messagingTemplate.convertAndSend(
                                "/topic/crypto",
                                data
                        );
                    }

                    @Override
                    public void handleTransportError(
                            WebSocketSession session,
                            Throwable exception
                    ) {
                        exception.printStackTrace();
                    }

                    @Override
                    public void afterConnectionClosed(
                            WebSocketSession session,
                            CloseStatus closeStatus
                    ) {
                        System.out.println(
                                "Binance disconnected"
                        );
                    }

                    @Override
                    public boolean supportsPartialMessages() {
                        return false;
                    }
                },
                url
        );
    }
}