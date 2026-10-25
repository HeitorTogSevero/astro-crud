package org.astro.astro_app.model;

// Classe da Model Aministrador
public class Administrador {

    // Atributos inteiros
    private int idAdmin;

    // Atributos String
    private String nome;
    private String email;
    private String senha;

    // Construtor completo para inicializar um administrador
    public Administrador(int idAdmin, String nome, String email, String senha) {
        this.idAdmin = idAdmin;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public Administrador() {}

    // Construtor sem o ID
    public Administrador(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getter
    public int getIdAdmin() {
        return idAdmin;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    // Setter
    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
