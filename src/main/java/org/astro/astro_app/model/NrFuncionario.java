package org.astro.astro_app.model;

import java.sql.Date;
import java.time.LocalDate;

// Classe Model NrFuncionario
public class NrFuncionario {

    //Atributos Inteiro
    private int idNrFunc;
    private int idNrDescricao;
    private int numero;

    // Atributos Date
    private Date dtRealizacao;

    // Atributos String
    private String titulo;
    private String status;


    // Construtor completo para inicializar uma NrFuncionario
    public NrFuncionario(int idNrFunc, int idNrDescricao, int numero, Date dtRealizacao, String titulo, String status) {
        this.idNrFunc = idNrFunc;
        this.idNrDescricao = idNrDescricao;
        this.numero = numero;
        this.dtRealizacao = dtRealizacao;
        this.titulo = titulo;
        this.status = status;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public NrFuncionario(){}

    // Construtor sem o ID
    public NrFuncionario(int idNrDescricao, int numero, Date dtRealizacao, String titulo, String status) {
        this.idNrDescricao = idNrDescricao;
        this.numero = numero;
        this.dtRealizacao = dtRealizacao;
        this.titulo = titulo;
        this.status = status;
    }

    // Getter

    public int getIdNrFunc() {
        return idNrFunc;
    }

    public int getIdNrDescricao() {
        return idNrDescricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public Date getDtRealizacao() {
        return dtRealizacao;
    }

    public int getNumero(){return numero;}

    public String getStatus(){return  status;}
}
