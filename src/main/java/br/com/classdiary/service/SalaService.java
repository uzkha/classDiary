package br.com.classdiary.service;

import java.util.Collection;

import br.com.classdiary.model.Sala;

public interface SalaService {
	
	public Collection<Sala> listar();
	
	public void salvar(Sala sala);
	
	public Sala findById(Long id);
	
	public void deletar(Long id);

}
