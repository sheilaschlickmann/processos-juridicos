package com.sheila.juridico.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Acao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAcao tipo;

    @Column(nullable = false)
    private LocalDateTime dataRegistro;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "processo_id", nullable = false)
    @JsonBackReference
    private Processo processo;

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public TipoAcao getTipo() {
        return tipo;
    }

    public void setTipo(TipoAcao tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

}
