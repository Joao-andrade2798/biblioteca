package com.andrade.bibliotecaApi;

public enum StatusExemplarEnum {
    DISPONIVEL(1, "Disponível"),
    EMPRESTADO(2, "Emprestado"),
    PERDIDO(3, "Perdido");

    private final int codigo;
    private final String situacao;

    StatusExemplarEnum(int codigo, String situacao) {
        this.codigo = codigo;
        this.situacao = situacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getSituacao() {
        return situacao;
    }
}
