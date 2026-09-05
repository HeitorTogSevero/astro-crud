package org.astro.astro_app.model;

import java.sql.Date;
import java.time.LocalDate;

// Classe Model NrFuncionario
public class NrFuncionario {
    //Declaração de Variavéis:

    //Atributos da classe:
    private int idNrFunc;
    private int idNrDescricao;
    private int numero;

    private Date dtRealizacao;

    private String titulo;
    private String status;


    //Metodo Construtor da Classe Nr_Funcionario:
    public NrFuncionario(int idNrFunc, int idNrDescricao, int numero, Date dtRealizacao, String titulo, String status) {
        this.idNrFunc = idNrFunc;
        this.idNrDescricao = idNrDescricao;
        this.numero = numero;
        this.dtRealizacao = dtRealizacao;
        this.titulo = titulo;
        this.status = status;
    }

    //Metodos Getters:
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
