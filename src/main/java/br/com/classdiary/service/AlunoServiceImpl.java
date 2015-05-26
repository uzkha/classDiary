package br.com.classdiary.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.classdiary.dao.AlunoDao;
import br.com.classdiary.model.Aluno;


@Service
@Transactional
public class AlunoServiceImpl implements AlunoService{

	@Autowired
	private AlunoDao alunoDao;
	
	@Override
	public Collection<Aluno> listar() {
		return alunoDao.listar();
	}

	@Override
	public void salvar(Aluno aluno) {
		alunoDao.salvar(aluno);		
	}
	
	

}
