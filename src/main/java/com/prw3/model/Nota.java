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
    @JoinColumn(name = "alunoId", nullable = false)
    private Aluno aluno;
    private final LocalDateTime dataCadastro = LocalDateTime.now();

    public Nota() {}

    public Nota(Double nota, Aluno aluno) {
        this.nota = nota;
        this.aluno = aluno;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return STR."Nome Aluno: \{aluno.getName()} Nota: \{nota} - Data: \{dataCadastro}";
    }
}
