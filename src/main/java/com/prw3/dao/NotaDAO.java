package com.prw3.dao;

import com.prw3.model.Nota;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotaDAO implements DAOBase<Nota> {
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
        String jpql = "SELECT n FROM Nota n";
       try{
            return em.createQuery(jpql, Nota.class)
                    .getResultList();
       }
       catch(Exception e){
           return null;
       }
    }

    public void save (Nota nota){
        em.getTransaction().begin();
        em.persist(nota);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Nota nota) {

    }

    public List<Nota> findById(Long alunoId) {
        String jpql = "SELECT n FROM Nota n WHERE n.aluno.id = :alunoId";
        return em.createQuery(jpql, Nota.class)
                .setParameter("alunoId", alunoId)
                .getResultList();
    }


    public List<Nota> findListById(Long id) {
        return List.of(em.find(Nota.class, id));
    }

    public List<Object[]> findAllApproved(){
        String jpql = "SELECT n.aluno.id, AVG(n.nota) FROM Nota n GROUP BY n.aluno.id HAVING AVG(n.nota) > 6";
        try{
            return em.createQuery(jpql, Object[].class).getResultList();
        }
        catch(Exception e){
            return null;
        }
    }
}
