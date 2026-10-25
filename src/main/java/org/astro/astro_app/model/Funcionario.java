package org.astro.astro_app.model;

// Classe Model Funcionario:
public class Funcionario {

    // Atributos Inteiros
    private String nome;
    private String cargo;
    private String cpf;
    private String email;

    // Atributos String
    private int idEmpresa;
    private int idFuncionario;
    private int idDepartamento;


    // Construtor completo para inicializar um Funcionario
    public Funcionario(String nome, String cargo, String cpf, String email, int idEmpresa, int idFuncionario, int idDepartamento) {
        this.nome = nome;
        this.cargo = cargo;
        this.cpf = cpf;
        this.email = email;
        this.idEmpresa = idEmpresa;
        this.idFuncionario = idFuncionario;
        this.idDepartamento = idDepartamento;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public Funcionario(){

    }

    // Construtor sem o ID
    public Funcionario(String nome, String cargo, String cpf, String email, int idEmpresa, int idDepartamento) {
        this.nome = nome;
        this.cargo = cargo;
        this.cpf = cpf;
        this.email = email;
        this.idEmpresa = idEmpresa;
        this.idDepartamento = idDepartamento;
    }

    // Getter

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

    public String getEmail(){
        return email;
    }
}