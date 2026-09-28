package com.andrade.bibliotecaApi.dto.response;

public class LivroDTO
{
    private String titulo;
    private Integer ano;
    private String genero;
    private AutorDTO autor;

    public LivroDTO(String titulo, Integer ano, String genero, AutorDTO autor) {
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
        this.autor = autor;
    }

    public String getTitulo() {return titulo; }

    public void setTitulo(String titulo) {this.titulo = titulo; }

    public Integer getAno() {return ano; }

    public void setAno(Integer ano) {this.ano = ano; }

    public String getGenero() {return genero; }

    public void setGenero(String genero) {this.genero = genero; }

    public AutorDTO getAutor() {return autor; }

    public void setAutor(AutorDTO autor) {this.autor = autor; }
}
