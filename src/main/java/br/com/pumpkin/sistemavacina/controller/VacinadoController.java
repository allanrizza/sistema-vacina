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
	public ResponseEntity<?> registraVacinado(@RequestBody VacinadoModel vacinadoModel) {
		
		try{
			validaExistencia(vacinadoModel);
			vacinadoService.salvaVacinado(vacinadoModel);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch(ConstraintViolationException exception) {
			List<String> listaException = new ArrayList<>();
			for(ConstraintViolation<?> campoInvalido : exception.getConstraintViolations()) {
				listaException.add(campoInvalido.getMessageTemplate());
			}
			return new ResponseEntity<>(listaException, HttpStatus.BAD_REQUEST);
		} catch(IllegalArgumentException exception) {
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

}
