package org.astro.astro_app.model;

//Classe model DivisaoTreinamento
public class DivisaoTreinamento {
    //Declaração de variáveis

    //Atributos da classe
    private int idDivisao;
    private int idCronograma;
    private int codigo;
    private int qtdFuncionario;

    private String nome;


    //Metodo construtor da classe
    public DivisaoTreinamento(int idDivisao, int idCronograma, int codigo, int qtdFuncionario, String nome) {
        this.idDivisao = idDivisao;
        this.idCronograma = idCronograma;
        this.codigo = codigo;
        this.qtdFuncionario = qtdFuncionario;
        this.nome = nome;
    }

    //Métodos Getters
    public int getIdDivisao() {
        return idDivisao;
    }

    public int getIdCronograma() {
        return idCronograma;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdFuncionario() {
        return qtdFuncionario;
    }
}
