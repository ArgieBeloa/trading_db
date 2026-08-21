package com.example.AICrypto_trader.userTrading;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/paper-trading")
public class PaperTradingController {

    private final PaperTradingService paperTradingService;

    public PaperTradingController(
            PaperTradingService paperTradingService
    ) {
        this.paperTradingService = paperTradingService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> start(
            @RequestParam double deposit
    ) {

        paperTradingService.start(deposit);

        return ResponseEntity.ok(
                "Paper trading started with $" + deposit
        );
    }

    @PostMapping("/execute")
    public ResponseEntity<TradingResult> execute(
            @RequestParam TradingAction action,
            @RequestParam double price
    ) {

        TradingResult result =
                paperTradingService.execute(
                        action,
                        price
                );

        return ResponseEntity.ok(result);
    }
}