package entities;
//Classe criada para organizar melhor o código, deixando as variaveis basicas da configuração do LFA em um lugar que pouco usaria e deixaria o arquivo principal com os comandos do lfa(exibição, preenchimento e verificação)
public class LFA {
    private int estadoInicial;
    private int quantidadeDeEstados;
    private int quantidadeEstadoFinal;
    private int estadoFinal;
    private int quantidadeSimbolosAlfabeto;
    private char[] simbolos;
    private int proximoEstado;
    private int rodaIndicePalavra;

    //Construtor para passar os valores do arquivo principal para os atributos da classe
    public LFA(int estadoInicial, int quantidadeDeEstados, int quantidadeEstadoFinal, int estadoFinal, int quantidadeSimbolosAlfabeto, char[] simbolos) {
        this.estadoInicial = estadoInicial;
        this.quantidadeDeEstados = quantidadeDeEstados;
        this.quantidadeEstadoFinal = quantidadeEstadoFinal;
        this.estadoFinal = estadoFinal;
        this.quantidadeSimbolosAlfabeto = quantidadeSimbolosAlfabeto;
        this.simbolos = simbolos;
        this.proximoEstado = 0;
        this.rodaIndicePalavra = 0;
    }

    //Necessário para conseguir acessar o valor armazenado pelo atributo no arquivo principal
    public int getProximoEstado() {
        return proximoEstado;
    }

    //Necessário para conseguir alterar o valor armazenado pelo atributo no arquivo principal
    public void setProximoEstado(int proximoEstado) {
        if(proximoEstado == 0){
            this.proximoEstado = 0;//Se o valor passado for 0, torna o valor 0 para poder verificar outras palavras
        }else{
            this.proximoEstado = proximoEstado;//Se o valor não for zero, significa que a verificação de uma palavra ainda não terminou
        }
    }

    //Necessário para conseguir acessar o valor armazenado pelo atributo no arquivo principal
    public int getRodaIndicePalavra() {
        return rodaIndicePalavra;
    }

    //Necessário para conseguir alterar o valor armazenado pelo atributo no arquivo principal
    public void setRodaIndicePalavra(int rodaIndicePalavra) {
        if(rodaIndicePalavra == 0){//Se o valor passado for 0, torna o valor 0 para poder verificar outras palavras
            this.rodaIndicePalavra = 0;
        }else{
            this.rodaIndicePalavra += rodaIndicePalavra;//Se o valor não for zero, significa que a verificação de uma palavra ainda não terminou
        }
    }
}