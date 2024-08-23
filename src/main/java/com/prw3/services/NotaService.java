package com.prw3.services;

import com.prw3.dao.NotaDAO;
import com.prw3.model.Nota;

public class NotaService {
    private final NotaDAO notaDAO;

    public NotaService(NotaDAO notaDAO) {
        this.notaDAO = notaDAO;
    }

    public void saveNota(Nota nota) {
        notaDAO.save(nota);
    }

    public Nota findNotaById(Long id) {
        return notaDAO.findById(id);
    }

    // Outros métodos relacionados a Nota
}
