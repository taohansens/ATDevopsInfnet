package com.taohansen.springwebclient.controller;

import com.taohansen.springwebclient.model.Result;
import com.taohansen.springwebclient.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping("/{acao}")
    public Mono<Result> getStock(@PathVariable String acao) {
        return stockService.getStockMarket(acao);
    }
}