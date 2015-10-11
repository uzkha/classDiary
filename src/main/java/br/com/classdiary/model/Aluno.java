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
	@GenericGenerator(name="alunoid" , strategy="increment")
	@GeneratedValue(generator="alunoid") 
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
	
	@Column
	@NotNull
	private Long matricula;
	
	@Column(length = 128)
	@NotNull
	private String senha;
	
	@Column(length = 128)
	@NotNull
	private String chave;

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

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	
	
}
