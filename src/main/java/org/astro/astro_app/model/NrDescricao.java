package org.astro.astro_app.model;

//Classe model NrDescricao
public class NrDescricao {

    // Atributos Inteiros
    private int idNrDescricao;
    private String tempoReciclagem;

    // Atributos String
    private String descricao;
    private String titulo;

    // Construtor completo para inicializar uma NrDescricao
    public NrDescricao(int idNrDescricao, String descricao, String tempoReciclagem, String titulo) {
        this.idNrDescricao = idNrDescricao;
        this.descricao = descricao;
        this.tempoReciclagem = tempoReciclagem;
        this.titulo = titulo;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public NrDescricao(){}

    // Construtor sem o ID
    public NrDescricao(String tempoReciclagem, String descricao, String titulo) {
        this.tempoReciclagem = tempoReciclagem;
        this.descricao = descricao;
        this.titulo = titulo;
    }

    // Getter
    public int getIdNrDescricao() {
        return idNrDescricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTempoReciclagem() {
        return tempoReciclagem;
    }

    public String getTitulo() {
        return titulo;
    }
}