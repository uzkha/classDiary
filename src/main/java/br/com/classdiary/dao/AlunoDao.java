package br.com.classdiary.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.classdiary.model.Aluno;

@Repository
public class AlunoDao extends AbstractClassSessionFactory{
	
	public Collection<Aluno>listar() {
		
		List<Aluno> alunos = getSession().createCriteria(Aluno.class).list();
		
		return alunos;
	}

	public void salvar(Aluno aluno) {
		super.getSession().saveOrUpdate(aluno);	
	}

	public Aluno findById(Long id) {
		Aluno aluno = (Aluno)getSession().load(Aluno.class, id);
		return aluno;
	}

	public void deletar(Long id) {
		Aluno aluno = new Aluno();
		aluno.setId(id);
		getSession().delete(aluno);		
	}

}
