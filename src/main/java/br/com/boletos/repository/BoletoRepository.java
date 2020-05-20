package br.com.boletos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.boletos.model.Boleto;

@Repository
public interface BoletoRepository extends CrudRepository<Boleto, Long>{

}
