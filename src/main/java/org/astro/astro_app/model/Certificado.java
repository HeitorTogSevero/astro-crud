package org.astro.astro_app.model;

// Bibliotecas:
import java.sql.Date;

// Classe da Model Certificado
public class Certificado {
    // Declaração de Variaveis:

    // Atributos da Classe:
    private int idCertificado;
    private int idFuncionario;
    private int idNrFuncionario;
    private int idNrEmpresa;

    private Date dtEmissao;
    private Date dtValidade;

    // Metodo Construtor da classe:
    public Certificado(int idCertificado, int idFuncionario, int idNrFuncionario, int idNrEmpresa, Date dtEmissao, Date dtValidade) {
        this.idCertificado = idCertificado;
        this.idFuncionario = idFuncionario;
        this.idNrFuncionario = idNrFuncionario;
        this.idNrEmpresa = idNrEmpresa;
        this.dtEmissao = dtEmissao;
        this.dtValidade = dtValidade;
    }

    public Certificado(int idFuncionario, int idNrFuncionario, Date dtEmissao, Date dtValidade) {
        this.idFuncionario = idFuncionario;
        this.idNrFuncionario = idNrFuncionario;
        this.dtEmissao = dtEmissao;
        this.dtValidade = dtValidade;
    }

    // Metodo Getters:

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
