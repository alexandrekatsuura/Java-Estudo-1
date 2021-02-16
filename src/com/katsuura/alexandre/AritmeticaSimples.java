package com.katsuura.alexandre;
import java.io.IOException;
import java.util.function.Consumer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class AritmeticaSimples {

    public static void Calcular() throws IOException {
        /*
            CONSUMER
            Executa utilizando um parametro e retorna void (nada)
        */
        Consumer<String> imprimirConsumer = System.out::println;
        imprimirConsumer.accept("\nCalcular e mostrar testes aritméticos implementando:");
        imprimirConsumer.accept("- Consumer (java.util.function.Consumer)");
        imprimirConsumer.accept("- Predicate (java.util.function.Predicate)");
        imprimirConsumer.accept("- Supplier (java.util.function.Supplier)");
        imprimirConsumer.accept("- Function (java.util.function.Function)");
        imprimirConsumer.accept("- Stream (java.util.stream.Stream)");
        imprimirConsumer.accept("- Funcao de Alta Ordem (Conceito)\n");

        // Pegar os numeros inseridos do usuario
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // pega primeiro numero
        System.out.print("Digite um número: ");
        StringTokenizer st = new StringTokenizer(br.readLine());
        double valor = Double.parseDouble(st.nextToken());

        // pega segundo numero
        System.out.print("Digite outro número: ");
        st = new StringTokenizer(br.readLine());
        double valor2 = Double.parseDouble(st.nextToken());


        // Exibe resultados
        System.out.println("\nResultado: ");

        /*
            SUPPLIER
            instancia classes -- utiliza metodo get para ter retorno
            no caso, retorna o toString() implementado na classe Numero
        */
        // define suppliers
        Supplier<Numero> instanciaNumero = () -> new Numero(valor);
        Supplier<Numero> instanciaNumero2 = () -> new Numero(valor2);

        // pega retorno dos suppliers
        System.out.println(instanciaNumero.get());
        System.out.println(instanciaNumero2.get());

        /*
            FUNCAO DE ALTA ORDEM
            Chama a interface criada Calculador
            Possui a anotacao @FunctionalInterface
            Possui n parametros.

            --- Eh chamado por um metodo que recebe qual das funcoes implementadas sera utilizada
            no caso executaCalculo(Calculador calculador,double a, double b)
        */

        // define as funcoes de alta ordem
        Calculador soma = Double::sum;
        Calculador subtracao = (a,b) -> a-b;
        Calculador multiplicacao = (a,b) -> a*b;
        Calculador divisao = (a,b) -> a/b;

        // mostra o retorno atravez do metodo executaCalculo
        System.out.printf("\nSoma: %.2f", executaCalculo(soma,valor,valor2));
        System.out.printf("\nSubtracao: %.2f", executaCalculo(subtracao,valor,valor2));
        System.out.printf("\nMultiplicacao: %.2f", executaCalculo(multiplicacao,valor,valor2));
        System.out.printf("\nDivisao: %.2f", executaCalculo(divisao,valor,valor2));


        /*
            FUNCTION
            Define uma funcao
            Recebe um parametro e retorna um parametro tambem.
            Function<T, R>
            T = parametro recebido
            R = parametro retorno

        */
        Function<Double,Double> funcaoDobroDoValor = a -> a * 2;

        // utiliza metodo apply para obter o retorno
        System.out.printf("\nDobro do primeiro numero: %.2f", funcaoDobroDoValor.apply(valor));
        System.out.printf("\nDobro do segundo numero: %.2f", funcaoDobroDoValor.apply(valor2));


        // criacao de arrays para Iteracao (Stream)
        Double[] arrayValor = criarArray(valor);
        Double[] arrayValor2 = criarArray(valor2);


        // chama metodo que retorna os numeros pares efetuando iteracoes com o primeiro array
        System.out.print("\nNúmeros pares até o primeiro número: ");
        retornaNumerosPares(arrayValor);

        // chama metodo que retorna os numeros pares efetuando iteracoes com o segundo array
        System.out.print("\nNúmeros pares até o segundo número: ");
        retornaNumerosPares(arrayValor2);

        // retorna ultima mensagem ao usuario
        System.out.println("\n\nExecutado! Voltando ao menu...");

    }

    // recebe o Calculador implementado e os parametros
    public static double executaCalculo(Calculador calculador,double a, double b){
        // retorna o metodo calcular referente ao definido no Calcular()
        return calculador.calcular(a,b);
    }

    // metodo generico para criar um array de Double, do 1 ao numero recebido
    private static Double[] criarArray(Double valor){
        Double[] array = new Double[(int) Math.abs(valor)];
        for(int i =0; i < (int) Math.abs(valor); i++){
            array[i] = (double) (i + 1);
        }
        return array;
    }

    // metodo recebe array de Double e retorna os numeros pares ate ele
    // efetua a iteracao
    public static void retornaNumerosPares(Double... numeros){
        Stream.of(numeros).filter( numero -> numero%2==0)
                .forEach(numero -> System.out.printf("%.0f ", numero));

    }
}


// interface funcional declarada e anotada para utilizar no conceito de funcao de Alta Ordem
@FunctionalInterface
interface Calculador {
    double calcular(double a, double b);
}

// Classe criada e implementada para exemplo de Supplier
class Numero{
    private final double numero;
    private final boolean positivo;

    // criacao recebendo parametro valor e atribuindo as variaveis
    public Numero(double valor){
        numero = valor;
        positivo = ePositivo();
    }

    /*
        PREDICATE
        Recebe um parametro, Predicado<T>
        T = parametro recebido
        e retorna uma boolean - se true ou false de acordo com a condicao
     */
    private boolean ePositivo(){
        // define o Predicate
        Predicate<Double> ePositivo = positivo -> positivo > 0;

        // retorna a boolean utilizando o metodo test.
        return ePositivo.test(numero);
    }

    // override de toString a ser utilizado no metodo get() do Supplier no Calcular()
    @Override
    public String toString() {
        return String.format("número: %.2f, positivo: %b",numero,positivo) ;
    }


}
