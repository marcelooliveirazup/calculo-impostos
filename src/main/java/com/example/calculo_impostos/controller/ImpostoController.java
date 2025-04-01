package com.example.calculo_impostos.controller;

import com.example.calculo_impostos.dto.*;
import com.example.calculo_impostos.service.ImpostoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/tipos")
@RequiredArgsConstructor
public class ImpostoController {

    private final ImpostoService impostoService;

    @GetMapping
    public ResponseEntity<List<ImpostoResponse>> listarImpostos() {
        return ResponseEntity.ok(impostoService.listarImpostos());
    }

    @PostMapping
    public ResponseEntity<ImpostoResponse> cadastrarImposto(@RequestBody ImpostoRequest request) {
        return ResponseEntity.status(201).body(impostoService.cadastrarImposto(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImpostoResponse> obterImposto(@PathVariable Long id) {
        return ResponseEntity.ok(impostoService.obterImpostoPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirImposto(@PathVariable Long id) {
        impostoService.excluirImposto(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/calculo")
    public ResponseEntity<CalculoImpostoResponse> calcularImposto(@RequestBody CalculoImpostoRequest request) {
        return ResponseEntity.ok(impostoService.calcularImposto(request));
    }
}

