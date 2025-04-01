package com.example.calculo_impostos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImpostoResponse {
    private Long id;
    private String nome;
    private String descricao;
    private Double aliquota;
}

