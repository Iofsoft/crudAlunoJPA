package com.prw3.dao;

import com.prw3.model.Aluno;
import jakarta.persistence.EntityManager;

public class AlunoDAO {
    private EntityManager em;

    public AlunoDAO() {}

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    public void CadastrarAluno (Aluno aluno){
        em.persist(aluno);
    }

    public void RemoverAluno (Aluno aluno){
        em.remove(aluno);
    }
}
