package application;

import java.util.List;//Necessario para utilizar e fazer operações com listas
import java.util.Scanner;//Necessário para permitir a entrada de dados do usuário
import java.util.ArrayList;//Necessario para utilizar e fazer operações com listas
import entities.LFA;//importa a classe

public class trabalhoLFA{
    //Criar uma função que imprime o cabecalho
    public static void imprimeCabecalho(){
        System.out.println("------Linguagens Formais, Autômatos e Computabilidade------");
        System.out.println("* Professor Guilherme Nakahata");
        System.out.println("* Aluno André Augusto Brito dos Santos");
        System.out.println("--------Automato Finito Determinístico--------");
    }//função imprimeCabecalho

    //Criar uma função que verifica se a palavra em uma determinada posição(Variável proximoEstado) pertence a matriz de transição e armazena o proximo estado na variavel que gera um retorno
    public static int verificaEstado(int[][] matriz, int quantidadeSimbolosAlfabeto, int proximoEstado, String palavra, int rodaIndicePalavra, List<Integer> estados){
        //Variável criada para armazenar o valor da mudança de estado e para que não desse problema durante a execução pois há alterações de estado dentro da verificação
        int aux = proximoEstado;
        //Criar uma variavel booleana para ajudar na verificação da palavra e adicionar na lista os valores
        boolean teste = false;

        for(int j = 0, i = proximoEstado; j < quantidadeSimbolosAlfabeto; j++){

            //se a ocorrencia do caractere em questao for igual a um dos caracteres armazenados na primeira linha da matriz e o valor na posição determinada da matriz for igual ao valor do proximo estado
            if(palavra.charAt(rodaIndicePalavra) == matriz[0][j] && matriz[i][j] == proximoEstado){
                aux = matriz[i][j];//armazena seu valor na variavel proximoEstado que sera retornada ao final do laço
                teste = true;//Se teve alteração na variavel aux, muda o valor da variavel booleana
            }

            //se a ocorrencia do caractere em questao for igual a um dos caracteres armazenados na primeira linha da matriz e o valor na posição determinada da matriz for diferente de -1
            else if(palavra.charAt(rodaIndicePalavra) == matriz[0][j] && matriz[i][j] != -1){
                aux = matriz[i][j];//armazena seu valor na variavel proximoEstado que sera retornada ao final do laço
                teste = true;//Se teve alteração na variavel aux, muda o valor da variavel booleana
            }

            //se nao for verdade a condição do if e else if, não muda o valor do proximoEstado, sendo seu valor inicial seu retorno
        }//for

        //Se teste sair como true, adiciona na lista o valor da variavel aux
        if(teste){
            estados.add(aux);
        //Se teste sair como false, adiciona -1 na lista, o que auxiliará se a palavra é aceita ou não
        }else{
            estados.add(-1);
        }//else

        //Passa o valor da alteração de estado para a variavel proximoEstado que será retornada
        proximoEstado = aux;

        //retorna o valor da variavel proximoEstado
        return proximoEstado;
    }//função verificaEstado

    //criar a função principal
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);//possibilita a entrada de dados do usuario

        //Chamar a função que imprime o cabeçalho
        imprimeCabecalho();

        //Pegar o valor do estado inicial
        System.out.print("Digite o Estado Inicial: ");
        int estadoInicial = sc.nextInt();

        //Pegar o valor da quantidade de estados
        System.out.print("Digite a Quantidade de Estados: ");
        int quantidadeDeEstados = sc.nextInt();

        //Pegar o valor da quantidade de estados finais
        System.out.print("Digite o a Quantidade de Estado(s) Final(is): ");
        int quantidadeDeEstadoFinal = sc.nextInt();

        //Pegar o valor do estado final
        System.out.print("Digite o Estado Final: ");
        int estadoFinal = sc.nextInt();

        //Pegar o valor da quantidade de simbolos
        System.out.print("Digite a Quantidade de Símbolos: ");
        int quantidadeSimbolosAlfabeto = sc.nextInt();

        //Limpar o buffer pois o tipo anterior é numérico e o tipo seguinte é tipo char
        sc.nextLine();//Armazena o valor da tecla ENTER do ultimo tipo numérico

        //Cria um array de char e atribui seu tamanho de acordo com a variavel que pega a quantidade de simbolos
        char[] simbolos = new char[quantidadeSimbolosAlfabeto];

        //Laço for para atribuir os valores ao array de simbolos
        for(int i = 0; i < quantidadeSimbolosAlfabeto; i++){
            System.out.print("Digite o " + (i+1) + "° símbolo: ");
            simbolos[i] = sc.next().charAt(0);
        }//for

        //Passa os valores para o construtor da classe
        LFA config = new LFA(estadoInicial, quantidadeDeEstados, quantidadeDeEstadoFinal, estadoFinal, quantidadeSimbolosAlfabeto, simbolos);

        //imprimir a tabela de Transição
        System.out.println("===== TABELA DE TRANSIÇÃO =====");
        for(int i = 0; i < quantidadeDeEstados+1; i++){
            for(int j = 0, x = 0; j < quantidadeSimbolosAlfabeto+1; j++){
                if (i == 0) {//Para a primeira linha da matriz
                    if(i == j){//Se i e j forem iguais a 0, imprime espaço
                        System.out.printf("%4c ", ' ');
                    }else{//Se i for igual a 0 e j diferente de i, imprime o valor armazenado em simbolos
                        System.out.printf("%4c ", simbolos[x]);
                        x++;
                    }//else
                }//if i = 0
                //Para a primeira coluna
                else if(j == 0){//Imprime Q1 e os demais variando o valor do quantidadeDeEstados para a primeira coluna
                    System.out.printf("%c%d%2c ", 'Q', i, ' ');
                }// if j == 0
                //Para o resto da matriz
                else{//Imprime o ã posição da matriz
                    System.out.printf("%2d,%d ", i, j);
                }//else
            }//for j
            System.out.println();//Pula uma linha para imprimir no formato de matriz
        }//for i

        //criar uma matriz 5x4 que armazena na primeira linha os simbolos e no resto armazena os possiveis estados
        int[][] matriz = new int[quantidadeDeEstados+1][quantidadeSimbolosAlfabeto];

        //preencher a matriz
        for(int i = 0; i < quantidadeDeEstados+1; i++){//For do tamanho dos estados + 1
            for(int j = 0, x = 0; j < quantidadeSimbolosAlfabeto; j++){//For do tamanho da quantidade de estados
                if(i == 0){//Para a primeira linha, passa os valores armazenados em simbolos
                    matriz[i][j] = simbolos[x];//Armazena o valor na matriz
                    x++;//aumenta o valor de x para pegar o simbolo da proxima posição
                }else{//Para o resto da matriz, pega o valor da transição
                    System.out.print("Preencha a transição " + i + "," + (j+1) + ": ");
                    matriz[i][j] = sc.nextInt();//Armazena o valor na matriz
                }//else
            }//for j
        }//for i

        //Armazena o valor da tecla ENTER do ultimo preenchimento dos estados que estão na matriz
        sc.nextLine();

        //Laço for para rodar uma quantidade determinada de vezes
        for(int qtdVezes = 0; qtdVezes < 3; qtdVezes++){
            //Pedir ao usuario digitar uma palavra
            System.out.print("Digite uma palavra: ");
            String palavra = sc.nextLine();

            //Criar uma lista para armazenar os valores dos estados(transições)
            List<Integer> estados = new ArrayList<>();

            //Verificar o primeiro estado
            for(int i = 1; i < 2; i++){
                for(int j = 0, x = 0; j < quantidadeSimbolosAlfabeto; j++){
                    if(palavra.charAt(x) == matriz[x][j] && matriz[i][j] != -1){//Se a primeira letra da palavra estiver na primeira linha da matriz e na posição i e j for diferente de -1, armazena o valor da posição i,j da matriz na variavel proximoEstado, caso nao aconteça, a variavel continua com valor 0
                        config.setProximoEstado(matriz[i][j]);//Altera o valor da variavel criada na classe para o valor armazenado na posição determinada da matriz
                        config.setRodaIndicePalavra(1);//Altera o valor da variavel que roda os indices da palavra em 1
                        estados.add(config.getProximoEstado());//Adiciona o valor do primeiro estado para a lista se tiver o primeiro estado
                    }//if
                }//for j
            }//for i

            //se nao teve alteração no valor da variavel, a palavra não é aceita
            if(config.getProximoEstado() == 0){
                System.out.println("Palavra não aceita!");
            }else{//Altera o valor da variavel para o valor do proximo estado
                for(int i = 0; i < palavra.length() - 1; i++){

                    //chama a função que verifica a palavra dentro da matriz e altera o valor do proximo estado
                    int proximoEstado = verificaEstado(matriz, quantidadeSimbolosAlfabeto, config.getProximoEstado(), palavra, config.getRodaIndicePalavra(), estados);

                    //Verifica se o valor da variavel que recebe o retorno da função e a variavel da classe que armazena o proximoEstado são iguais
                    if(proximoEstado != config.getProximoEstado() || i < palavra.length() - 1){
                        config.setProximoEstado(proximoEstado);//altera o antigo valor da variavel da classe proximoEstado para o valor que foi alterado na função
                        config.setRodaIndicePalavra(1);
                    }//if
                }//for i

                //Criar uma variavel do tipo boolean para ajudar na ultima verificação após toda a verificação da palavra na função
                boolean ultimaVerificacao = true;

                //For para rodar os valores da lista
                for(int i = 0; i < estados.size(); i++){
                    //Se tiver algum valor -1 na lista, muda o valor da variavel booleana para false
                    if(estados.get(i) == -1){
                        ultimaVerificacao = false;
                        break;
                    }//if

                    //Se não tiver um -1 na lista mas seu valor na ultima posição for diferente da variavel que define o estado final, muda o valorda variavel booleana para false
                    else if(estados.get(i) != -1 && estados.get(estados.size()-1) != estadoFinal){
                        ultimaVerificacao = false;
                        break;
                    }//else if

                    //Se não entrar no if/else if o valor da variavel booleana não sofre alteração e continua com seu valor inicial
                }//for

                //Se ao sair do ultimo for que seria a verificação da lista(estados) o valor da ultimaVerificacao for true a palavra é aceita
                if(ultimaVerificacao){
                   System.out.println("Palavra aceita!");
                //Caso contrário a palavra não é aceita
                }else{
                    System.out.println("Palavra não aceita!");
                }//else

                //Volta para o valor inicial da variavel para testar a próxima palavra
                config.setProximoEstado(0);

                //Volta para o valor inicial da variavel para testar a próxima palavra
                config.setRodaIndicePalavra(0);
            }//else verificação primeiro estado
        }//for qtdVezes

        sc.close();//libera o scanner
    }//main
}//classe