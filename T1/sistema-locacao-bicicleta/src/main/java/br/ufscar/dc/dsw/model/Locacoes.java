package br.ufscar.dc.dsw.model;
import sun.awt.TimedWindowEvent;

import java.sql.Date;
import java.sql.Timestamp;


public class Locacoes {
    private int id;
    private String cpfCliente;
    private String cnpjLocadora;
    private Timestamp dataInicio;

    public Locacoes() {}

    public Locacoes (String cpfCliente, String cnpjLocadora, Timestamp dataInicio) {
        this.cpfCliente = cpfCliente;
        this.cnpjLocadora = cnpjLocadora;
        this.dataInicio = dataInicio;
    }

    public Locacoes (int id, String cpfCliente, String cnpjLocadora, Timestamp dataInicio) {
        this.cpfCliente = cpfCliente;
        this.cnpjLocadora = cnpjLocadora;
        this.dataInicio = dataInicio;
    }

    //getters
    public String getCpfCliente() {
        return cpfCliente;
    }

    public String getCnpjLocadora() {
        return cnpjLocadora;
    }

    public Timestamp getDataInicio() {
        return dataInicio;
    }

    public int getId() {
        return id;
    }



    //setters
    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public void setCnpjLocadora(String cnpjLocadora) {
        this.cnpjLocadora = cnpjLocadora;
    }

    public void setDataInicio(Timestamp dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setId(int id) {
        this.id = id;
    }
}
