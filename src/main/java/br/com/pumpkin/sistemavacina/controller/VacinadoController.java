package br.com.pumpkin.sistemavacina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pumpkin.sistemavacina.models.VacinadoModel;
import br.com.pumpkin.sistemavacina.services.VacinadoService;

@RestController
public class VacinadoController {
	
	@Autowired
	private VacinadoService vacinadoService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/vacinado")
	public void registraVacinado(
			@RequestBody VacinadoModel vacinadoModel
			) {
		vacinadoService.salvaVacinado(vacinadoModel);
	}
}
