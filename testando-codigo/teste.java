//Pedir ao usuario digitar uma palavra
System.out.print("Digite uma palavra: ");
String palavra = sc.nextLine();

//Verificar o primeiro estado
for(int i = 1; i < 2; i++){
    for(int j = 0, x = 0; j < quantidadeSimbolosAlfabeto; j++){
        if(palavra.charAt(x) == matriz[x][j] && matriz[i][j] != -1){//Se a primeira letra da palavra estiver na primeira linha da matriz e na posição i e j for diferente de -1, armazena o valor da posição i,j da matriz na variavel proximoEstado, caso nao aconteça, a variavel continua com valor 0
            config.setProximoEstado(matriz[i][j]);//Altera o valor da variavel criada na classe para o valor armazenado na posição determinada da matriz
            config.setRodaIndicePalavra(1);//Altera o valor da variavel que roda os indices da palavra em 1
        }//if
    }//for j
}//for i

//se nao teve alteração no valor da variavel, a palavra não é aceita
if(config.getProximoEstado() == 0){
    System.out.println("Palavra não aceita");
}else{//Altera o valor da variavel para o valor do proximo estado
    for(int i = 0; i < palavra.length() - 1; i++){

        //chama a função que verifica a palavra dentro da matriz e altera o valor do proximo estado
        int proximoEstado = verificaEstado(matriz, quantidadeSimbolosAlfabeto, config.getProximoEstado(), palavra, config.getRodaIndicePalavra());

        //Verifica se o valor da variavel que recebe o retorno da função e a variavel da classe que armazena o proximoEstado são iguais
        if(proximoEstado != config.getProximoEstado() || i < palavra.length() - 1){
            config.setProximoEstado(proximoEstado);//altera o antigo valor da variavel da classe proximoEstado para o valor que foi alterado na função
            config.setRodaIndicePalavra(1);
        }//if

        else{//Se forem diferentes, isso implica que houve uma alteração devido a letra determinada esta de acordo com a tabela de transição
            System.out.println("Palavra não aceita");//sendo assim, imprime na tela que a palavra não pertence à linguagem
            break;
        }//else
    }//for i

    if(config.getProximoEstado() == quantidadeDeEstados){
        System.out.println("Palavra aceita");
    }else{
        System.out.println("Palavra não aceita");
    }//else palavra aceita ou não
}//else verificação primeiro estado