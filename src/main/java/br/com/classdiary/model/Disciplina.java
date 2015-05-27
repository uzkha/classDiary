package br.com.classdiary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="disciplina")
public class Disciplina {

	@Id	
	@GenericGenerator(name="disciplinaid" , strategy="increment")
	@GeneratedValue(generator="disciplinaid") 
	private Long id;
	
	@Column(length = 100)
	@NotNull
	private String nome;
		
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
	
}
