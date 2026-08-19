package org.astro.astro_app.model;

//Classe model Cronograma
public class Cronograma {
    //Declaração de variáveis

    //Atributos da classe
    private int idFuncionario;
    private String agendamentos;
    private int idEmpresa;
    private int idDivisao;

    //Método construtor da classe
    public Cronograma(int idFuncionario, String agendamentos, int idEmpresa, int idDivisao) {
        this.idFuncionario = idFuncionario;
        this.agendamentos = agendamentos;
        this.idEmpresa = idEmpresa;
        this.idDivisao = idDivisao;
    }

    //Métodos Getters
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public String getAgendamentos() {
        return agendamentos;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public int getIdDivisao() {
        return idDivisao;
    }
}