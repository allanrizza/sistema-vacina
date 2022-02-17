package br.com.pumpkin.sistemavacina.controller;

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

		try {
			vacinadoService.salvaVacinado(vacinadoModel);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}

		// Pega erro em caso de Email e CPF inválido.
		catch (ConstraintViolationException exception) {
			List<String> listaException = new ArrayList<>();
			for (ConstraintViolation<?> campoInvalido : exception.getConstraintViolations()) {
				listaException.add(campoInvalido.getMessageTemplate());
			}
			return new ResponseEntity<>(listaException, HttpStatus.BAD_REQUEST);
		}

		// Pega erro nos casos de: 1) Já existir alguém no BD com o email ou com o cpf
		// informado; 2) Houver algum número no nome informado; 3) A data de nascimento for inválida.
		catch (IllegalArgumentException exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
