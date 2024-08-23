package com.prw3.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double nota;
    @ManyToOne()
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;
    private LocalDateTime dataCadastro = LocalDateTime.now();

    public Nota() {}

    public Nota(Double nota, Aluno aluno) {
        this.nota = nota;
        this.aluno = aluno;
    }
}
