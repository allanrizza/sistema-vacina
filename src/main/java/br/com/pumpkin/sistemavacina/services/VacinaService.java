package br.com.pumpkin.sistemavacina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pumpkin.sistemavacina.models.VacinaModel;
import br.com.pumpkin.sistemavacina.repositories.VacinaRepository;

@Service
public class VacinaService {
    @Autowired
    VacinaRepository vacinaRepository;

    public void salvaVacina(VacinaModel vacina) {
        vacinaRepository.save(vacina);
    }


}
