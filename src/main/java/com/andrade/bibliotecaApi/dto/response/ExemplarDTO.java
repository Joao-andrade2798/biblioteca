package com.andrade.bibliotecaApi.dto.response;

import com.andrade.bibliotecaApi.enums.StatusExemplarEnum;

public class ExemplarDTO {
    private Integer codigo;
    private LivroDTO livro;
    private StatusExemplarEnum statusExemplarEnum;

    public ExemplarDTO(Integer codigo, LivroDTO livro, StatusExemplarEnum statusExemplarEnum) {
        this.codigo = codigo;
        this.livro = livro;
        this.statusExemplarEnum = statusExemplarEnum;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public LivroDTO getLivro() {
        return livro;
    }

    public void setLivro(LivroDTO livro) {
        this.livro = livro;
    }

    public StatusExemplarEnum getStatusExemplarEnum() {
        return statusExemplarEnum;
    }

    public void setStatusExemplarEnum(StatusExemplarEnum statusExemplarEnum) {
        this.statusExemplarEnum = statusExemplarEnum;
    }
}
