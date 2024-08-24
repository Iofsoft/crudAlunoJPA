package com.prw3.dao;

import com.prw3.config.DependencyInjection;
import com.prw3.model.Aluno;
import com.prw3.model.Nota;
import com.prw3.services.AlunoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;

import java.util.Collection;
import java.util.Collections;

public class NotaDAO implements DAOBase<Nota> {
    private final EntityManager em;
    AlunoService alunoService = DependencyInjection.createAlunoService();

    public NotaDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Nota nota) {
        try {
            em.getTransaction().begin();
            em.persist(nota);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.println(STR."Erro ao salvar nota: \{e.getMessage()}");
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    public Collection<Nota> findById(Long alunoId) {
        String jpql = "SELECT n FROM Nota n WHERE n.aluno.id = :alunoId";
        try {
            return em.createQuery(jpql, Nota.class)
                    .setParameter("alunoId", alunoId)
                    .getResultList();
        } catch (NoResultException e) {
            System.out.println(STR."Nenhuma nota encontrada para o aluno com ID: \{alunoId}");
            return Collections.emptyList();
        } catch (PersistenceException e) {
            System.err.println(STR."Erro ao recuperar notas por ID do aluno: \{e.getMessage()}");
            return Collections.emptyList();
        }
    }

    @Override
    public Collection<Nota> findByName(String name) {
        Collection<Aluno>listaAlunos = alunoService.findByName(name);
        if(listaAlunos.isEmpty()) return Collections.emptyList();
        Aluno aluno = alunoService.findByName(name).iterator().next();
        String jpql = "SELECT n FROM Nota n WHERE n.aluno.id = :id ";
        try{
            return em.createQuery(jpql, Nota.class)
                    .setParameter("id", aluno.getId())
                    .getResultList();
        }catch (NoResultException e){
            System.out.println(STR."Nenhuma nota encontrada para o aluno com ID: \{aluno.getId()}");
            return Collections.emptyList();
        } catch (PersistenceException e) {
            System.err.println(STR."Erro ao recuperar notas por ID do aluno: \{e.getMessage()}");
            return Collections.emptyList();
        }

    }

    @Override
    public Collection<Nota> findAll() {
        String jpql = "SELECT n FROM Nota n";
        try {
            return em.createQuery(jpql, Nota.class).getResultList();
        } catch (PersistenceException e) {
            System.err.println(STR."Erro ao recuperar todas as notas: \{e.getMessage()}");
            return Collections.emptyList();
        }
    }

    @Override
    public void delete(Nota nota) {
        try {
            em.getTransaction().begin();
            em.remove(nota);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.println(STR."Erro ao deletar nota: \{e.getMessage()}");
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    public Collection<Object[]> findAllApproved() {
        String jpql = "SELECT n.aluno.id, AVG(n.nota) FROM Nota n GROUP BY n.aluno.id HAVING AVG(n.nota) >= 6";
        try {
            return em.createQuery(jpql, Object[].class).getResultList();
        } catch (PersistenceException e) {
            System.err.println(STR."Erro ao recuperar alunos aprovados: \{e.getMessage()}");
            return Collections.emptyList();
        }
    }

    public void update(Collection<Nota> listaNotas) {
        try {
            em.getTransaction().begin();
            for(Nota nota : listaNotas){
                em.merge(nota);
            }
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.println(STR."Erro ao atualizar nota: \{e.getMessage()}");
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }
}
