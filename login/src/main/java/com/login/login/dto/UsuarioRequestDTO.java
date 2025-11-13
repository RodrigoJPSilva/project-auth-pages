package com.login.login.dto;

import com.login.login.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {
    @NotNull(message = "O usuario não pode ser nulo")
    private String name;

    @NotBlank(message = "O email não pode estar vazio")
    private String email;

    @NotBlank(message = "O produto precisa ter uma quantidade")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String password;


        public UsuarioRequestDTO(Usuario user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
    public UsuarioRequestDTO(String password, String email, String name) {
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
