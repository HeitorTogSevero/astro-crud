package org.astro.astro_app.model;

public class Cronograma {
    private int idFuncionario;
    private String agendamentos;
    private int idEmpresa;
    private int idDivisao;

    public Cronograma(int idFuncionario, String agendamentos, int idEmpresa, int idDivisao) {
        this.idFuncionario = idFuncionario;
        this.agendamentos = agendamentos;
        this.idEmpresa = idEmpresa;
        this.idDivisao = idDivisao;
    }

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