package com.prw3.dao;

import com.prw3.model.Nota;
import jakarta.persistence.EntityManager;

public class NotaDAO {
    private EntityManager em;

    public NotaDAO() {}

    public NotaDAO(EntityManager em) {
        this.em = em;
    }

    public void CadastrarNota (Nota nota){
        em.persist(nota);
    }

    public void RemoverNota (Nota nota){
        em.remove(nota);
    }
}
