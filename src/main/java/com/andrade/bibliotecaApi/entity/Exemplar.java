package com.andrade.bibliotecaApi.entity;

import com.andrade.bibliotecaApi.enums.StatusExemplarEnum;
import com.andrade.bibliotecaApi.repository.ExemplarRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.generator.EventType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Entity
@Table(name = "exemplar")
public class Exemplar {
    @Id
    private String id;

    @Column(unique = true, nullable = false, insertable = false, updatable = false)
    @Generated(event = EventType.INSERT)
    private Integer codigo;

    @NotBlank(message = "Livro é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @NotBlank(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    private StatusExemplarEnum status;

    protected Exemplar() {
    }

    public Exemplar(Livro livro, StatusExemplarEnum status) {
        this.id = UUID.randomUUID().toString();
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
