package com.prw3.dao;

import com.prw3.model.Aluno;
import com.prw3.model.Nota;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AlunoDAO implements DAOBase<Aluno> {
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
        String jpql = "SELECT a FROM Aluno a";
        try{
            return em.createQuery(jpql, Aluno.class)
                    .getResultList();
        }
        catch(Exception e){
            return null;
        }
    }

//    public List<Aluno> findAllApproved() {
//
//    }


    @Override
    public void delete (Aluno aluno){
        em.remove(aluno);
    }
}
