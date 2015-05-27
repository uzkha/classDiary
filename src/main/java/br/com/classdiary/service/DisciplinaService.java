package br.com.classdiary.service;

import java.util.Collection;

import br.com.classdiary.model.Disciplina;

public interface DisciplinaService {
	
	public Collection<Disciplina> listar();
	
	public void salvar(Disciplina disciplina);
	
	public Disciplina findById(Long id);
	
	public void deletar(Long id);

}
