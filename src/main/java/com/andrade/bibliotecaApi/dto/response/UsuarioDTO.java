package com.andrade.bibliotecaApi.dto.response;

public class UsuarioDTO {
    private String nome;
    private String email;
    private String telefone;
    public UsuarioDTO(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getTelefone() {return telefone;}

    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}
}
