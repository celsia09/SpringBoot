package com.example.revisaospring.dto;

import com.example.revisaospring.entities.Resposta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RespostaDTO {

    private int pkResposta;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public RespostaDTO(Resposta resposta) {
        this.pkResposta = resposta.getPkResposta();
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataResposta();
        this.nomeAutor = resposta.getUsuario().getEmail();
    }


}
