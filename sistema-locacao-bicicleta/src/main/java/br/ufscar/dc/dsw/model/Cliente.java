package main.java.br.ufscar.dc.dsw.model;

public class Cliente {
    private String email;
    private String senha;
    private String cpf;
    private String nome;
    private String telefone;
    private String sexo;
    private String dataNascimento;

<<<<<<< HEAD
    public Cliente (String email, String senha, String cpf, String nome, String telefone, String sexo, String dataNascimento) {
=======
    public Cliente(String email, String senha, String cpf, String nome, String telefone, String sexo, String dataNascimento) {
>>>>>>> caaf6b95d229cd837a334a62d4c8f29a1a96a026
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }
<<<<<<< HEAD
    
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
=======

    public String getEmail() {
        return email;
    }

>>>>>>> caaf6b95d229cd837a334a62d4c8f29a1a96a026
    public void setEmail(String email) {
        this.email = email;
    }

<<<<<<< HEAD
=======
    public String getSenha() {
        return senha;
    }

>>>>>>> caaf6b95d229cd837a334a62d4c8f29a1a96a026
    public void setSenha(String senha) {
        this.senha = senha;
    }

<<<<<<< HEAD
=======
    public String getCpf() {
        return cpf;
    }

>>>>>>> caaf6b95d229cd837a334a62d4c8f29a1a96a026
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

<<<<<<< HEAD
    public void setNome(String nome) {
        this.nome = nome;
    }   
=======
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }
>>>>>>> caaf6b95d229cd837a334a62d4c8f29a1a96a026

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

<<<<<<< HEAD
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }   
=======
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
>>>>>>> caaf6b95d229cd837a334a62d4c8f29a1a96a026

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
