package com.prw3;

import com.prw3.dao.AlunoDAO;
import com.prw3.dao.NotaDAO;
import com.prw3.model.Aluno;
import com.prw3.model.Nota;
import com.prw3.utils.JPAUtil;
import jakarta.persistence.EntityManager;


public class Main {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!");
        Aluno jovem = new Aluno("Jovem da Silva", "123", "jovem@mail.com");
        Aluno mina = new Aluno("Mina Pereira", "234", "mina@mail.com");
        Aluno vagabond = new Aluno("Vagabond Malandro", "345", "vagabond@mail.com");
        Nota notaJovem1 = new Nota(7.5, jovem);
        Nota notaJovem2 = new Nota(8.5, jovem);
        Nota notaJovem3 = new Nota(9.5, jovem);

        Nota notaMina1 = new Nota(9.5, mina);
        Nota notaMina2 = new Nota(10.0, mina);
        Nota notaMina3 = new Nota(9.5, mina);

        Nota notaVagal1 = new Nota(6.5, vagabond);
        Nota notaVagal2 = new Nota(4.5, vagabond);
        Nota notaVagal3 = new Nota(3.5, vagabond);


        EntityManager em = JPAUtil.getEntityManager();

        AlunoDAO alunoDAO = new AlunoDAO(em);
        NotaDAO notaDAO = new NotaDAO(em);

        em.getTransaction().begin();

        alunoDAO.CadastrarAluno(jovem);
        alunoDAO.CadastrarAluno(mina);
        alunoDAO.CadastrarAluno(vagabond);
        notaDAO.CadastrarNota(notaJovem1);
        notaDAO.CadastrarNota(notaJovem2);
        notaDAO.CadastrarNota(notaJovem3);
        notaDAO.CadastrarNota(notaMina1);
        notaDAO.CadastrarNota(notaMina2);
        notaDAO.CadastrarNota(notaMina3);
        notaDAO.CadastrarNota(notaVagal1);
        notaDAO.CadastrarNota(notaVagal2);
        notaDAO.CadastrarNota(notaVagal3);

        em.getTransaction().commit();
        em.close();
    }
}