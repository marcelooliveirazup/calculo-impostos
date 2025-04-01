package com.example.calculo_impostos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculoImpostoRequest {
    private Long tipoImpostoId;
    private Double valorBase;
}

