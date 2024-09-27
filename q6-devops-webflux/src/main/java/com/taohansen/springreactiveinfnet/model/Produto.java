package com.taohansen.springreactiveinfnet.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("produtos")
@Data
public class Produto {
    @Id
    private Long id;
    private String nome;
    private double preco;
}
