package org.astro.astro_app.model;

public class DivisaoTreinamento {
    private int idDivisao;
    private int idCronograma;
    private int cnpj;
    private String nome;
    private int qtdFuncionario;

    public DivisaoTreinamento(int idDivisao, int idCronograma, int cnpj, String nome, int qtdFuncionario) {
        this.idDivisao = idDivisao;
        this.idCronograma = idCronograma;
        this.cnpj = cnpj;
        this.nome = nome;
        this.qtdFuncionario = qtdFuncionario;
    }

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
