package com.example.revisaospring.repositories;

import com.example.revisaospring.entities.Topico;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {
    List<Topico> findByCursoNome(String nomeCurso, Pageable paginacao);

}
