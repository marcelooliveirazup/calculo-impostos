package com.example.calculo_impostos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImpostoRequest {
    private String nome;
    private String descricao;
    private Double aliquota;
}

