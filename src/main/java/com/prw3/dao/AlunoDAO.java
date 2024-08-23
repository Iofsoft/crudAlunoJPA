package com.prw3.dao;

import com.prw3.model.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.NoSuchElementException;

public class AlunoDAO implements DAO<Aluno> {
    private EntityManager em;

    public AlunoDAO() {}

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Aluno aluno){
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Aluno findByName(String nome){
        String jpql = "SELECT a FROM Aluno a WHERE a.nome LIKE :nome";
        try{
            return em.createQuery(jpql, Aluno.class)
                    .setParameter("nome","%" + nome +"%")
                    .getSingleResult();
        }
        catch(NoResultException e){
            System.out.println("Nenhum aluno Encontrado");;
            return null;
        }
    }

    @Override
    public List<Aluno> findAll() {
        return List.of();
    }

    @Override
    public void delete (Aluno aluno){
        em.remove(aluno);
    }
}
