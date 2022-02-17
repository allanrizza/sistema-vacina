package br.com.pumpkin.sistemavacina.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;


@SuppressWarnings("serial")
@Entity
@Table(name = "vacinados")
public class VacinadoModel implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Nome não pode ser nulo")
	private String nome;
	
	@Email(message = "Informe um e-mail válido.")
	private String email;
	
	@CPF(message = "Informe um CPF válido.")
	private String cpf;
	
	@NotNull(message = "Data de nascimento não pode ser nula")
	private LocalDate dataNascimento;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
}
