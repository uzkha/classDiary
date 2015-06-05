package br.com.classdiary.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.classdiary.dao.TurmaDao;
import br.com.classdiary.model.Aluno;
import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Turma;
import br.com.classdiary.model.TurmaAluno;
import br.com.classdiary.model.TurmaDisciplina;


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

	@Override
	public List<TurmaAluno> listarAlunos(Turma turma) {
		return turmaDao.listarAlunos(turma);
	}

	@Override
	public void salvarAluno(TurmaAluno turmaAluno) {
		turmaDao.salvarAluno(turmaAluno);	
	}

	@Override
	public void deletarAluno(TurmaAluno turmaAluno) {
		turmaDao.deletarAluno(turmaAluno);		
	}

	@Override
	public void deletarAluno(List<TurmaAluno> listaTurmaAluno) {
		turmaDao.deletarAluno(listaTurmaAluno);		
	}

	@Override
	public TurmaAluno findByTurmaAluno(Turma turma, Aluno aluno) {
		List<TurmaAluno> listaAlunos =  turmaDao.listarAlunos(turma, aluno);
		
		TurmaAluno turmaAluno = new TurmaAluno();
		
		if(listaAlunos.size() != 1){
			throw new ServiceException("Ocorreu um erro ao buscar ao buscar o Aluno na Turma para fazer e exclusão.");
		}else{
			for (TurmaAluno turmaAlunoLista : listaAlunos){
				turmaAluno = turmaAlunoLista;
			}
		}
		
		return turmaAluno;
	}

	@Override
	public void salvarDisciplina(TurmaDisciplina turmaDisciplina) {
		turmaDao.salvarDisciplina(turmaDisciplina);			
	}

	@Override
	public void deletarDisciplina(TurmaDisciplina turmaDisciplina) {
		turmaDao.deletarDisciplina(turmaDisciplina);		
	}

	@Override
	public void deletarDisciplinaTurma(List<TurmaDisciplina> turmaDisciplinas){
		turmaDao.deletarDisciplina(turmaDisciplinas);		
	}

	@Override
	public TurmaDisciplina findByTurmaDisciplina(Turma turma, Disciplina disciplina) {
		List<TurmaDisciplina> listaDisciplinas =  turmaDao.listarDisciplinas(turma, disciplina);
		
		TurmaDisciplina turmaDisciplina = new TurmaDisciplina();
		
		if(listaDisciplinas.size() != 1){
			throw new ServiceException("Ocorreu um erro ao buscar ao buscar a Disciplina na Turma para fazer e exclusão.");
		}else{
			for (TurmaDisciplina turmaDisciplinaLista : listaDisciplinas){
				turmaDisciplina = turmaDisciplinaLista;
			}
		}
		
		return turmaDisciplina;
	}

	@Override
	public List<TurmaDisciplina> listarDisciplinas(Turma turma) {
		return turmaDao.listarDisciplinas(turma);
	}

	@Override
	public TurmaDisciplina findByTurmaDisciplina(Long id) {
		return turmaDao.findByTurmaDisciplina(id);
	}

}
