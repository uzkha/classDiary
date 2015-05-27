package br.com.classdiary.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.classdiary.dao.DisciplinaDao;
import br.com.classdiary.model.Disciplina;


@Service
@Transactional
public class DisciplinaServiceImpl implements DisciplinaService{

	@Autowired
	private DisciplinaDao disciplinaDao;
	
	@Override
	public Collection<Disciplina> listar() {
		return disciplinaDao.listar();
	}

	@Override
	public void salvar(Disciplina disciplina) {
		disciplinaDao.salvar(disciplina);		
	}

	@Override
	public Disciplina findById(Long id) {
		return disciplinaDao.findById(id);
	}

	@Override
	public void deletar(Long id) {
		disciplinaDao.deletar(id);
	}	

}
