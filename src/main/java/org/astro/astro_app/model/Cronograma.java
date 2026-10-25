package org.astro.astro_app.model;

// Bibliotecas:
import java.sql.Date;

//Classe model Cronograma
public class Cronograma {

    //Atributos Inteiros
    private int idFuncionario;
    private int idEmpresa;
    private int idCronograma;

    // Atributos Date
    private Date agendamento;


    // Construtor completo para inicializar um Cronograma
    public Cronograma(int idFuncionario, int idEmpresa, int idCronograma, Date agendamento) {
        this.idFuncionario = idFuncionario;
        this.idEmpresa = idEmpresa;
        this.idCronograma = idCronograma;
        this.agendamento = agendamento;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public Cronograma(){}

    // Construtor sem o ID
    public Cronograma(int idFuncionario, int idEmpresa, Date agendamento) {
        this.idFuncionario = idFuncionario;
        this.idEmpresa = idEmpresa;
        this.agendamento = agendamento;
    }

    // Getter
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