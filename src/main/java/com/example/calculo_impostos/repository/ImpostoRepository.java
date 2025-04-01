package com.example.calculo_impostos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.calculo_impostos.model.Imposto;

@Repository
public interface ImpostoRepository extends JpaRepository<Imposto, Long> {
}

