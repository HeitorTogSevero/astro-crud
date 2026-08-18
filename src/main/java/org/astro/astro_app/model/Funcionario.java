package org.astro.astro_app.model;

public class Funcionario {
    private String nome;
    private String cargo;

    private int idFuncionario;
    private int idCronograna;
    private long cpf;

    public Funcionario(String nome, int idFuncionario, int idCronograna, String cargo, long cpf) {
        this.nome = nome;
        this.idFuncionario = idFuncionario;
        this.idCronograna = idCronograna;
        this.cargo = cargo;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public int getIdCronograna() {
        return idCronograna;
    }

    public String getCargo() {
        return cargo;
    }

    public long getCpf() {
        return cpf;
    }

}