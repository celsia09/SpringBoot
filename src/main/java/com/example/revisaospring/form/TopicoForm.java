package com.example.revisaospring.form;


import com.example.revisaospring.entities.Curso;
import com.example.revisaospring.entities.Topico;
import com.example.revisaospring.repositories.CursoRepository;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Optional;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicoForm {

    @NotNull @NotBlank @Length(min = 5)
    private String titulo;

    @NotNull @NotBlank
    private String mensagem;

    @NotNull   @NotBlank
    private String nomeCurso;


    public Topico converter(CursoRepository cursoRepository){
        Optional<Curso> curso = cursoRepository.findByNome(nomeCurso);

        return new Topico(titulo, mensagem, curso.orElse(null));
    }
}
