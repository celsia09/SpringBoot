package com.example.revisaospring.dto;

import java.time.LocalDateTime;

public record DetalhesDTO(
        int pkTopico,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao
        //String nomeAutor
        //String status,
        //List<RespostaDTO> respostas
){

}
