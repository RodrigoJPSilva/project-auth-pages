package com.login.login.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoRequestDTO {
    @NotBlank(message = "O produto não pode ser nulo")
    private String name;

    @NotNull(message = "O preço não pode estar vazio")
    private double price;

    @NotNull(message = "O produto precisa ter uma quantidade")
    private int quantity;

    public ProdutoRequestDTO() {
    }
    public ProdutoRequestDTO(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
