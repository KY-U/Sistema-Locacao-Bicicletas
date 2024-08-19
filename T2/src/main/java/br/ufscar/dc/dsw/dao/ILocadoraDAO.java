package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Locadora;

@SuppressWarnings("unchecked")
public interface ILocadoraDAO extends CrudRepository<Locadora, Long>{
	Locadora findById(long id);
    //Locadora findByCNPJ (String CNPJ);
	List<Locadora> findAll();
	Locadora save(Locadora locadora);
	void deleteById(Long id);

	@Query("SELECT locadora FROM Locadora locadora WHERE locadora.cnpj = :cnpj")
	Locadora findByCNPJ(@Param ("cnpj") String cnpj);


	@Query("SELECT locadora FROM Locadora locadora WHERE locadora.email = :email")
	Locadora findByEmail(@Param ("email") String email);

	@Query("SELECT locadora FROM Locadora locadora WHERE locadora.cidade = :cidade")
    List<Locadora> findByCidade(@Param("cidade") String cidade);

	@Query("SELECT DISTINCT locadora.cidade FROM Locadora locadora")
    List<String> findDistinctCidades();

	/*
	@Query("SELECT codigo, nome, email, cidade FROM Usuario JOIN Locadora ON Usuario.codigo = Locadora.cnpj WHERE Usuario.papel = 'LOCADORA' AND cidade = ?")
	List<Locadora> findByCidade(@Param ("cidade")  String cidade);
	*/

	/*@Query("SELECT l FROM Locadora WHERE l.CNPJ = :CNPJ")
	Locadora findByEmail(@Param ("email") String email);*/

}