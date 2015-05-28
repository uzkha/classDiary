package br.com.classdiary.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Turma;

@Repository
public class TurmaDao extends AbstractClassSessionFactory{
	
	public Collection<Turma>listar() {
		
		List<Turma> turmas = getSession().createCriteria(Turma.class).list();
		
		return turmas;
	}

	public void salvar(Turma turma) {
		super.getSession().saveOrUpdate(turma);	
	}

	public Turma findById(Long id) {
		Turma turma = (Turma)getSession().load(Turma.class, id);
		return turma;
	}

	public void deletar(Long id) {
		Turma turma = new Turma();
		turma.setId(id);
		getSession().delete(turma);		
	}

	public List<Disciplina> buscaDisciplinas(Long id) {
		
		String hql = "from Turma_disciplina where turma_id = :id"; 
		Query query = getSession().createQuery(hql); 
		query.setParameter("id", id);
				
		List<Disciplina> disciplinas = query.list();		
		
		return disciplinas;
		
	}

}
