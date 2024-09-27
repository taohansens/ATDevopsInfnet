package com.taohansen.springwebclient.service;

import com.taohansen.springwebclient.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockService {
    private final WebClient.Builder webClientBuilder;

    public Mono<Result> getStockMarket(String acao) {
        String baseUrl = "https://brapi.dev/api/quote/" + acao + "?token=" + "xz6PFvnzNN9VPsUkJkcGTt";
        WebClient webClient = webClientBuilder.baseUrl(baseUrl).build();
        return webClient.get()
                .retrieve()
                .bodyToMono(Result.class);
    }
}