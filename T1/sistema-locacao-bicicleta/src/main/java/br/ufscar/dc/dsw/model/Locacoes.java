package br.ufscar.dc.dsw.model;
import sun.awt.TimedWindowEvent;

import java.sql.Date;
import java.sql.Timestamp;


public class Locacoes {
    private int id;
    private String cpf_cliente;
    private String cnpj_locadora;
    private Timestamp data_inicio;

    public Locacoes() {}

    public Locacoes (String cpf_cliente, String cnpj_locadora, Timestamp data_inicio) {
        this.cpf_cliente = cpf_cliente;
        this.cnpj_locadora = cnpj_locadora;
        this.data_inicio = data_inicio;
    }

    public Locacoes (int id, String cpf_cliente, String cnpj_locadora, Timestamp data_inicio) {
        this.cpf_cliente = cpf_cliente;
        this.cnpj_locadora = cnpj_locadora;
        this.data_inicio = data_inicio;
    }

    //getters
    public String getCpfCliente() {
        return cpf_cliente;
    }

    public String getCnpjLocadora() {
        return cnpj_locadora;
    }

    public Timestamp getDataInicio() {
        return data_inicio;
    }

    public int getId() {
        return id;
    }



    //setters
    public void setCpfCliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public void setCnpjLocadora(String cnpj_locadora) {
        this.cnpj_locadora = cnpj_locadora;
    }

    public void setDataInicio(Timestamp data_inicio) {
        this.data_inicio = data_inicio;
    }

    public void setId(int id) {
        this.id = id;
    }
}
