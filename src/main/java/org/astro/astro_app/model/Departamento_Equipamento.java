package org.astro.astro_app.model;

public class Departamento_Equipamento {

    // Atributos Inteiros:
    private int idDepartamento;
    private int idEquipamento;

    // Construtor completo para inicializar um Departamento_Equipamento
    public Departamento_Equipamento(int idDepartamento, int idEquipamento) {
        this.idDepartamento = idDepartamento;
        this.idEquipamento = idEquipamento;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public Departamento_Equipamento(){}

    // Getter
    public int getIdDepartamento() {
        return idDepartamento;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }
}
