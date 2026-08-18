package org.astro.astro_app.model;
public class NrDescricao {
    private int idNrDescricao;
    private String descricao;
    private int tempoReciclagem;
    private String titulo;

    public NrDescricao(int idNrDescricao, String descricao, int tempoReciclagem, String titulo) {
        this.idNrDescricao = idNrDescricao;
        this.descricao = descricao;
        this.tempoReciclagem = tempoReciclagem;
        this.titulo = titulo;
    }

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