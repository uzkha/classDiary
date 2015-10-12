package br.com.classdiary.controllerapi;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.classdiary.model.Aluno;
import br.com.classdiary.model.Chamada;
import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Retorno;
import br.com.classdiary.model.Turma;
import br.com.classdiary.service.AlunoService;
import br.com.classdiary.service.ChamadaService;
import br.com.classdiary.service.DisciplinaService;
import br.com.classdiary.service.TurmaService;
import br.com.classdiary.util.Auxiliar;
import br.com.classdiary.util.Frequencia;


@Controller
@RequestMapping(value= "/api/aluno")
public class ApiAlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private ChamadaService chamadaService;
	
	
	@RequestMapping(value = "/validarMatricula", produces="application/json", method = RequestMethod.POST)
	@ResponseBody
	public Retorno salvar(Long matricula, String senha) {
		
		Retorno retorno = new Retorno();
		
		if(matricula == null || senha == null){
			
			retorno.setSucesso(false);
			retorno.setDescricao("Informe a matricula e senha.");
			
		}else{		
		
			Aluno aluno = alunoService.validarMatricula(matricula, Auxiliar.converterToMD5(senha));
		
			
			if(aluno.getId() == null){
				retorno.setSucesso(false);
				retorno.setDescricao("Matricula/Senha invalido(s)");
			}else{				
				retorno.setSucesso(true);
				retorno.setDescricao(aluno.getChave());
			}
		}
		
		return retorno;
		
	}
	
	
	@RequestMapping(value="/setarPresenca", produces="application/json", method = RequestMethod.POST)
	@ResponseBody	
	public Retorno setarPresenca(String chave, Long turmaId, Long disciplinaId) {
		
		Retorno retorno = new Retorno();	
		
		if(chave == null || turmaId == null || disciplinaId == null){
			retorno.setSucesso(false);
			retorno.setDescricao("Os parametros: chave, turmaId e disciplinaId precisam ser informados.");
		
		}else{
		
			
			Frequencia frequencia = Frequencia.Presenca;
			
			Turma turma = turmaService.findById(turmaId);		
			Disciplina disciplina = disciplinaService.findById(disciplinaId);
			Aluno aluno = alunoService.findByChave(chave);
			
			int aulaId = 1;
			
			try {
				
				Chamada chamada = new Chamada();
				
				//verifica se ja existe registro para o aluno
				chamada = chamadaService.listarFrequencia(turma, disciplina, aulaId, aluno);
				
				//se existir recupera o id para o update
				if (chamada.getId() == null){				
					chamada.setAluno(aluno);
					chamada.setAula(aulaId);
					chamada.setDisciplina(disciplina);
					chamada.setFrequencia(frequencia);
					chamada.setTurma(turma);
				}else{
					chamada.setFrequencia(frequencia);
				}
				
				chamadaService.salvar(chamada);
				
				retorno.setSucesso(true);
				retorno.setDescricao("Presenca atualizada com sucesso!");
				
				
			} catch (Exception  e) {
				retorno.setSucesso(true);
				retorno.setDescricao("Ocorreu um erro, presenca não foi salva. <br>" + e.getMessage());
			}
		
		} // fim if
		
		return retorno;
		
	}

}
