package com.example.calculo_impostos.service;

import com.example.calculo_impostos.dto.CalculoImpostoRequest;
import com.example.calculo_impostos.dto.CalculoImpostoResponse;
import com.example.calculo_impostos.dto.ImpostoRequest;
import com.example.calculo_impostos.dto.ImpostoResponse;
import com.example.calculo_impostos.model.Imposto;
import com.example.calculo_impostos.repository.ImpostoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImpostoServiceTest {

    @InjectMocks
    private ImpostoService impostoService;

    @Mock
    private ImpostoRepository impostoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveListarTodosOsImpostos() {
        when(impostoRepository.findAll()).thenReturn(Arrays.asList(
                new Imposto(1L, "ICMS", "Imposto sobre Circulação", 18.0),
                new Imposto(2L, "ISS", "Imposto sobre Serviços", 5.0)
        ));

        List<ImpostoResponse> impostos = impostoService.listarImpostos();

        assertEquals(2, impostos.size());
        assertEquals("ICMS", impostos.get(0).getNome());
    }

    @Test
    void deveCadastrarImposto() {
        ImpostoRequest request = new ImpostoRequest();
        request.setNome("IPI");
        request.setDescricao("Imposto sobre Produtos Industrializados");
        request.setAliquota(12.0);

        Imposto imposto = new Imposto(3L, "IPI", "Imposto sobre Produtos Industrializados", 12.0);
        when(impostoRepository.save(any(Imposto.class))).thenReturn(imposto);

        ImpostoResponse response = impostoService.cadastrarImposto(request);

        assertNotNull(response);
        assertEquals("IPI", response.getNome());
    }

    @Test
    void deveObterImpostoPorId() {
        when(impostoRepository.findById(1L)).thenReturn(Optional.of(new Imposto(1L, "ICMS", "Imposto sobre Circulação", 18.0)));

        ImpostoResponse response = impostoService.obterImpostoPorId(1L);

        assertNotNull(response);
        assertEquals("ICMS", response.getNome());
    }

    @Test
    void deveExcluirImposto() {
        when(impostoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(impostoRepository).deleteById(1L);

        assertDoesNotThrow(() -> impostoService.excluirImposto(1L));
        verify(impostoRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveCalcularImposto() {
        CalculoImpostoRequest request = new CalculoImpostoRequest();
        request.setTipoImpostoId(1L);
        request.setValorBase(1000.0);

        when(impostoRepository.findById(1L)).thenReturn(Optional.of(new Imposto(1L, "ICMS", "Imposto sobre Circulação", 18.0)));

        CalculoImpostoResponse response = impostoService.calcularImposto(request);

        assertEquals(180.0, response.getValorImposto());
    }
}

