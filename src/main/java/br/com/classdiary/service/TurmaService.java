package br.com.classdiary.service;

import java.util.Collection;

import br.com.classdiary.model.Turma;

public interface TurmaService {
	
	public Collection<Turma> listar();
	
	public void salvar(Turma turma);
	
	public Turma findById(Long id);
	
	public void deletar(Long id);

}
