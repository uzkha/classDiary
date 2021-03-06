package br.com.classdiary.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.classdiary.model.Aluno;
import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Turma;
import br.com.classdiary.model.TurmaAluno;
import br.com.classdiary.model.TurmaDisciplina;

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

	public List<TurmaAluno> listarAlunos(Turma turma) {
		
		Criteria cr = getSession().createCriteria(TurmaAluno.class);
		cr.add(Restrictions.eq("turma", turma));
		List<TurmaAluno> turmaAlunos = cr.list();
		
		return turmaAlunos; 
	}

	public void salvarAluno(TurmaAluno turmaAluno) {
		super.getSession().saveOrUpdate(turmaAluno);
	}

	public void deletarAluno(TurmaAluno turmaAluno) {
		getSession().delete(turmaAluno);		
	}

	public void deletarAluno(List<TurmaAluno> listaTurmaAluno) {
		for (TurmaAluno turmaAluno : listaTurmaAluno){
			deletarAluno(turmaAluno);
		}
	}

	public List<TurmaAluno> listarAlunos(Turma turma, Aluno aluno) {
		Criteria cr = getSession().createCriteria(TurmaAluno.class);
		cr.add(Restrictions.eq("turma", turma));
		cr.add(Restrictions.eq("aluno", aluno));
		List<TurmaAluno> turmaAlunos = cr.list();
		
		return turmaAlunos;
		
	}

	public List<TurmaDisciplina> listarDisciplinas(Turma turma,	Disciplina disciplina) {
		Criteria cr = getSession().createCriteria(TurmaDisciplina.class);
		cr.add(Restrictions.eq("turma", turma));
		cr.add(Restrictions.eq("disciplina", disciplina));
		List<TurmaDisciplina> turmaDisciplinas = cr.list();
		
		return turmaDisciplinas;
	}

	public void deletarDisciplina(List<TurmaDisciplina> turmaDisciplinas) {
		for (TurmaDisciplina turmaDisciplina : turmaDisciplinas){
			deletarDisciplina(turmaDisciplina);
		}		
	}

	public void deletarDisciplina(TurmaDisciplina turmaDisciplina) {
		getSession().delete(turmaDisciplina);			
	}

	public void salvarDisciplina(TurmaDisciplina turmaDisciplina) {
		super.getSession().saveOrUpdate(turmaDisciplina);
	}

	public List<TurmaDisciplina> listarDisciplinas(Turma turma) {
		Criteria cr = getSession().createCriteria(TurmaDisciplina.class);
		cr.add(Restrictions.eq("turma", turma));
		List<TurmaDisciplina> turmaDisciplinas = cr.list();
		
		return turmaDisciplinas; 
	}

	public TurmaDisciplina findByTurmaDisciplina(Long id) {
		TurmaDisciplina turmaDisciplina = (TurmaDisciplina)getSession().load(TurmaDisciplina.class, id);
		return turmaDisciplina;
	}
	
	public List<Turma> findByName(String nome) {
		Criteria cr = getSession().createCriteria(Turma.class);
		cr.add(Restrictions.like("nome", nome));
		List<Turma> listaTurmas = cr.list();
		
		return listaTurmas; 
	}

}
