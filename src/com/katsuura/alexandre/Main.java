package com.katsuura.alexandre;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        StringBuilder menu = new StringBuilder();
        boolean loop = true;
        menu.append("Menu:\n");
        menu.append("1 - Aritmética Simples\n");
        menu.append("2 - Trabalhando com String\n");
        menu.append("0 - Sair");

        BufferedReader br;
        StringTokenizer st;

        do{

            System.out.println("\r");
            System.out.println(menu);

            br = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Opção: ");
            st = new StringTokenizer(br.readLine());
            String valor = st.nextToken();

            switch (valor) {
                case "1" -> AritmeticaSimples.Calcular();
                case "2" -> TrabalhandoComString.Trabalhar();
                case "0" -> loop = false;
                default -> System.out.println("Opção Inválida! Digite uma opcao válida ou zero (0) para sair");
            }

        }while(loop);
    }
}
