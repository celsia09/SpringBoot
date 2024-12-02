package com.example.revisaospring.dto;

import com.example.revisaospring.entities.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesTopicoDTO {
    private int pkTopico;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;
    private String status;
    private List<RespostaDTO> respostas;

    public DetalhesTopicoDTO(Topico topico) {
        this.pkTopico = topico.getPkTopico();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.nomeAutor = topico.getUsuario().getEmail();
        this.status = topico.getStatus().toString();
        this.respostas = new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));

    }


}
