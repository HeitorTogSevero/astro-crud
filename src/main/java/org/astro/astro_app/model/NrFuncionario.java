package org.astro.astro_app.model;

import java.time.LocalDate;

// Classe Model NR_Funcionario
public class NrFuncionario {

//    Declaração de Variavéis:
    private int idNrFunc;
    private int idNrDescricao;
    private int tempoReciclagem;

    private String titulo;
    private String status;

    private LocalDate dtRealizacao;

//    Metodo Construtor da Classe Nr_Funcionario:
    public NrFuncionario(int idNrFunc, int idNrDescricao, int tempoReciclagem, String titulo, LocalDate dtRealizacao, String status) {

        this.idNrFunc = idNrFunc;
        this.idNrDescricao = idNrDescricao;
        this.tempoReciclagem = tempoReciclagem;
        this.titulo = titulo;
        this.dtRealizacao = dtRealizacao;
        this.status = status;

    }

//    Getters da Classe:
    public int getIdNrFunc() {
        return idNrFunc;
    }

    public int getIdNrDescricao() {
        return idNrDescricao;
    }

    public int getTempoReciclagem() {
        return tempoReciclagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getDtRealizacao() {
        return dtRealizacao;
    }
}
