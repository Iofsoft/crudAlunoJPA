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
                    Aluno novoAluno = new Aluno();
                    StringUtil.enterAlunoData(novoAluno);
                    alunoService.save(novoAluno);
                    break;

                case 2: // Cadastrar nota
                    Double notaValue = StringUtil.enterNotaValue();
                    String alunoName = StringUtil.enterAlunoName();
                    Collection<Aluno> alunosEncontrado = alunoService.findByName(alunoName);
                    if(alunosEncontrado.size() == 1){
                        Aluno aluno = alunosEncontrado.iterator().next();
                        Nota nota = new Nota(notaValue, aluno);
                        notaService.save(nota);
                    }
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
                    if (!listaAlunos.isEmpty()){
                        for(Aluno aluno : listaAlunos){
                            System.out.println(STR."\{aluno}Situação: \{notaService.getSituacao(aluno.getName())}");
                        }
                    }

                    Collection<Nota> listaNotas = notaService.findByName(name);
                    if (!listaNotas.isEmpty()) listaNotas.forEach(System.out::println);
                    break;

                case 7: // Listar todos os alunos
                    Collection<Aluno> alunos = alunoService.findAll();
                    for(Aluno aluno : alunos){

                        System.out.println(STR."\{aluno}Situação: \{notaService.getSituacao(aluno.getName())}");
                    }
                    break;

                case 8: // Listar todas as notas
                    Collection<Nota> notas = notaService.findAll();
                    for (Nota nota : notas) {
                        Aluno aluno = alunoService.findById(nota.getAluno().getId()).iterator().next();
                        System.out.println(STR.
                            """
                            Nota: \{nota.getNota()} Nome: \{aluno.getName()}
                            """
                        );
                    }
                    break;

                case 9: // Buscar alunos aprovados
                    Collection<Object[]> listaAprovados = notaService.findAllApproved();
                    if(listaAprovados.isEmpty()){
                        System.out.println("\nNenhum Aluno foi aprovado");
                        return;
                    }
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
