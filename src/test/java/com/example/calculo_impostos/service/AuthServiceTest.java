package com.example.calculo_impostos.service;

import com.example.calculo_impostos.dto.LoginRequest;
import com.example.calculo_impostos.dto.RegisterRequest;
import com.example.calculo_impostos.model.Usuario;
import com.example.calculo_impostos.repository.UsuarioRepository;
import com.example.calculo_impostos.security.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRegistrarUsuarioComSucesso() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("usuario123");
        request.setPassword("senhaSegura");
        request.setRole("USER");

        when(passwordEncoder.encode(anyString())).thenReturn("senhaHash");
        when(usuarioRepository.save(any())).thenReturn(new Usuario(1L), "usuario123", "senhaHash", Role.USER());

        assertDoesNotThrow(() -> authService.registrar(request));
        verify(usuarioRepository, times(1)).save(any());
    }

    @Test
void deveAutenticarEDevolverToken() {
        LoginRequest request = new LoginRequest("usuario123", "senhaSegura");

        Usuario usuario = new Usuario(1L, "usuario123", "senhaHash", "USER");

        when(usuarioRepository.findByUsername("usuario123")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("senhaSegura", "senhaHash")).thenReturn(true);
        when(jwtService.gerarToken(usuario)).thenReturn("mocked-jwt-token");

        String token = authService.autenticar(request);

        assertNotNull(token);
        assertEquals("mocked-jwt-token", token);
    }
}

