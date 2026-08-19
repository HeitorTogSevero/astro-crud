package org.astro.astro_app.model;

// Bibliotecas:
import java.time.LocalDate;

// Classe da Model Certificado
public class Certificado {
    // Declaração de Variaveis:

    // Atributos da Classe:
    private int idCertificado;
    private int idFuncionario;
    private int idNrFuncionario;

    private LocalDate dtEmissao;
    private LocalDate dtValidade;

    // Metodo Construtor da classe:
    public Certificado(int idCertificado, int idFuncionario, int idNrFuncionario, LocalDate dtEmissao, LocalDate dtValidade) {
        this.idCertificado = idCertificado;
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

    public LocalDate getDtEmissao() {
        return dtEmissao;
    }

    public LocalDate getDtValidade() {
        return dtValidade;
    }

}
