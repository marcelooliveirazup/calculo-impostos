package com.example.calculo_impostos.service;

import com.example.calculo_impostos.dto.*;
import com.example.calculo_impostos.model.Imposto;
import com.example.calculo_impostos.repository.ImpostoRepository;

import org.springframework.stereotype.Service;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImpostoService {

    private final ImpostoRepository impostoRepository;

    public List<ImpostoResponse> listarImpostos() {
        return impostoRepository.findAll()
                .stream()
                .map(imposto -> new ImpostoResponse(imposto.getId(), imposto.getNome(), imposto.getDescricao(), imposto.getAliquota()))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ImpostoResponse cadastrarImposto(ImpostoRequest request) {
        Imposto imposto = Imposto.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .aliquota(request.getAliquota())
                .build();

        imposto = impostoRepository.save(imposto);
        return new ImpostoResponse(imposto.getId(), imposto.getNome(), imposto.getDescricao(), imposto.getAliquota());
    }

    public ImpostoResponse obterImpostoPorId(Long id) {
        Imposto imposto = impostoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposto não encontrado"));

        return new ImpostoResponse(imposto.getId(), imposto.getNome(), imposto.getDescricao(), imposto.getAliquota());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void excluirImposto(Long id) {
        if (!impostoRepository.existsById(id)) {
            throw new RuntimeException("Imposto não encontrado");
        }
        impostoRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public CalculoImpostoResponse calcularImposto(CalculoImpostoRequest request) {
        Imposto imposto = impostoRepository.findById(request.getTipoImpostoId())
                .orElseThrow(() -> new RuntimeException("Imposto não encontrado"));

        double valorImposto = request.getValorBase() * (imposto.getAliquota() / 100);

        return new CalculoImpostoResponse(imposto.getNome(), request.getValorBase(), imposto.getAliquota(), valorImposto);
    }
}

