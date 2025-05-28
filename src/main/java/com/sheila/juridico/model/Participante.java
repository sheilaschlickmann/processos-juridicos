package com.sheila.juridico.model;

import jakarta.persistence.*;

@Entity
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private String cnpjCpf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoParticipante tipo;

    private String email;

    private String telefone;

    @ManyToOne
    @JoinColumn(name = "processo_id", nullable = false)
    private Processo processo;










}
