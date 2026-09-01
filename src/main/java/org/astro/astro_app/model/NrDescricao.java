package org.astro.astro_app.model;

//Classe model NrDescricao
public class NrDescricao {

    //Declaração de variaveis

    //Atributos da classe
    private int idNrDescricao;
    private int tempoReciclagem;

    private String descricao;
    private String titulo;

    //Metodo construtor da classe
    public NrDescricao(int idNrDescricao, String descricao, int tempoReciclagem, String titulo) {
        this.idNrDescricao = idNrDescricao;
        this.descricao = descricao;
        this.tempoReciclagem = tempoReciclagem;
        this.titulo = titulo;
    }

    //Metodos Getters
    public int getIdNrDescricao() {
        return idNrDescricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getTempoReciclagem() {
        return tempoReciclagem;
    }

    public String getTitulo() {
        return titulo;
    }
}