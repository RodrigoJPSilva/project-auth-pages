package com.login.login.controller;

import com.login.login.dto.UsuarioRequestDTO;
import com.login.login.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.login.login.entity.Usuario;
import com.login.login.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController

public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(value = "usuario/cadastro")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UsuarioRequestDTO user) {
        Usuario usuario = new Usuario(user.getName(), user.getEmail(), user.getPassword());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("O usuário foi cadastrado com sucesso!");
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@Valid @RequestBody UsuarioRequestDTO user) {

        Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
        if (findUser == null) {
            return ResponseEntity.ok("Usuário não encontrado");
        } else {
            if (findUser.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok("Usuário Logado!");
            } else {
                return ResponseEntity.ok("Senha incorrreta");
            }
        }
    }

    @GetMapping(value = "Rodrigo")
    public List<UsuarioResponseDTO> listaDeUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> listarUsuarios = new ArrayList<>();
//        for (Usuario usuario : usuarios) {
//            listarUsuarios.add(new UsuarioResponseDTO(usuario));
//        }
        listarUsuarios = usuarios.stream().map(UsuarioResponseDTO::new).toList();
        return listarUsuarios;
    }

    @GetMapping(value = "Ryan")
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Optional<Usuario> usuarioPorId(@PathVariable int id) {
        return usuarioRepository.findById(id);
    }
    @DeleteMapping(value = "del/{id}")
    public ResponseEntity<String> deletarPorId(@PathVariable int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
    }

/*        if(usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        } *
 */
        @PutMapping("/alterar/{id}")
        public ResponseEntity<Usuario> atualizar (@PathVariable int id, @RequestBody Usuario novoUsuario) {
            Optional<Usuario> UsuarioExistente = usuarioRepository.findById(id);

            if (UsuarioExistente.isPresent()) {
                Usuario Usuario = UsuarioExistente.get();
                Usuario.setName(novoUsuario.getName());
                Usuario.setPassword(novoUsuario.getPassword());
                usuarioRepository.save(Usuario);
                return ResponseEntity.ok(Usuario);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
}

