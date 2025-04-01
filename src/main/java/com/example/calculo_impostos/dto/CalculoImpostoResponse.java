package com.example.calculo_impostos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculoImpostoResponse {
    private String tipoImposto;
    private Double valorBase;
    private Double aliquota;
    private Double valorImposto;
}

