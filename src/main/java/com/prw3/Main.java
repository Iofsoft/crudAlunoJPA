package com.prw3;

import com.prw3.config.DependencyInjection;
import com.prw3.model.Aluno;
import com.prw3.model.Nota;
import com.prw3.services.AlunoService;
import com.prw3.services.NotaService;
import com.prw3.util.StringUtil;

import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AlunoService alunoService = DependencyInjection.createAlunoService();
        NotaService notaService = DependencyInjection.createNotaService();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n** CADASTRO DE ALUNOS **");

            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Cadastrar nota");
            System.out.println("3 - Alterar aluno");
            System.out.println("4 - Alterar nota");
            System.out.println("5 - Excluir aluno");
            System.out.println("6 - Buscar aluno por nome");
            System.out.println("7 - Listar todos os alunos");
            System.out.println("8 - Listar todas as notas");
            System.out.println("9 - Buscar alunos aprovados");

            System.out.println("0 - Sair");
            System.out.println("\nDigite uma opção:");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1: // Cadastrar aluno
                    alunoService.save();
                    break;

                case 2: // Cadastrar nota
                    notaService.save();
                    break;

                case 3: // Alterar aluno
                    alunoService.update();
                    break;

                case 4: // Alterar nota
                    notaService.update();
                    break;

                case 5: // Excluir aluno
                    alunoService.delete();
                    break;

                case 6: // Buscar aluno por nome
                    String name = StringUtil.enterAlunoName();
                    Collection<Aluno> listaAlunos = alunoService.findByName(name);
                    if (!listaAlunos.isEmpty()) listaAlunos.forEach(System.out::println);
                    Collection<Nota> listaNotas = notaService.findByName(name);
                    if (!listaNotas.isEmpty()) listaNotas.forEach(System.out::println);
                    break;

                case 7: // Listar todos os alunos
                    alunoService.findAll();
                    break;

                case 8: // Listar todas as notas
                    notaService.findAll();
                    break;

                case 9: // Buscar alunos aprovados
                    notaService.findAllApproved();
                    break;

                case 0:
                    DependencyInjection.closeEntityManagerFactory();
                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}
