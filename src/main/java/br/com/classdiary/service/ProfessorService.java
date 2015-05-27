package br.com.classdiary.service;

import java.util.Collection;

import br.com.classdiary.model.Professor;

public interface ProfessorService {
	
	public Collection<Professor> listar();
	
	public void salvar(Professor professor);
	
	public Professor findById(Long id);
	
	public void deletar(Long id);

}
