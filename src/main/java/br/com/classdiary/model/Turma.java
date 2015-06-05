package br.com.classdiary.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="turma")
public class Turma {

	@Id	
	@GenericGenerator(name="turmaid" , strategy="increment")
	@GeneratedValue(generator="turmaid") 
	private Long id;
	
	@Column(length = 100)
	@NotNull
	private String nome;
	
	/*@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="turma_disciplina", joinColumns={@JoinColumn(name="turma_id", referencedColumnName="id")}, inverseJoinColumns={@JoinColumn(name="disciplina_id", referencedColumnName="id")})
	private List<Disciplina> disciplinas;*/
	
		
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
