package br.com.classdiary.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.classdiary.model.Aluno;
import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Turma;
import br.com.classdiary.model.TurmaAluno;
import br.com.classdiary.model.TurmaDisciplina;

public interface TurmaService {
	
	public Collection<Turma> listar();
	
	public void salvar(Turma turma);
	
	public Turma findById(Long id);
	
	public void deletar(Long id);
	
	public List<TurmaAluno> listarAlunos(Turma turma);
	
	public void salvarAluno(TurmaAluno turmaAluno);
	
	public void deletarAluno(TurmaAluno turmaAluno);
	
	public void deletarAluno(List<TurmaAluno> listaTurmaAluno);
	
	public TurmaAluno findByTurmaAluno(Turma turma, Aluno aluno);
	
	public void salvarDisciplina(TurmaDisciplina turmaDisciplina);
	
	public void deletarDisciplina(TurmaDisciplina turmaDisciplina);
	
	public void deletarDisciplinaTurma(List<TurmaDisciplina> listaTurmaDisciplinas);
	
	public TurmaDisciplina findByTurmaDisciplina(Turma turma, Disciplina disciplina);
	
	public List<TurmaDisciplina> listarDisciplinas(Turma turma);
	
	public TurmaDisciplina findByTurmaDisciplina(Long id);
	
	public List<Turma> findByName(String nome);
}
