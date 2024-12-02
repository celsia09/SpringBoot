package com.example.revisaospring.controllers;


import com.example.revisaospring.entities.Usuario;
import com.example.revisaospring.services.implementation.UsuarioServiceImpl;
import com.example.revisaospring.utils.ResponseBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("usuario")

public class UsuarioController extends BaseController {

    @Autowired
    private UsuarioServiceImpl service;


    @GetMapping
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseBody> listar(){
        return this.ok("Usuarios listados com sucesso", this.service.findAll());
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseBody> buscarPorId(@PathVariable int id){
        Optional<Usuario> usuario = this.service.findById(id);
        if(usuario.isPresent())
            return this.ok("Usuario encontrado com sucesso", usuario.get());
        return this.naoEncontrado("Usuario nao encontrado", null);
    }

    @PostMapping
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseBody> criar(@RequestBody Usuario usuario) {
        System.out.println(usuario);
        return this.ok("Usuario adicionado com sucesso", this.service.criar(usuario));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseBody> eliminar(@PathVariable int id) {
        return this.ok("Usuario eliminado com sucesso", this.service.eliminar(id));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<?> atualizar(@PathVariable int id, @RequestBody Usuario usuario) {
        if (this.service.findById(id).isPresent()) {
            return this.ok("Usuário atualizado com sucesso", this.service.editar(id, usuario));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Usuário não encontrado"));
    }


}

