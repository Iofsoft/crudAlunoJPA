package com.prw3;

import com.prw3.dao.AlunoDAO;
import com.prw3.model.Aluno;
import com.prw3.utils.JPAUtil;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!");
        Aluno jovem = new Aluno("Jovem da Silva", "123", "jovem@mail.com");
        Aluno mina = new Aluno("Mina Pereira", "234", "mina@mail.com");
        Aluno vagabond = new Aluno("Vagabond Malandro", "345", "vagabond@mail.com");

        EntityManager em = JPAUtil.getEntityManager();

        AlunoDAO dao = new AlunoDAO(em);

        em.getTransaction().begin();
        dao.CadastrarAluno(jovem);
        dao.CadastrarAluno(mina);
        dao.CadastrarAluno(vagabond);
        em.getTransaction().commit();
        em.close();
    }
}