package com.prw3;

import com.prw3.config.DependencyInjection;
import com.prw3.model.Aluno;
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
            System.out.println("3 - Buscar Aluno Por Nome");
            System.out.println("4 - Buscar Todos os Alunos Aprovados");
            System.out.println("5 - Alterar Aluno");
            System.out.println("6 - Excluir Aluno");
            System.out.println("7 - Listar Todos os Alunos");
            System.out.println("8 - Listar Todas as Notas");

            System.out.println("0 - Sair");
            System.out.println("\nDigite uma opção:");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1: //CADASTRAR ALUNO
                    alunoService.save();
                    break;

                case 2: //CADASTRAR NOTA
                    notaService.save();
                    break;

                case 3: //BUSCAR ALUNO PELO NOME
                    String name = StringUtil.enterAlunoName();
                    Collection<Aluno> listaAlunos = alunoService.findByName(name);
                    listaAlunos.forEach(System.out::println);
                    break;

                case 4: //LISTAR ALUNOS APROVADOS
                    notaService.findAllApproved();
                    break;

                case 5: //ALTERAR ALUNOS
                    alunoService.update();
                    break;

                case 6: //EXCLUIR ALUNO
                    alunoService.delete();
                    break;

                case 7: //LISTAR TODOS ALUNOS
                    alunoService.findAll();
                    break;

                case 8: //LISTAR TODAS NOTAS
                    notaService.findAll();
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
