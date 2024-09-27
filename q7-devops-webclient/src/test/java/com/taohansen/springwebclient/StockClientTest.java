package com.taohansen.springwebclient;

import com.taohansen.springwebclient.controller.StockController;
import com.taohansen.springwebclient.model.Result;
import com.taohansen.springwebclient.service.StockService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

@SpringBootTest
@Testcontainers
public class StockClientTest {
    @Container
    public static GenericContainer<?> mockServer = new GenericContainer<>("mockserver/mockserver:latest")
            .withExposedPorts(1080);


    private StockService stockService;

    @BeforeEach
    public void setUp() {
        WebClient.Builder webClientBuilder = WebClient.builder();
        String baseUrl = String.format("http://localhost:%d", mockServer.getFirstMappedPort());
        stockService = new StockService(webClientBuilder.baseUrl(baseUrl));
    }

    @Test
    public void testPETR4() {
        Mono<Result> stockMarket = stockService.getStockMarket("PETR4");

        StepVerifier.create(stockMarket)
                .expectNextMatches(response -> Objects.equals(response.getResults().getFirst().longName, "Petróleo Brasileiro S.A. - Petrobras"))
                .verifyComplete();
    }

    @Test
    public void testBBAS3() {
        Mono<Result> stockMarket = stockService.getStockMarket("BBAS3");

        StepVerifier.create(stockMarket)
                .expectNextMatches(response -> Objects.equals(response.getResults().getFirst().longName, "Banco do Brasil S.A."))
                .verifyComplete();
    }

    @Test
    public void testVALE3() {
        Mono<Result> stockMarket = stockService.getStockMarket("VALE3");

        StepVerifier.create(stockMarket)
                .expectNextMatches(response -> Objects.equals(response.getResults().getFirst().longName, "Vale S.A."))
                .verifyComplete();
    }

}
