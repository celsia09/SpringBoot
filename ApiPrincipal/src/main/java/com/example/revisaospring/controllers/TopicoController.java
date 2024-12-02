package com.example.revisaospring.controllers;
import com.example.revisaospring.dto.DetalhesDTO;
import com.example.revisaospring.dto.DetalhesTopicoDTO;
import com.example.revisaospring.dto.TopicoDTO;
import com.example.revisaospring.form.ActualizacaoTopicoForm;
import com.example.revisaospring.form.TopicoForm;
import com.example.revisaospring.entities.Topico;
import com.example.revisaospring.repositories.CursoRepository;
import com.example.revisaospring.repositories.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    //lista todos os topicos
    /*@GetMapping
    public Page<TopicoDTO> lista(){
        List<Topico> topicos = topicoRepository.findAll();
        return TopicoDTO.converter(topicos);
    }*/


    //em topicos, procura curso por nome
    @GetMapping("/curso")
    @SecurityRequirement(name = "jwt_auth")
    @Cacheable(value = "listaDeTopicos")
    public Page<TopicoDTO> listarnomeCurso(@RequestParam(required = false) String nomeCurso,
                                           @PageableDefault(sort = "titulo", direction = Sort.Direction.ASC) Pageable paginacao){

        if (nomeCurso == null){
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDTO.converter(topicos);
        }else{
            Page<Topico> topicos = (Page<Topico>) topicoRepository.findByCursoNome(nomeCurso, paginacao);
            return TopicoDTO.converter(topicos);
        }
    }

    @PostMapping
    @SecurityRequirement(name = "jwt_auth")
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    //Essa notação cacheevict limpa o cache para que os dados
    // venham actualizados
    public ResponseEntity<TopicoDTO>cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder){
        Topico topico = topicoForm.converter(cursoRepository);
        topicoRepository.save(topico);
        //URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        //return ResponseEntity.created(uri).body(new TopicoDTO(topico));
        return ResponseEntity.ok(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<DetalhesDTO> detalhar(@PathVariable int id){
        Optional<Topico> topico = this.topicoRepository.findById(id);
        if (topico.isPresent()){
            System.out.println(topico.get().getTitulo());
            DetalhesDTO dt = new DetalhesDTO(topico.get().getPkTopico(),topico.get().getTitulo(), topico.get().getMensagem(), topico.get().getDataCriacao());
            System.out.println(dt.mensagem());
            return ResponseEntity.ok(dt);
        }
        return ResponseEntity.notFound().build();

    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> actualizar(@PathVariable int id, @RequestBody @Valid ActualizacaoTopicoForm actualizarForm){
        Optional <Topico> topic = topicoRepository.findById(id);
        if (topic.isPresent()){
            Topico topico = actualizarForm.actualizar(id,topicoRepository );
            return ResponseEntity.ok(new TopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();

    }


    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable int id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
