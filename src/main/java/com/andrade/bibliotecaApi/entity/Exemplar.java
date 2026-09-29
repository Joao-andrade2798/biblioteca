package com.andrade.bibliotecaApi.entity;

import com.andrade.bibliotecaApi.enums.StatusExemplarEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.util.UUID;

@Entity
@Table(name = "exemplar")
public class Exemplar {
    @Id
    private String id;

    @Column(unique = true, nullable = false, insertable = false, updatable = false)
    @Generated(event = EventType.INSERT)
    private Integer codigo;

    @NotNull(message = "Livro é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    private StatusExemplarEnum status;

    protected Exemplar() {
    }

    public Exemplar(Livro livro, StatusExemplarEnum status) {
        this.id = UUID.randomUUID().toString();
        this.livro = livro;
        this.status = status;
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
