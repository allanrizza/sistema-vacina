package br.com.pumpkin.sistemavacina.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pumpkin.sistemavacina.models.VacinadoModel;
import br.com.pumpkin.sistemavacina.services.VacinadoService;

@RestController
@Validated
public class VacinadoController {

	@Autowired
	private VacinadoService vacinadoService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/vacinado")
	public ResponseEntity<?> registraVacinado(@RequestBody VacinadoModel vacinadoModel) throws Exception {
		
		try{
			validaExistencia(vacinadoModel);
			verificaNumeroNome(vacinadoModel);
			verificaDataNascimento(vacinadoModel);
			vacinadoService.salvaVacinado(vacinadoModel);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		//Pega erro em caso de Email e CPF inválido
		catch(ConstraintViolationException exception) {
			List<String> listaException = new ArrayList<>();
			for(ConstraintViolation<?> campoInvalido : exception.getConstraintViolations()) {
				listaException.add(campoInvalido.getMessageTemplate());
			}
			return new ResponseEntity<>(listaException, HttpStatus.BAD_REQUEST);
		}
		//Pega erro nos casos de: 1) Já existir alguém no BD com o email ou com o cpf informado. 2) Houver algum número no nome informado
		catch(IllegalArgumentException exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	public void validaExistencia(VacinadoModel modelo) {
		boolean existeVacinadoEmail = vacinadoService.existeEmail(modelo.getEmail());
		boolean existeVacinadoCpf = vacinadoService.existeCpf(modelo.getCpf());
		String tipoErro = "";
		
		if(existeVacinadoEmail) {
			tipoErro = "Já existe uma pessoa vacinada com este e-mail!";
		}
		
		if(existeVacinadoCpf) {
			tipoErro = "Já existe uma pessoa vacinada com este CPF!";
		}
		
		if(existeVacinadoEmail && existeVacinadoCpf) {
			tipoErro= "Já existe uma pessoa vacinada com este email e CPF";
		}
		
		if(existeVacinadoEmail || existeVacinadoCpf) {
			throw new IllegalArgumentException(tipoErro);
		}
	}
	
	public void verificaNumeroNome(VacinadoModel vacinadoModel) throws Exception {
		String nomeVacinado = vacinadoModel.getNome();
		for(int i = 0; i < nomeVacinado.length(); i++) {
			if(!Character.isLetter(nomeVacinado.charAt(i))) {
				throw new IllegalArgumentException("O nome não pode conter um número");
			}
		}
	}
	
	public void verificaDataNascimento(VacinadoModel vacinadoModel) throws Exception {
		LocalDate dataAtual = LocalDate.now();
		LocalDate dataNascimento = vacinadoModel.getDataNascimento();
		
		int anoDataAtual = dataAtual.getYear();
		int mesDataAtual = dataAtual.getMonthValue();
		int diaDataAtual = dataAtual.getDayOfMonth();
		
		int anoDataNascimento = dataNascimento.getYear();
		int mesDataNascimento = dataNascimento.getMonthValue();
		int diaDataNascimento = dataNascimento.getDayOfMonth();
		
		boolean anoInvalido = (anoDataAtual < anoDataNascimento);
		boolean mesInvalido = (anoDataAtual == anoDataNascimento) && (mesDataAtual < mesDataNascimento);
		boolean diaInvalido = (anoDataAtual == anoDataNascimento) && (mesDataAtual == mesDataNascimento) && (diaDataNascimento > diaDataAtual);
		
		if(anoInvalido || mesInvalido || diaInvalido) {
			throw new IllegalArgumentException("Insira uma data de nascimento válida");
		}
		
	}

}
