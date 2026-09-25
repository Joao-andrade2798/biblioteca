package com.andrade.bibliotecaApi.dto;

import com.andrade.bibliotecaApi.entity.Livro;
import com.andrade.bibliotecaApi.enums.StatusExemplarEnum;

public class ExemplarDTO {
    private Integer codigo;
    private Livro livro;
    private StatusExemplarEnum statusExemplarEnum;

    public ExemplarDTO(Integer codigo, Livro livro, StatusExemplarEnum statusExemplarEnum) {
        this.codigo = codigo;
        this.livro = livro;
        this.statusExemplarEnum = statusExemplarEnum;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public StatusExemplarEnum getStatusExemplarEnum() {
        return statusExemplarEnum;
    }

    public void setStatusExemplarEnum(StatusExemplarEnum statusExemplarEnum) {
        this.statusExemplarEnum = statusExemplarEnum;
    }
}
