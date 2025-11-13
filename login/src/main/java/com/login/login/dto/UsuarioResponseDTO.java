package com.login.login.dto;

import com.login.login.entity.Usuario;

public class UsuarioResponseDTO {
    private int id;
    private String name;
    private String email;


        public UsuarioResponseDTO(Usuario user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
//    public UsuarioRequestDTO(String password, String email, String name) {
//        this.password = password;
//        this.email = email;
//        this.name = name;
//    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
