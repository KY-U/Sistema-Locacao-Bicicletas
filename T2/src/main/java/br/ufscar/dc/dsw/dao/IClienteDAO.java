package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Cliente;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, Long>{
	Cliente findById(long id);
    //Cliente findByCPF (String CPF);
	List<Cliente> findAll();
	Cliente save(Cliente cliente);
	void deleteById(Long id);

	@Query("SELECT cliente FROM Cliente WHERE cliente.cpf = :cpf")
    public Cliente findByCpf(@Param("cpf") String cpf);

	@Query("SELECT cliente FROM Cliente WHERE cliente.email = :email")
	Cliente findByEmail(@Param ("email") String email);

}