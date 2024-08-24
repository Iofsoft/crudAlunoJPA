package com.prw3.services;

import com.prw3.config.DependencyInjection;
import com.prw3.dao.NotaDAO;
import com.prw3.model.Aluno;
import com.prw3.model.Nota;
import com.prw3.util.StringUtil;

import java.util.Collection;

public class NotaService {
    private final NotaDAO notaDAO;
    AlunoService alunoService = DependencyInjection.createAlunoService();

    public NotaService(NotaDAO notaDAO) {
        this.notaDAO = notaDAO;
    }

    public void save() {
        Double notaValue = StringUtil.enterNotaValue();
        String alunoName = StringUtil.enterAlunoName();
        Collection<Aluno> alunosEncontrado = alunoService.findByName(alunoName);
        if(alunosEncontrado.size() == 1){
            Aluno aluno = alunosEncontrado.iterator().next();
            Nota nota = new Nota(notaValue, aluno);
            notaDAO.save(nota);
            System.out.println("Nota cadastrada com sucesso!");
        }
    }

    public void findAllApproved(){
        System.out.println("\nAlunos Aprovados");
        Collection<Object[]> listaAprovados = notaDAO.findAllApproved();
        for (Object[] result : listaAprovados) {
            Long alunoId = (Long) result[0];
            Aluno alunoAprovado = alunoService.findById(alunoId).iterator().next();
            Double averageNota = (Double) result[1];
            System.out.println(STR.
                """
                =====================================
                Aluno ID: \{alunoId}
                Nome: \{alunoAprovado.getName()}
                Média das Notas: \{averageNota}
                """
            );
        }
        notaDAO.findAllApproved();
    }

    public void findAll(){
        System.out.println("\nTodas as Notas");
        Collection<Nota> listaNotas = notaDAO.findAll();
        for (Nota nota : listaNotas) {
            Aluno aluno = alunoService.findById(nota.getAluno().getId()).iterator().next();
            System.out.println(STR.
                """
                Nota: \{nota.getNota()} Nome: \{aluno.getName()}
                """
            );
        }
        notaDAO.findAll();

    }
}
