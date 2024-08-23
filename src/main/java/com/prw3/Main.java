package com.prw3;

import com.prw3.config.DependencyInjection;
import com.prw3.model.Aluno;
import com.prw3.model.Nota;
import com.prw3.services.AlunoService;
import com.prw3.services.NotaService;
import com.prw3.util.StringUtil;

import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        AlunoService alunoService = DependencyInjection.createAlunoService();
        NotaService notaService = DependencyInjection.createNotaService();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Digite uma opção:");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Cadastrar nota");
            System.out.println("4 - Buscar Aluno Por Nome");

            System.out.println("3 - Sair");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    Aluno aluno = StringUtil.enterAlunoData();
                    alunoService.save(aluno);
                    System.out.println("Aluno cadastrado com sucesso!");
                    break;
                case 2:
                    Double notaValue = StringUtil.enterNotaValue();
                    String alunoName = StringUtil.enterAlunoName();
                    Aluno alunoExistente = alunoService.findByName(alunoName);
                    Nota nota = new Nota(notaValue, alunoExistente);
                    notaService.saveNota(nota);
                    System.out.println("Nota cadastrada com sucesso!");
                    break;
                case 3:
                    DependencyInjection.closeEntityManagerFactory();
                    System.out.println("Saindo...");
                    return;
                case 4:
                    String nomeAluno = StringUtil.enterAlunoName();
                    aluno = alunoService.findByName(nomeAluno);
                    System.out.println(aluno);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }



}
