package com.example.revisaospring.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "respostas")
public class Resposta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pkResposta;

    @Column(name = "mensagem", nullable = false)
    private String mensagem;

    @ManyToOne
    private Topico topico;

    private LocalDateTime dataResposta;

    @ManyToOne
    private Usuario usuario;

    private Boolean solucao = false;


}