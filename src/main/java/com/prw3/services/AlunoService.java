package com.prw3.services;

import com.prw3.dao.AlunoDAO;
import com.prw3.model.Aluno;
import com.prw3.util.StringUtil;

import java.util.Collection;

public class AlunoService {
    private final AlunoDAO alunoDAO;

    public AlunoService(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public void save() {
        Aluno novoAluno = new Aluno();
        StringUtil.enterAlunoData(novoAluno);
        alunoDAO.save(novoAluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    public Collection<Aluno> findByName(String name) {
        return alunoDAO.findByName(name);
    }

    public Collection<Aluno> findById(Long id) {
        return alunoDAO.findById(id);
    }

    public void update() {
        String nomeAluno = StringUtil.enterAlunoName();
        Collection<Aluno> alunos = alunoDAO.findByName(nomeAluno);
        Aluno aluno = alunos.iterator().next();
        StringUtil.enterAlunoData(aluno);
        alunoDAO.update(alunos);
        System.out.println("\nCadastro atualizado com sucesso");
    }

    public void delete(){
        String nomeAluno = StringUtil.enterAlunoName();
        Collection<Aluno> alunos = alunoDAO.findByName(nomeAluno);
        Aluno alunoEncontrado = alunos.iterator().next();

        if(alunos.isEmpty()){
            System.out.println("\nNao foi possivel deletar");
            return;
        }

        if(alunos.size() > 1){
            System.out.println("\nNão foi possivel deletar");
            return;
        }

        System.out.println("\nDeseja Excluir o cadastro? (S/N)");
        if(StringUtil.enterOption().equalsIgnoreCase("S")){
            alunoDAO.delete(alunoEncontrado);
            System.out.println("\nAluno removido com sucesso!");
        }
    }

    public void findAll() {
        Collection<Aluno>alunos = alunoDAO.findAll();
        if(alunos.isEmpty()) System.out.println("\nNenhum Aluno Cadastrado");
        else alunoDAO.findAll().forEach(System.out::println);
    }
}
