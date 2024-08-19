package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;


import br.ufscar.dc.dsw.domain.Locacao;

@SuppressWarnings("unchecked")
public interface ILocacaoDAO extends CrudRepository<Locacao, Long>{

	Locacao findById(long id);

	@Query("SELECT locacao FROM Locacao WHERE cliente.id = ?1")
    List<Locacao> findAllByCliente(Long clienteId);

	@Query("SELECT locacao FROM Locacao WHERE locadora.id = ?1")
    List<Locacao> findAllByLocadora(Long locadoraId);
	
	Locacao save(Locacao locacao);
	
	void deleteById(Long id);

	@Query("SELECT locacao FROM Locacao WHERE (locadora.id = ?1 OR cliente.id = ?2) AND (data = ?3) ")
	List<Locacao> buscarLocacoesPorClienteELocadoraEData(Long locadora, Long cliente, String data);
}