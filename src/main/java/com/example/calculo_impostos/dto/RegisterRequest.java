package com.example.calculo_impostos.dto;

//import java.util.TerminatingThreadLocal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest extends ThreadLocal<Object> {
private String username;
    private String password;
    private String role;
}

