package com.login.login.dto;

import com.login.login.entity.Produto;

public class ProdutoResponseDTO {
    private String name;
    private double price;
    private int quantity;
    private double subtotal;

    public ProdutoResponseDTO(Produto product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.subtotal = quantity * price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }
}
