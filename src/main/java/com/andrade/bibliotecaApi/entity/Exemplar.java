package com.andrade.bibliotecaApi.entity;

import com.andrade.bibliotecaApi.StatusExemplarEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class Exemplar {
    @Id
    private String id;

    @Column(unique = true, nullable = false)
    @SequenceGenerator(name = "codigo", sequenceName = "exemplar_codigo", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigo")
    private Integer codigo;

    @NotBlank(message = "Livro é obrigatório")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @NotBlank(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    private StatusExemplarEnum status;

    protected Exemplar() {
        this.id = UUID.randomUUID().toString();
    }

    public Exemplar(Livro livro, StatusExemplarEnum status) {
        this();
        this.status = status;
        this.livro = livro;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public StatusExemplarEnum getStatus() {
        return status;
    }

    public void setStatus(StatusExemplarEnum status) {
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }
}
