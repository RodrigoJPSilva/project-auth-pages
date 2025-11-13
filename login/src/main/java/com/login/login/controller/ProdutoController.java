package com.login.login.controller;

import com.login.login.dto.ProdutoRequestDTO;
import com.login.login.dto.ProdutoResponseDTO;
import com.login.login.dto.UsuarioResponseDTO;
import com.login.login.entity.Produto;
import com.login.login.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable int id) {

        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
    }

    @GetMapping("/all")
    public List<ProdutoResponseDTO> buscarTodosId() {
        List<Produto> produto = produtoRepository.findAll();
        List<ProdutoResponseDTO> listarProdutos = new ArrayList<>();
        listarProdutos = produto.stream().map(ProdutoResponseDTO::new).toList();
        return listarProdutos;
    }
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar (@PathVariable int id, @RequestBody Produto novoProduto) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setQuantity(novoProduto.getQuantity());
            produtoRepository.save(produto);
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarPorId(@PathVariable int id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
    }
    @PostMapping(value = "/cadastro")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProdutoRequestDTO product) {
        Produto produto = new Produto(product.getName(), product.getPrice(), product.getQuantity());
        produtoRepository.save(produto);
        return ResponseEntity.ok("O produto foi cadastrado com sucesso!");
    }
}

