package org.astro.astro_app.model;

//Classe Model DivisãoDeTreinamento
public class DivisaoTreinamento {

    // Declaração de Variaveis:

    // Atributos da Classe:
    private int idDivisao;
    private int idCronograma;
    private long cnpj;
    private String nome;
    private int qtdFuncionario;

    // Metodo Construtor da Classe DivisãoDeTreinamento:
    public DivisaoTreinamento(int idDivisao, int idCronograma, long cnpj, String nome, int qtdFuncionario) {
        this.idDivisao = idDivisao;
        this.idCronograma = idCronograma;
        this.cnpj = cnpj;
        this.nome = nome;
        this.qtdFuncionario = qtdFuncionario;
    }
    // Metodos Getters:
    public int getIdDivisao() {
        return idDivisao;
    }

    public int getIdCronograma() {
        return idCronograma;
    }

    public long getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdFuncionario() {
        return qtdFuncionario;
    }
}
