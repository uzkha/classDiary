package br.com.classdiary.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.classdiary.model.Aluno;
import br.com.classdiary.model.Chamada;
import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Turma;
import br.com.classdiary.model.TurmaAluno;
import br.com.classdiary.util.Frequencia;

@Repository
public class ChamadaDao extends AbstractClassSessionFactory{
	
	
	public List<Chamada> listarFrequencia(Turma turma, Disciplina disciplina, int aula) {
		// TODO Auto-generated method stub
		Criteria cr = getSession().createCriteria(Chamada.class);
		cr.add(Restrictions.eq("turma", turma));
		cr.add(Restrictions.eq("disciplina", disciplina));
		cr.add(Restrictions.eq("aula", aula));
		List<Chamada> listaChamada = cr.list();
		
		return listaChamada; 
	}

	public List<Chamada> listarFrequencia(Turma turma, Disciplina disciplina, int aula, Aluno aluno) {
		// TODO Auto-generated method stub
		Criteria cr = getSession().createCriteria(Chamada.class);
		cr.add(Restrictions.eq("turma", turma));
		cr.add(Restrictions.eq("disciplina", disciplina));
		cr.add(Restrictions.eq("aula", aula));
		cr.add(Restrictions.eq("aluno", aluno));
		List<Chamada> listaChamada = cr.list();
		
		return listaChamada; 
	}
	
	public void salvar(Chamada chamada){
		super.getSession().saveOrUpdate(chamada);	
	}

}
