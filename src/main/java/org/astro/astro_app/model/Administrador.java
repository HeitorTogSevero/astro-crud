package org.astro.astro_app.model;

public class Administrador {

    private int idAdmin;

    private String nome;
    private String email;
    private String senha;

    public Administrador(int idAdmin, String nome, String email, String senha) {
        this.idAdmin = idAdmin;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Administrador() {
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
