package br.com.classdiary.service;

import java.util.List;

import br.com.classdiary.model.Aluno;
import br.com.classdiary.model.Chamada;
import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Turma;
import br.com.classdiary.util.Frequencia;


public interface ChamadaService {
	
	public List<Chamada> listarFrequencia(Turma turma, Disciplina disciplina, int aula);
	
	public Chamada listarFrequencia(Turma turma, Disciplina disciplina, int aula, Aluno aluno);
	
	public void salvar(Chamada chamada);
}
