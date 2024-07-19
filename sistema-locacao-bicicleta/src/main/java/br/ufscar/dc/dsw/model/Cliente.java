package main.java.br.ufscar.dc.dsw.model;

public class Cliente {
    private String email;
    private String senha;
    private String cpf;
    private String nome;
    private String telefone;
    private String sexo;
    private String dataNascimento;

    public Cliente (String email, String senha, String cpf, String nome, String telefone, String sexo, String dataNascimento) {
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }
    
    //getters
    public String getEmail() {
        return email;
    }
    
    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    //setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }   

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }   

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
