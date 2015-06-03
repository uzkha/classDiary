package br.com.classdiary.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.classdiary.dao.ChamadaDao;
import br.com.classdiary.dao.TurmaDao;
import br.com.classdiary.model.Aluno;
import br.com.classdiary.model.Chamada;
import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Turma;
import br.com.classdiary.model.TurmaAluno;
import br.com.classdiary.util.Frequencia;


@Service
@Transactional
public class ChamadaServiceImpl implements ChamadaService{
	
	@Autowired
	ChamadaDao chamadaDao;
	
	@Override
	public List<Chamada> listarFrequencia(Turma turma,
			Disciplina disciplina, int aula) {
		return chamadaDao.listarFrequencia(turma, disciplina, aula);
	}

	@Override
	public void salvar(Chamada chamada) {
		chamadaDao.salvar(chamada);
	}

	@Override
	public Chamada listarFrequencia(Turma turma, Disciplina disciplina,
			int aula, Aluno aluno) {
		
		
		List<Chamada> listaChamadas =  chamadaDao.listarFrequencia(turma, disciplina, aula, aluno);
		
		Chamada chamada = new Chamada();
		
		if(listaChamadas.size() > 1){
			throw new ServiceException("Ocorreu um erro: Há mais de registro de chamada para este aluno neste aula.");
		}else{
			for (Chamada chamadaLista : listaChamadas){
				chamada = chamadaLista;
			}
		}
		
		return chamada;
		
	}

	

}
