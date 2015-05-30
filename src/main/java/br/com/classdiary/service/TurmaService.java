package br.com.classdiary.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.classdiary.model.Aluno;
import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Turma;
import br.com.classdiary.model.TurmaAluno;

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
}
