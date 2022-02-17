package br.com.pumpkin.sistemavacina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pumpkin.sistemavacina.models.VacinadoModel;
import br.com.pumpkin.sistemavacina.repositories.VacinadoRepository;

@Service
public class VacinadoService {
	@Autowired
	private VacinadoRepository vacinadoRepository;

	public void salvaVacinado(VacinadoModel vacinadoModel) {
		vacinadoRepository.save(vacinadoModel);
	}

	public boolean existeEmail(String email) {
		return vacinadoRepository.existsByEmail(email);
	}
	
	public boolean existeCpf(String cpf) {
		return vacinadoRepository.existsByCpf(cpf);
	}
	
	
}
