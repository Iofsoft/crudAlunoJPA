package com.prw3.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String ra;
    @Column(unique = true)
    private String email;
    public Aluno() {}

    public Aluno(String nome, String ra, String email) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
    }
}
