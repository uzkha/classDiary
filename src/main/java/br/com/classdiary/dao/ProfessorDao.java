package br.com.classdiary.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.classdiary.model.Professor;

@Repository
public class ProfessorDao extends AbstractClassSessionFactory{
	
	public Collection<Professor>listar() {
		
		List<Professor> professors = getSession().createCriteria(Professor.class).list();
		
		return professors;
	}

	public void salvar(Professor professor) {
		super.getSession().saveOrUpdate(professor);	
	}

	public Professor findById(Long id) {
		Professor professor = (Professor)getSession().load(Professor.class, id);
		return professor;
	}

	public void deletar(Long id) {
		Professor professor = new Professor();
		professor.setId(id);
		getSession().delete(professor);		
	}

}
