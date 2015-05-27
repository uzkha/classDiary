package br.com.classdiary.service;

import java.util.Collection;

import br.com.classdiary.model.Aluno;

public interface AlunoService {
	
	public Collection<Aluno> listar();
	
	public void salvar(Aluno aluno);
	
	public Aluno findById(Long id);
	
	public void deletar(Long id);

}
