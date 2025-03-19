package com.example.calculo_impostos.repository;

import com.example.calculo_impostos.model.TipoImposto;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TipoImpostoRepository extends JpaRepository<TipoImposto, Long> {
}

