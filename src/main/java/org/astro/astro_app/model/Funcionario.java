package org.astro.astro_app.model;

// Classe Model Funcionario:
public class Funcionario {
    //Declaração de Variaveis:

    // Atributos da Classe:
    private String nome;
    private String cargo;
    private String cpf;

    private int idEmpresa;
    private int idFuncionario;
    private int idDepartamento;


    // Metodo Construtor da classe


    public Funcionario(String nome, String cargo, String cpf, int idEmpresa, int idFuncionario, int idDepartamento) {
        this.nome = nome;
        this.cargo = cargo;
        this.cpf = cpf;
        this.idEmpresa = idEmpresa;
        this.idFuncionario = idFuncionario;
        this.idDepartamento = idDepartamento;
    }

    // Metodos Getters:

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }
}