package org.astro.astro_app.model;

//Classe model NrEmpresa
public class NrEmpresa {
    //Declaração de variáveis

    //Atributos da classe
    private int idNremp;
    private int idNrDescricao;
    private String titulo;
    private String status;

    //Método construtor da classe
    public NrEmpresa(int idNremp, int idNrDescricao, String titulo, String status) {
        this.idNremp = idNremp;
        this.idNrDescricao = idNrDescricao;
        this.titulo = titulo;
        this.status = status;
    }

    //Métodos Getters
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