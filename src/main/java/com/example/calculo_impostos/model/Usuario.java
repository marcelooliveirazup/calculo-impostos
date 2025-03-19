package com.example.calculo_impostos.model;

import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String passWord;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}


