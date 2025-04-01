package com.example.calculo_impostos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "impostos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Imposto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double aliquota;
}

