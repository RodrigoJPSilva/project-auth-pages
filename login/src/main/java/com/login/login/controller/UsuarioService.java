package com.login.login.controller;

import com.login.login.dto.UsuarioLoginRequestDTO;
import com.login.login.dto.UsuarioSingupRequestDTO;
import com.login.login.dto.UsuarioResponseDTO;
import com.login.login.entity.Usuario;
import com.login.login.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public UsuarioResponseDTO saveUser(UsuarioSingupRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario(usuarioRequestDTO.getName(),
                usuarioRequestDTO.getEmail(),
                usuarioRequestDTO.getPassword());
        usuarioRepository.save(usuario);
        UsuarioResponseDTO user = new UsuarioResponseDTO(usuario);
        return user;
    }
    public String login(@Valid UsuarioLoginRequestDTO user) {

        Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
        if (findUser == null) {
            return "Usuário não encontrado";
        } else {
            if (findUser.getPassword().equals(user.getPassword())) {
                return "Usuário Logado!";
            } else {
                return "Senha incorrreta";
            }
        }
    }


    public List<UsuarioResponseDTO> listaDeUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> listarUsuarios = new ArrayList<>();
//        for (Usuario usuario : usuarios) {
//            listarUsuarios.add(new UsuarioResponseDTO(usuario));
//        }
        listarUsuarios = usuarios.stream().map(UsuarioResponseDTO::new).toList();
        return listarUsuarios;
    }


    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }


    public Optional<Usuario> usuarioPorId(int id) {
        return usuarioRepository.findById(id);
    }

    public String deletarPorId(int id) {
        try {
            usuarioRepository.deleteById(id);
            return "Excluido com sucesso!";
        } catch (RuntimeException e) {
            return "Não encontrado";
        }
//        if(usuarioRepository.existsById(id)) {
//            return usuarioRepository.deleteById(id);
//
//        }
//        else {
//            return "null";
//        }
    }



    public Usuario atualizar (int id, Usuario novoUsuario) {
        Optional<Usuario> UsuarioExistente = usuarioRepository.findById(id);

        if (UsuarioExistente.isPresent()) {
            Usuario usuario = UsuarioExistente.get();
            usuario.setName(novoUsuario.getName());
            usuario.setPassword(novoUsuario.getPassword());
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }
}
