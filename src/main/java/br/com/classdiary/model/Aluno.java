package br.com.classdiary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="aluno")
public class Aluno {

	@Id	
	@GenericGenerator(name="agenciaid" , strategy="increment")
	@GeneratedValue(generator="agenciaid") 
	private Long id;
	
	@Column(length = 100)
	@NotNull
	private String nome;
		
	@Column(length = 30)
	@NotNull
	private String telefone;
	
	@Column(length = 100)
	@NotNull
	private String endereco;
	
	@Column(length = 50)
	@NotNull
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
