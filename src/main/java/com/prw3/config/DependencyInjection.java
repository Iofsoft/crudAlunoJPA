package com.prw3.config;

import com.prw3.dao.AlunoDAO;
import com.prw3.dao.NotaDAO;
import com.prw3.services.AlunoService;
import com.prw3.services.NotaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DependencyInjection {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("boletim");

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public static AlunoDAO createAlunoDAO() {
        return new AlunoDAO(createEntityManager());
    }

    public static NotaDAO createNotaDAO() {
        return new NotaDAO(createEntityManager());
    }

    public static AlunoService createAlunoService() {
        return new AlunoService(createAlunoDAO());
    }

    public static NotaService createNotaService() {
        return new NotaService(createNotaDAO());
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
