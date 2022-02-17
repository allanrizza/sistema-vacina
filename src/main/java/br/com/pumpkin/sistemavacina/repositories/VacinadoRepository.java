package br.com.pumpkin.sistemavacina.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pumpkin.sistemavacina.models.VacinadoModel;

@Repository
public interface VacinadoRepository extends CrudRepository<VacinadoModel, Integer>{
	boolean existsByEmail(String email);
	boolean existsByCpf(String cpf);
}
