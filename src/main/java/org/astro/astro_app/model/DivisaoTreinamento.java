package org.astro.astro_app.model;

//Classe model DivisaoTreinamento
public class DivisaoTreinamento {

    //Atributos Inteiros
    private int idDivisao;
    private int idCronograma;
    private int codigo;
    private int idEmpresa;

    // Atributos String
    private String nome;


    // Construtor completo para inicializar uma divisão
    public DivisaoTreinamento(int idDivisao, int idCronograma, int codigo, int idEmpresa, String nome) {
        this.idDivisao = idDivisao;
        this.idCronograma = idCronograma;
        this.codigo = codigo;
        this.idEmpresa = idEmpresa;
        this.nome = nome;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public DivisaoTreinamento(){}

    // Construtor sem o ID
    public DivisaoTreinamento( int idCronograma, int codigo, int idEmpresa, String nome) {
        this.idCronograma = idCronograma;
        this.codigo = codigo;
        this.idEmpresa = idEmpresa;
        this.nome = nome;
    }

    // Getter

    public int getIdDivisao() {
        return idDivisao;
    }

    public int getIdCronograma() {
        return idCronograma;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public String getNome() {
        return nome;
    }
}
