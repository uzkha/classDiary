package br.com.classdiary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name="turma_aluno")
public class TurmaAluno {
	
	@Id	
	@GenericGenerator(name="turmaalunoid" , strategy="increment")
	@GeneratedValue(generator="turmaalunoid") 
	private Long id;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Aluno aluno;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Turma turma;
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Aluno getAluno() {
		return aluno;
	}


	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}


	public Turma getTurma() {
		return turma;
	}


	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}
