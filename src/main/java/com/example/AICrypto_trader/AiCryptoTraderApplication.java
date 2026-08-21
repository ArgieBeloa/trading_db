package com.example.AICrypto_trader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AiCryptoTraderApplication {
	public static void main(String[] args) {

		SpringApplication.run(AiCryptoTraderApplication.class, args);
	}
}
