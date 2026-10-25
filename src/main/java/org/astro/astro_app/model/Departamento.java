package org.astro.astro_app.model;

//Classe model Departamento
public class Departamento {

   //Atributos Inteiros
    private int idDepartamento;
    private int idEmpresa;

    // Atributos String
    private String grauRisco;
    private String nome;
    private String descricao;
    private String atividade;

    // Construtor completo para inicializar um Departamento
    public Departamento(int idDepartamento, int idEmpresa, String grauRisco, String nome, String descricao, String atividade) {
        this.idDepartamento = idDepartamento;
        this.idEmpresa = idEmpresa;
        this.grauRisco = grauRisco;
        this.nome = nome;
        this.descricao = descricao;
        this.atividade = atividade;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public Departamento(){}

    // Construtor sem o ID
    public Departamento(int idEmpresa, String grauRisco, String nome, String descricao, String atividade) {
        this.idEmpresa = idEmpresa;
        this.grauRisco = grauRisco;
        this.nome = nome;
        this.descricao = descricao;
        this.atividade = atividade;
    }

    // Getter

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public String getGrauRisco() {
        return grauRisco;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getAtividade() {
        return atividade;
    }
}