package com.example.revisaospring.validacao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErroFormulario {
    private String campo;
    private String mensagem;
}
