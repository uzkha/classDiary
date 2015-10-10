package br.com.classdiary.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.classdiary.model.Sala;

@Repository
public class SalaDao extends AbstractClassSessionFactory{
	
	public Collection<Sala>listar() {
		
		List<Sala> salas = getSession().createCriteria(Sala.class).list();
		
		return salas;
	}

	public void salvar(Sala sala) {
		super.getSession().saveOrUpdate(sala);	
	}

	public Sala findById(Long id) {
		Sala sala = (Sala)getSession().load(Sala.class, id);
		return sala;
	}

	public void deletar(Long id) {
		Sala sala = new Sala();
		sala.setId(id);
		getSession().delete(sala);		
	}

}
