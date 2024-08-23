package com.prw3.services;

import com.prw3.dao.NotaDAO;
import com.prw3.model.Nota;

import java.util.List;

public class NotaService {
    private final NotaDAO notaDAO;

    public NotaService(NotaDAO notaDAO) {
        this.notaDAO = notaDAO;
    }

    public void saveNota(Nota nota) {
        notaDAO.save(nota);
    }

    public List<Nota> findById(Long id) {
        return notaDAO.findById(id);
    }
    public List<Nota> findListById(Long id) {
        return notaDAO.findListById(id);
    }

    public List<Nota> findAll() {
        return notaDAO.findAll();
    }

    public List<Object[]> findAllApproved(){
        return notaDAO.findAllApproved();
    }

    // Outros métodos relacionados a Nota
}
