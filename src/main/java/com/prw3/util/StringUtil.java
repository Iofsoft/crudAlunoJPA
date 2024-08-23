package com.prw3.util;

import com.prw3.model.Aluno;
import com.prw3.model.Nota;

import java.util.Scanner;

public class StringUtil {
    public static Aluno enterAlunoData(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o RA do aluno: ");
        String ra = scanner.nextLine();
        System.out.print("Digite o email do aluno: ");
        String email = scanner.nextLine();
        return new Aluno(nome, ra, email);
    }

    public static String enterAlunoName(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do aluno: ");
        return scanner.nextLine();
    }

    public static String enterAlunoId(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o id do aluno: ");
        return scanner.nextLine();
    }

    public static Double enterNotaValue(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a nota do aluno: ");
        return scanner.nextDouble();
    }

}
