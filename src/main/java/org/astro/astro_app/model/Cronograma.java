package org.astro.astro_app.model;

import java.sql.Date;

//Classe model Cronograma
public class Cronograma {
    //Declaração de variáveis

    //Atributos da classe
    private int idFuncionario;
    private int idEmpresa;
    private int idCronograma;

    private Date agendamento;


    //Metodo construtor da classe


    public Cronograma(int idFuncionario, int idEmpresa, int idCronograma, Date agendamento) {
        this.idFuncionario = idFuncionario;
        this.idEmpresa = idEmpresa;
        this.idCronograma = idCronograma;
        this.agendamento = agendamento;
    }

    //Métodos Getters
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public Date getAgendamento() {
        return agendamento;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public int getIdCronograma() {
        return idCronograma;
    }
}