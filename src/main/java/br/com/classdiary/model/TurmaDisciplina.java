package br.com.classdiary.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name="turma_disciplina")
public class TurmaDisciplina {
	
	@Id	
	@GenericGenerator(name="turmadisciplinaid" , strategy="increment")
	@GeneratedValue(generator="turmadisciplinaid") 
	private Long turmaDisciplinaId;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Disciplina disciplina;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Turma turma;
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Professor professor;	
	
	@ManyToOne
    @JoinColumn(nullable=false)
	private Sala sala;	
	
	@Column
	@NotNull
	private Date dataInicio;
	
	@Column
	@NotNull
	private int intervalo;
	
	@Column
	@NotNull
	private int numeroAulas;

	public Long getTurmaDisciplinaId() {
		return turmaDisciplinaId;
	}

	public void setTurmaDisciplinaId(Long turmaDisciplinaId) {
		this.turmaDisciplinaId = turmaDisciplinaId;
	}
	

	public Disciplina getDisciplina() {
		return disciplina;
	}


	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}


	public Turma getTurma() {
		return turma;
	}


	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public int getNumeroAulas() {
		return numeroAulas;
	}

	public void setNumeroAulas(int numeroAulas) {
		this.numeroAulas = numeroAulas;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public int getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}
	
}
