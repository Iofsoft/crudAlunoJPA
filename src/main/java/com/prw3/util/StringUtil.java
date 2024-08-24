package com.prw3.util;

import com.prw3.model.Aluno;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StringUtil {
    public static void enterAlunoData(Aluno aluno){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine().toUpperCase();
        aluno.setName(nome);
        System.out.print("Digite o RA do aluno: ");
        String ra = scanner.nextLine().toUpperCase();
        aluno.setRa(ra);
        System.out.print("Digite o email do aluno: ");
        String email = scanner.nextLine().toUpperCase();
        aluno.setEmail(email);
    }

    public static String enterAlunoName(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do aluno: ");
        return scanner.nextLine().toUpperCase();
    }

    public static Double enterNotaValue() {
        Scanner scanner = new Scanner(System.in);
        Double nota = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Digite a nota do aluno: ");
            try {
                nota = scanner.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
                scanner.next();
            }
        }
        return nota;
    }

    public static String enterOption(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toUpperCase();
    }

}
