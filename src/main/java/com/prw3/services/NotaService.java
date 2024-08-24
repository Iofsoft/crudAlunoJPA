package com.prw3.services;

import com.prw3.dao.NotaDAO;
import com.prw3.model.Nota;
import com.prw3.util.StringUtil;

import java.util.Collection;

public class NotaService {
    private final NotaDAO notaDAO;

    public NotaService(NotaDAO notaDAO) {
        this.notaDAO = notaDAO;
    }

    public void save(Nota nota) {
        notaDAO.save(nota);
        System.out.println("Nota cadastrada com sucesso!");
    }

    public Collection<Nota> findByName(String name) {
        return notaDAO.findByName(name);
    }

    public Collection<Object[]> findAllApproved(){
        System.out.println("\nAlunos Aprovados");
        return notaDAO.findAllApproved();
    }

    public Collection<Nota> findAll(){
        System.out.println("\nTodas as Notas");
        return notaDAO.findAll();
    }

    public void update() {
        String nomeAluno = StringUtil.enterAlunoName();
        Collection<Nota> listaNotas = findByName(nomeAluno);
        for (Nota nota : listaNotas) {
            System.out.println(nota);
            Double notaValue = StringUtil.enterNotaValue();
            nota.setNota(notaValue);
        }
        notaDAO.update(listaNotas);
        System.out.println("\nCadastro atualizado com sucesso");
    }

    public String getSituacao(String nome){
        Collection<Nota> listaNotas = findByName(nome);
        double mediaNotas = listaNotas.stream()
                                    .mapToDouble(Nota::getNota)
                                    .average()
                                    .orElse(0.0);
        String situacao;
        if(mediaNotas >= 6.0) situacao = "Aprovado";
        else if(mediaNotas < 6.0 && mediaNotas >= 4.0) situacao = "Recuperação";
        else situacao = "Reprovado";
        return situacao;
    }
}
