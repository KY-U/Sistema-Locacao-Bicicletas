package br.ufscar.dc.dsw.model;

public class Locacoes {
    private String cpf_cliente;
    private String cnpj_locadora;
    private String data_inicio;

    public Locacoes (String cpf_cliente, String cnpj_locadora, String data_inicio) {
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

    public String getDataInicio() {
        return data_inicio;
    }

    //setters
    public void setCpfCliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public void setCnpjLocadora(String cnpj_locadora) {
        this.cnpj_locadora = cnpj_locadora;
    }

    public void setDataInicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }
}
