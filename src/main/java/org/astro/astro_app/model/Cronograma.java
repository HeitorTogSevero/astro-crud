package org.astro.astro_app.model;

//Classe model Cronograma
public class Cronograma {
    //Declaração de variáveis

    //Atributos da classe
    private int idFuncionario;
    private int idEmpresa;
    private int idCronograma;

    private String agendamentos;


    //Metodo construtor da classe


    public Cronograma(int idFuncionario, int idEmpresa, int idCronograma, String agendamentos) {
        this.idFuncionario = idFuncionario;
        this.idEmpresa = idEmpresa;
        this.idCronograma = idCronograma;
        this.agendamentos = agendamentos;
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

    public int getIdCronograma() {
        return idCronograma;
    }
}