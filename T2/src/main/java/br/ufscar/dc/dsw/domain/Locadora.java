package br.ufscar.dc.dsw.domain;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import br.ufscar.dc.dsw.validation.UniqueCNPJ;
import jakarta.persistence.CascadeType;


@SuppressWarnings("serial")
@Entity
@Table(name = "Locadora")
public class Locadora extends Usuario {

	@NotBlank
    @UniqueCNPJ (message = "CNPJ já cadastrado")
	@Size(min = 13, max = 18, message = "Número de caracteres inválido")
	@Column(nullable = false, unique = true, length = 60)
	private String cnpj;

    @Column(nullable = true, length = 50)
    private String cidade;

	@OneToMany(mappedBy = "locadora", cascade = CascadeType.ALL)
    private List<Locacao> locacoes;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

    public String getCidade(){
        return cidade;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }

	public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }
}