package com.example.revisaospring.dto;

import com.example.revisaospring.entities.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter

public class TopicoDTO {

    private int pkTopico;
    @NotNull @NotBlank
    private String titulo;
    @NotNull @NotBlank
    public String mensagem;

    public LocalDateTime dataCriacao;

    public TopicoDTO(Topico topico) {
        this.pkTopico = topico.getPkTopico();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();

    }

    public static Page<TopicoDTO> converter(Page<Topico> topicos) {
        return topicos.map(TopicoDTO::new);
    }

}
