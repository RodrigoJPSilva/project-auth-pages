package com.login.login.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioLoginRequestDTO {
    @NotBlank(message = "O email não pode estar vazio")
    private String email;

    @NotBlank(message = "O produto precisa ter uma quantidade")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String password;


//        public UsuarioRequestDTO(Usuario user) {
//        this.name = user.getName();
//        this.email = user.getEmail();
//        this.password = user.getPassword();
//    }
    public UsuarioLoginRequestDTO(String password, String email, String name) {
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
