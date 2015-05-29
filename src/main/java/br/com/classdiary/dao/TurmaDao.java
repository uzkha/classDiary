package br.com.classdiary.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Turma;
import br.com.classdiary.model.TurmaAluno;

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

	public Collection<TurmaAluno> listarAlunos(Turma turma) {
		
		Criteria cr = getSession().createCriteria(TurmaAluno.class);
		cr.add(Restrictions.eq("turma", turma));
		List<TurmaAluno> turmaAlunos = cr.list();
		
		return turmaAlunos; 
	}

}
