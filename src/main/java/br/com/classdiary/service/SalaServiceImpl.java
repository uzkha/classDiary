package br.com.classdiary.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.classdiary.dao.SalaDao;
import br.com.classdiary.model.Sala;


@Service
@Transactional
public class SalaServiceImpl implements SalaService{

	@Autowired
	private SalaDao salaDao;
	
	@Override
	public Collection<Sala> listar() {
		return salaDao.listar();
	}

	@Override
	public void salvar(Sala sala) {
		salaDao.salvar(sala);		
	}

	@Override
	public Sala findById(Long id) {
		return salaDao.findById(id);
	}

	@Override
	public void deletar(Long id) {
		salaDao.deletar(id);
	}	

}
