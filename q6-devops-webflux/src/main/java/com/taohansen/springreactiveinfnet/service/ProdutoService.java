package com.taohansen.springreactiveinfnet.service;

import com.taohansen.springreactiveinfnet.model.Produto;
import com.taohansen.springreactiveinfnet.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Mono<Produto> updateProduto(Long id, Produto produto) {
        return produtoRepository.findById(id)
                .flatMap(existingProduto -> {
                    existingProduto.setNome(produto.getNome());
                    existingProduto.setPreco(produto.getPreco());
                    return produtoRepository.save(existingProduto);
                })
                .switchIfEmpty(Mono.error(new Exception("Produto não encontrado")));
    }

    public Mono<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public Mono<Produto> save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Mono<Void> deleteById(Long id) {
        return produtoRepository.deleteById(id);
    }
}
