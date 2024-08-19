package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import br.ufscar.dc.dsw.validation.UniqueLocacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@UniqueLocacao
@Table(name = "Locacao")
public class Locacao extends AbstractEntity<Long> {

	@NotNull
	@Column(nullable = false, length = 19)
	private String data;
    
	@NotNull
	@Column(columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private BigDecimal valor;

	@NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotNull
	@ManyToOne
	@JoinColumn(name = "locadora_id")
	private Locadora locadora;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

    public Locadora getLocadora() {
		return locadora;
	}

	public void setLocadora(Locadora locadora) {
		this.locadora = locadora;
	}   
}
