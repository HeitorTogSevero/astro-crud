package org.astro.astro_app.model;

public class Email {
    //Declaração de Variaveis

    // Atributos da classe:
    private int idEmail;
    private int idFuncionario;

    private String email;

    //Metodo Construtor da classe:
    public Email(int idEmail, int idFuncionario, String email) {
        this.idEmail = idEmail;
        this.idFuncionario = idFuncionario;
        this.email = email;
    }

    // Metodos Getters:
    public int getIdEmail() {
        return idEmail;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public String getEmail() {
        return email;
    }
}
