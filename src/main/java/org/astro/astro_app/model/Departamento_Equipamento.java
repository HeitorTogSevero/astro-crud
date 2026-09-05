package org.astro.astro_app.model;

public class Departamento_Equipamento {
    //Declaração de Variaveis

    // Atributos da Classe:
    private int idDepartamento;
    private int idEquipamento;

    //Metodo Construtor da classe:
    public Departamento_Equipamento(int idDepartamento, int idEquipamento) {
        this.idDepartamento = idDepartamento;
        this.idEquipamento = idEquipamento;
    }

    //Metodos Getters:
    public int getIdDepartamento() {
        return idDepartamento;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }
}
