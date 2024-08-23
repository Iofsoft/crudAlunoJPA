package com.prw3.dao;

import com.prw3.model.Nota;
import jakarta.persistence.EntityManager;

import java.util.List;

public class NotaDAO implements DAO<Nota> {
    private EntityManager em;

    public NotaDAO() {}

    public NotaDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Nota findByName(String name) {
        return null;
    }

    @Override
    public List<Nota> findAll() {
        return List.of();
    }

    public void save (Nota nota){
        em.persist(nota);
    }

    @Override
    public void delete(Nota nota) {

    }

    public Nota findById(Long id) {
        return em.find(Nota.class, id);
    }
}
