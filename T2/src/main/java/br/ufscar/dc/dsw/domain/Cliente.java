package br.ufscar.dc.dsw.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.UniqueCpf;

import jakarta.persistence.OneToMany;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

 
@Entity
@Table(name = "Cliente")
public class Cliente extends Usuario {
    
    @NotBlank
	@UniqueCpf(message = "CPF já cadastrado")
	@Size(min = 11, max = 15, message = "Número de caracteres inváE1lido")
    @Column(nullable = false, length = 45 )
    private String cpf;
    
    @Column(nullable = false, length = 64)
    private String telefone;
    
    @Column(nullable = false, length = 45)
    private String genero;
    
    @Column(nullable = false, length = 15)
    private String dataNasc;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Locacao> locacoes;
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getDataNasc() {
		return dataNasc;
	}
	
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }
}