package br.com.pumpkin.sistemavacina.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pumpkin.sistemavacina.models.VacinaModel;

@Repository
public interface VacinaRepository extends CrudRepository<VacinaModel, Integer>{
    
}
