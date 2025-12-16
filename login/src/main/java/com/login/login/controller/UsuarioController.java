package com.login.login.controller;

import com.login.login.dto.UsuarioLoginRequestDTO;
import com.login.login.dto.UsuarioSingupRequestDTO;
import com.login.login.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.login.login.entity.Usuario;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
@RestController

public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping(value = "/cadastro")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UsuarioSingupRequestDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUser(user));
    }

    @PostMapping(value = "login")
    public ResponseEntity<String> login(@Valid @RequestBody UsuarioLoginRequestDTO user) {
        return ResponseEntity.ok(usuarioService.login(user));
    }

    @GetMapping(value = "Rodrigo")
    public List<UsuarioResponseDTO> listaDeUsuarios() {
        return usuarioService.listaDeUsuarios();
    }

    @GetMapping(value = "Ryan")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping(value = "{id}")
    public Optional<Usuario> usuarioPorId(@PathVariable int id) {
        return usuarioService.usuarioPorId(id);
    }
    @DeleteMapping(value = "del/{id}")
    public ResponseEntity<String> deletarPorId(@PathVariable int id) {
            return ResponseEntity.ok(usuarioService.deletarPorId(id));
    }

    /*        if(usuarioService.existsById(id)) {
                usuarioService.deleteById(id);
                return ResponseEntity.noContent().build();
            }
            else {
                return ResponseEntity.notFound().build();
            } *
     */
    @PutMapping("/alterar/{id}")
    public ResponseEntity<Usuario> atualizar (@PathVariable int id, @RequestBody Usuario novoUsuario) {
        if (usuarioService != null) {
            return ResponseEntity.ok(usuarioService.atualizar(id, novoUsuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

