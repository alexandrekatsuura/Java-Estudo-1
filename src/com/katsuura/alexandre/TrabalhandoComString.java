package com.katsuura.alexandre;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TrabalhandoComString {

    public static void Trabalhar(){
        /*
            CONSUMER
            Executa utilizando um parametro e retorna void (nada)
        */
        Consumer<String> imprimirConsumer = System.out::println;
        imprimirConsumer.accept("\nTrabalhar com string e implementar:");
        imprimirConsumer.accept("- Consumer (java.util.function.Consumer)");
        imprimirConsumer.accept("- Predicate (java.util.function.Predicate)");
        imprimirConsumer.accept("- Supplier (java.util.function.Supplier)");
        imprimirConsumer.accept("- Function (java.util.function.Function)");
        imprimirConsumer.accept("- List (java.util.List)");
        imprimirConsumer.accept("- Arrays (java.util.Arrays)");
        imprimirConsumer.accept("- ArrayList (java.util.ArrayList)");
        imprimirConsumer.accept("- Stream (java.util.stream.Stream)\n");

        // Pegar o string inseridos do usuario
        Scanner input = new Scanner (System.in);
        input.useDelimiter("\\s");

        // pega string
        System.out.print("Digite um nome completo: ");
        String nomeCompleto = input.nextLine();

        // Exibe resultados
        System.out.println("\nResultado: ");

        /*
            SUPPLIER
            instancia classes -- utiliza metodo get para ter retorno
            no caso, retorna o toString() implementado na classe Numero
        */
        // define suppliers
        Supplier<Pessoa> instanciaPessoa = () -> new Pessoa(nomeCompleto);

        // pega retorno dos suppliers
        System.out.println(instanciaPessoa.get());


        /*
            FUNCTION
            Define uma funcao
            Recebe um parametro e retorna um parametro tambem.
            Function<T, R>
            T = parametro recebido
            R = parametro retorno

        */
        Function<String,Integer> pegaLength = String::length;

        // utiliza metodo apply para obter o retorno
        System.out.printf("Length (Tamanho da string): %s", pegaLength.apply(nomeCompleto));

        // criacao de arrays para Iteracao (Stream)
        String[] arrayNome = nomeCompleto.split(" ");

        // chama metodo que retorna o nome e a quantidade de letras
        System.out.print("\n\nNomes e quantidade de letras: ");
        retornaNomesEQtdLetras(arrayNome);

        // chama metodo que retorna todos os nomes que comecam com a letra A
        System.out.print("\n\nNomes com a letra A: ");

        //criado para trabalhar com List
        List<String> nomes = new ArrayList<>(Arrays.asList(arrayNome));

        // Trabalhando com List e Stream - efetuando iteracoes
        nomes.stream().filter(nome -> nome.startsWith("A") || nome.startsWith("a"))
                    .forEach(nome -> System.out.print(nome + " "));


        // retorna ultima mensagem ao usuario
        System.out.println("\n\nExecutado! Voltando ao menu...");

    }

    // metodo que efetua iteracao da Array de nomes
    public static void retornaNomesEQtdLetras(String... nomes){
        Stream.of(nomes)
                .forEach(nome -> System.out.print("\nNome: " + nome + " - Letras: " + nome.length()));
    }


}

// Classe criada e implementada para exemplo de Supplier
class Pessoa{
    private final String nomePessoa;

    public Pessoa(String nomeCompleto){
        nomePessoa = nomeCompleto;
    }

    /*
        PREDICATE
        Recebe um parametro, Predicado<T>
        T = parametro recebido
        e retorna uma boolean - se true ou false de acordo com a condicao
     */
    private boolean isEmpty(){
        // define o Predicate
        Predicate<String> isEmpty = String::isEmpty;

        // retorna a boolean utilizando o metodo test.
        return isEmpty.test(nomePessoa);
    }

    // override de toString a ser utilizado no metodo get() do Supplier no Trabalhar()
    @Override
    public String toString() {
        return String.format("Nome Inserido: %s \nVazio? %b",nomePessoa,isEmpty()) ;
    }
}
