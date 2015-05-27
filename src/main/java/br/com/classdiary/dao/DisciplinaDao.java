package br.com.classdiary.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.classdiary.model.Disciplina;

@Repository
public class DisciplinaDao extends AbstractClassSessionFactory{
	
	public Collection<Disciplina>listar() {
		
		List<Disciplina> disciplinas = getSession().createCriteria(Disciplina.class).list();
		
		return disciplinas;
	}

	public void salvar(Disciplina disciplina) {
		super.getSession().saveOrUpdate(disciplina);	
	}

	public Disciplina findById(Long id) {
		Disciplina disciplina = (Disciplina)getSession().load(Disciplina.class, id);
		return disciplina;
	}

	public void deletar(Long id) {
		Disciplina disciplina = new Disciplina();
		disciplina.setId(id);
		getSession().delete(disciplina);		
	}

}
