package org.astro.astro_app.model;

//Classe model DivisaoTreinamento
public class DivisaoTreinamento {
    //Declaração de variáveis

    //Atributos da classe
    private int idDivisao;
    private int idCronograma;
    private int cnpj;
    private String nome;
    private int qtdFuncionario;

    //Método construtor da classe
    public DivisaoTreinamento(int idDivisao, int idCronograma, int cnpj, String nome, int qtdFuncionario) {
        this.idDivisao = idDivisao;
        this.idCronograma = idCronograma;
        this.cnpj = cnpj;
        this.nome = nome;
        this.qtdFuncionario = qtdFuncionario;
    }

    //Métodos Getters
    public int getIdDivisao() {
        return idDivisao;
    }

    public int getIdCronograma() {
        return idCronograma;
    }

    public int getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdFuncionario() {
        return qtdFuncionario;
    }
}
