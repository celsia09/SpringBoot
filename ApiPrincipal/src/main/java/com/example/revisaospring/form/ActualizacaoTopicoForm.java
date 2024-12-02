package com.example.revisaospring.form;

import com.example.revisaospring.entities.Topico;
import com.example.revisaospring.repositories.TopicoRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ActualizacaoTopicoForm {

    @NotNull
    @NotBlank
    private String titulo;

    @NotNull
    @NotBlank
    private String mensagem;

    public Topico actualizar(int pkTopico, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.findById(pkTopico).get();
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);
        return topico;
    }
}
