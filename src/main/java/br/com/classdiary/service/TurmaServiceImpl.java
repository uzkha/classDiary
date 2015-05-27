package br.com.classdiary.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.classdiary.dao.TurmaDao;
import br.com.classdiary.model.Turma;


@Service
@Transactional
public class TurmaServiceImpl implements TurmaService{

	@Autowired
	private TurmaDao turmaDao;
	
	@Override
	public Collection<Turma> listar() {
		return turmaDao.listar();
	}

	@Override
	public void salvar(Turma turma) {
		turmaDao.salvar(turma);		
	}

	@Override
	public Turma findById(Long id) {
		return turmaDao.findById(id);
	}

	@Override
	public void deletar(Long id) {
		turmaDao.deletar(id);
	}	

}
