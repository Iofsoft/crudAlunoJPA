package com.prw3.dao;

import com.prw3.model.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

import java.util.Collection;
import java.util.Collections;

public class AlunoDAO implements DAOBase<Aluno> {
    private final EntityManager em;

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Aluno aluno) {
        try {
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.println(STR."Erro ao salvar aluno: \{e.getMessage()}");
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public Collection<Aluno> findById(Long id) {
        try {
            Aluno aluno = em.find(Aluno.class, id);
            return aluno != null ? Collections.singletonList(aluno) : Collections.emptyList();
        } catch (PersistenceException e) {
            System.err.println(STR."Erro ao recuperar aluno por ID: \{e.getMessage()}");
            return Collections.emptyList();
        }
    }

    @Override
    public Collection<Aluno> findByName(String nome) {
        String jpql = "SELECT a FROM Aluno a WHERE a.name LIKE :nome";
        try {
            Collection<Aluno> alunos = em.createQuery(jpql, Aluno.class)
                    .setParameter("nome", STR."%\{nome}%")
                    .getResultList();
            if (alunos.size() > 1) {
                System.out.println("\nMúltiplos registros encontrados:");
                alunos.forEach(System.out::println);
            } else if (alunos.isEmpty()) {
                System.out.println("\nNenhum aluno encontrado");
            }
            return alunos;
        } catch (PersistenceException e) {
            System.err.println(STR."Erro ao recuperar aluno por nome: \{e.getMessage()}");
            return Collections.emptyList();
        }
    }

    @Override
    public Collection<Aluno> findAll() {
        String jpql = "SELECT a FROM Aluno a";
        try {
            return em.createQuery(jpql, Aluno.class).getResultList();
        } catch (PersistenceException e) {
            System.err.println(STR."Erro ao recuperar todos os alunos: \{e.getMessage()}");
            return Collections.emptyList();
        }
    }

    @Override
    public void delete(Aluno aluno) {
        try {
            em.getTransaction().begin();
            em.remove(aluno);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.println(STR."Erro ao deletar aluno: \{e.getMessage()}");
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    public void update(Collection<Aluno> alunos) {
        try {
            em.getTransaction().begin();
            em.merge(alunos.iterator().next());
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.println(STR."Erro ao atualizar aluno: \{e.getMessage()}");
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }
}
