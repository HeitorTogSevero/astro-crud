package org.astro.astro_app.model;

// Bibliotecas:
import java.sql.Date;

// Classe da Model Certificado
public class Certificado {

    // Atributos Inteiros
    private int idCertificado;
    private int idFuncionario;
    private int idNrFuncionario;
    private int idNrEmpresa;

    // Atributos Date
    private Date dtEmissao;
    private Date dtValidade;

    // Construtor completo para inicializar um Certificado
    public Certificado(int idCertificado, int idFuncionario, int idNrFuncionario, int idNrEmpresa, Date dtEmissao, Date dtValidade) {
        this.idCertificado = idCertificado;
        this.idFuncionario = idFuncionario;
        this.idNrFuncionario = idNrFuncionario;
        this.idNrEmpresa = idNrEmpresa;
        this.dtEmissao = dtEmissao;
        this.dtValidade = dtValidade;
    }

    // Construtor sem o ID
    public Certificado(int idFuncionario, int idNrFuncionario, Date dtEmissao, Date dtValidade, int idNrEmpresa) {
        this.idFuncionario = idFuncionario;
        this.idNrFuncionario = idNrFuncionario;
        this.dtEmissao = dtEmissao;
        this.dtValidade = dtValidade;
        this.idNrEmpresa = idNrEmpresa;
    }

    // Construtor vaziu (para recuperar dados já existentes do banco de dados)
    public Certificado(){}

    // Getter

    public int getIdCertificado() {
        return idCertificado;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public int getIdNrFuncionario() {
        return idNrFuncionario;
    }

    public int getIdNrEmpresa() {
        return idNrEmpresa;
    }

    public Date getDtEmissao() {
        return dtEmissao;
    }

    public Date getDtValidade() {
        return dtValidade;
    }
}
