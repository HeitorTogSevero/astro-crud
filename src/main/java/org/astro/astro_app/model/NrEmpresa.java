package org.astro.astro_app.model;

public class NrEmpresa {
    private int idNremp;
    private int idNrDescricao;
    private String titulo;
    private String status;


    public NrEmpresa(int idNremp, int idNrDescricao, String titulo, String status) {
        this.idNremp = idNremp;
        this.idNrDescricao = idNrDescricao;
        this.titulo = titulo;
        this.status = status;
    }

    public int getIdNremp() {
        return idNremp;
    }

    public int getIdNrDescricao() {
        return idNrDescricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getStatus() {
        return status;
    }
}