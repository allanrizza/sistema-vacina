package br.com.pumpkin.sistemavacina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pumpkin.sistemavacina.models.VacinaModel;
import br.com.pumpkin.sistemavacina.services.VacinaService;

@RestController
@RequestMapping(value = "/vacina")
public class VacinaController {

    @Autowired
    VacinaService vacinaService;

    @PostMapping
    public ResponseEntity<?> registraVacina(@RequestBody VacinaModel vacina) {
        vacinaService.salvaVacina(vacina);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
