package com.taohansen.springreactiveinfnet.controller;

import com.taohansen.springreactiveinfnet.model.Produto;
import com.taohansen.springreactiveinfnet.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Produto>> update(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.updateProduto(id, produto).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<Produto>> save(@RequestBody Produto produto) {
        return produtoService.save(produto).map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Produto>> get(@PathVariable Long id) {
        return produtoService.findById(id).map(ResponseEntity::ok);
    }

    @DeleteMapping
    public Mono<Void> delete(@RequestParam Long id) {
        return produtoService.deleteById(id);
    }
}
