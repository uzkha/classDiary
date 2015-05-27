package br.com.classdiary.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.classdiary.dao.ProfessorDao;
import br.com.classdiary.model.Professor;


@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService{

	@Autowired
	private ProfessorDao professorDao;
	
	@Override
	public Collection<Professor> listar() {
		return professorDao.listar();
	}

	@Override
	public void salvar(Professor professor) {
		professorDao.salvar(professor);		
	}

	@Override
	public Professor findById(Long id) {
		return professorDao.findById(id);
	}

	@Override
	public void deletar(Long id) {
		professorDao.deletar(id);
	}	

}
