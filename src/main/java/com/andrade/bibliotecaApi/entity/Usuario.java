package com.andrade.bibliotecaApi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    private String id;

    @NotBlank(message = "O nome é obrigatório!")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório!")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "O telefone é obrigatório!")
    @Column(nullable = false)
    private String telefone;

    public Usuario() {
    }

    public Usuario(String id) {
        this.id = UUID.randomUUID().toString();
    }

    public Usuario(String nome, String email, String telefone) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
