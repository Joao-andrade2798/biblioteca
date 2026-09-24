package com.andrade.bibliotecaApi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    private String id;

    @NotBlank(message = "O titulo é obrigatório!")
    @Column(nullable = false)
    private String titulo;

    @NotNull(message = "O ano é obrigatório!")
    @Column(nullable = false)
    private Integer ano;

    @NotBlank(message = "O genero é obrigatório!")
    @Column(nullable = false)
    private String genero;

    @NotNull(message = "O autor é obrigatório!")
    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    public Livro() {
        this.id = UUID.randomUUID().toString();
    }

    public Livro(String titulo, Integer ano, String genero, Autor autor) {
        this();
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
        this.autor = autor;
    }

    public String getGenero() {return genero; }
    public void setGenero(String genero) {this.genero = genero; }

    public Integer getAno() {return ano; }
    public void setAno(Integer ano) {this.ano = ano; }

    public String getTitulo() {return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getId() {return id; }
    public void setId(String id) {this.id = id; }

    public Autor getAutor() {return autor; }
    public void setAutor(Autor autor) {this.autor = autor; }
}
