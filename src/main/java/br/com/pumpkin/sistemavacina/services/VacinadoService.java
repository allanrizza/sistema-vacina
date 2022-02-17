package br.com.pumpkin.sistemavacina.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pumpkin.sistemavacina.models.VacinadoModel;
import br.com.pumpkin.sistemavacina.repositories.VacinadoRepository;

@Service
public class VacinadoService {
	@Autowired
	private VacinadoRepository vacinadoRepository;

	public void salvaVacinado(VacinadoModel vacinadoModel) throws Exception {
		validaExistencia(vacinadoModel);
		validaExistencia(vacinadoModel);
		verificaNumeroNome(vacinadoModel);
		verificaDataNascimento(vacinadoModel);
		
		vacinadoRepository.save(vacinadoModel);
	}

	public boolean existeEmail(String email) {
		return vacinadoRepository.existsByEmail(email);
	}

	public boolean existeCpf(String cpf) {
		return vacinadoRepository.existsByCpf(cpf);
	}

	public void validaExistencia(VacinadoModel modelo) {
		boolean existeVacinadoEmail = existeEmail(modelo.getEmail());
		boolean existeVacinadoCpf = existeCpf(modelo.getCpf());
		String tipoErro = "";

		if (existeVacinadoEmail) {
			tipoErro = "Já existe uma pessoa vacinada com este e-mail!";
		}

		if (existeVacinadoCpf) {
			tipoErro = "Já existe uma pessoa vacinada com este CPF!";
		}

		if (existeVacinadoEmail && existeVacinadoCpf) {
			tipoErro = "Já existe uma pessoa vacinada com este email e CPF";
		}

		if (existeVacinadoEmail || existeVacinadoCpf) {
			throw new IllegalArgumentException(tipoErro);
		}
	}

	public void verificaNumeroNome(VacinadoModel vacinadoModel) throws Exception {
		String nomeVacinado = vacinadoModel.getNome();
		for (int i = 0; i < nomeVacinado.length(); i++) {
			char space = ' ';
			if (!(Character.isLetter(nomeVacinado.charAt(i)) || Character.isSpace(nomeVacinado.charAt(i)))) {
				
				throw new IllegalArgumentException("O nome não pode conter um número.");
			}
		}
	}

	public void verificaDataNascimento(VacinadoModel vacinadoModel) throws Exception {
		LocalDate dataAtual = LocalDate.now();
		if (vacinadoModel.getDataNascimento().isAfter(dataAtual)) {
			throw new IllegalArgumentException("A data de nascimento é inválida.");
		}

	}

}
