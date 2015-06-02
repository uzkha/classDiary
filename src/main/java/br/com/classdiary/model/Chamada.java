package br.com.classdiary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.com.classdiary.util.Frequencia;

@Entity
@Table(name="chamada")
public class Chamada {
	
	@Id	
	@GenericGenerator(name="chamdaid" , strategy="increment")
	@GeneratedValue(generator="chamdaid") 
	private Long id;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Aluno aluno;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Turma turma;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Disciplina disciplina;
	
	@Column
	@NotNull
	private int aula;
	
	@Column
	@NotNull
	private Frequencia frequencia;

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

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public int getAula() {
		return aula;
	}

	public void setAula(int aula) {
		this.aula = aula;
	}
	
}
