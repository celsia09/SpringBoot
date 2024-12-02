package com.example.revisaospring.services.implementation;

import com.example.revisaospring.entities.Topico;
import com.example.revisaospring.services.TopicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoServiceImpl extends AbstractService<Topico,Integer> implements TopicoService {

    public Page<Topico> findByCursoNome(String nomeCurso, Pageable paginacao) {
        return null;

    }


}
