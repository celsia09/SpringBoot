package com.example.revisaospring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "cursos")
public class Curso{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pkCurso;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "categoria", nullable = false)
    private String categoria;


    public Curso(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }
}
