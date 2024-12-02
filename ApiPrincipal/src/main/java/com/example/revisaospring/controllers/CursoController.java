package com.example.revisaospring.controllers;

import com.example.revisaospring.entities.Curso;
import com.example.revisaospring.services.implementation.CursoServiceImpl;
import com.example.revisaospring.utils.ResponseBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("curso")
public class CursoController extends BaseController {

    @Autowired
    private CursoServiceImpl service;

    @GetMapping
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseBody> listar(){
        return this.ok("Cursos listados com sucesso", this.service.findAll());
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseBody> buscarPorId(@PathVariable int id){
        Optional<Curso> curso = this.service.findById(id);
        if(curso.isPresent())
            return this.ok("Curso encontrado com sucesso", curso.get());
        return this.naoEncontrado("Curso nao encontrado", null);
    }

    @PostMapping
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseBody> criar(@RequestBody Curso curso) {
        System.out.println(curso);
        return this.ok("Curso adicionado com sucesso", this.service.criar(curso));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseBody> eliminar(@PathVariable int id) {
        return this.ok("Curso eliminado com sucesso", this.service.eliminar(id));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<?> atualizar(@PathVariable int id, @RequestBody Curso curso) {
        if (this.service.findById(id).isPresent()) {
            return this.ok("Curso atualizado com sucesso", this.service.editar(id, curso));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Curso não encontrado"));
    }


}

