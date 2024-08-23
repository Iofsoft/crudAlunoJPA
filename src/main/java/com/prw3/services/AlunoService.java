package com.prw3.services;

import com.prw3.dao.AlunoDAO;
import com.prw3.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoService {
    private final AlunoDAO alunoDAO;

    public AlunoService(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public void save(Aluno aluno) {
        alunoDAO.save(aluno);
    }

    public Aluno findByName(String name) {
        return alunoDAO.findByName(name);
    }

    public List<Aluno> findAll() {
        return alunoDAO.findAll();
    }

//    public List<Aluno> findAllApproved(){


}
