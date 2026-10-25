package org.astro.astro_app.model;

// Bibliotecas:
import java.sql.Date;

// Classe da Model Alerta
public class Alerta {

    // Atributos
    private int idAlerta;
    private int codigo;
    private int idEmpresa;

    // Atributos String
    private String descricao;

    // Atributos Date
    private Date dtLimite;

    // Construtor completo para inicializar um alerta
    public Alerta(int idAlerta, int codigo, int idEmpresa, String descricao, Date dtLimite) {
        this.idAlerta = idAlerta;
        this.codigo = codigo;
        this.idEmpresa = idEmpresa;
        this.descricao = descricao;
        this.dtLimite = dtLimite;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public Alerta(){

    }

    // Construtor sem o ID
    public Alerta(int codigo, int idEmpresa, String descricao, Date dtLimite) {
        this.codigo = codigo;
        this.idEmpresa = idEmpresa;
        this.descricao = descricao;
        this.dtLimite = dtLimite;
    }

    // Getter
    public int getIdAlerta() {
        return idAlerta;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDtLimite() {
        return dtLimite;
    }
}
