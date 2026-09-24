package com.andrade.bibliotecaApi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    private String id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "A nacionalidade é obrigatória")
    @Column(nullable = false)
    private String nacionalidade;

    public Autor() {
        this.id = UUID.randomUUID().toString();
    }

    public Autor(String nome, String nacionalidade) {
        this();
        this.nome = nome;
        this.nacionalidade = nacionalidade;
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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
