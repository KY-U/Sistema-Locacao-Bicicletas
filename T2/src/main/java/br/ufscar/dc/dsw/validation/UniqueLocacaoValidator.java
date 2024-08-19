package br.ufscar.dc.dsw.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.ILocacaoDAO;
import br.ufscar.dc.dsw.domain.Locacao;

@Component
public class UniqueLocacaoValidator implements ConstraintValidator<UniqueLocacao, Locacao> {

    @Autowired
    private ILocacaoDAO dao;

    @Override
    public boolean isValid(Locacao locacao, ConstraintValidatorContext context) {
        if(dao != null && locacao != null) {
        	return dao.buscarLocacoesPorClienteELocadoraEData(locacao.getCliente().getId(), locacao.getLocadora().getId(), locacao.getData()).isEmpty();

        }
        
        return true;
    }
}