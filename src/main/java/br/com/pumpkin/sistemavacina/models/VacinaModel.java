package br.com.pumpkin.sistemavacina.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "vacinas")
public class VacinaModel implements Serializable {

    
    @Id
    @NotBlank
    private String nome_vacina;


    public String getNome_vacina() {
        return this.nome_vacina;
    }

    public void setNome_vacina(String nome_vacina) {
        this.nome_vacina = nome_vacina;
    }



    @Override
    public String toString() {
        return "{" +
            " nome_vacina='" + getNome_vacina() + "'" +
            "}";
    }


    


}
