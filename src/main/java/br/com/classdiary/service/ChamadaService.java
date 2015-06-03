package br.com.classdiary.service;

import java.util.List;

import br.com.classdiary.model.Aluno;
import br.com.classdiary.model.Chamada;
import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Turma;


public interface ChamadaService {
	
	public List<Chamada> listarFrequencia(Turma turma, Disciplina disciplina, int aula);

}
