package br.com.classdiary;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.service.spi.ServiceException;
import org.omg.CORBA.Request;
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
import br.com.classdiary.model.Turma;
import br.com.classdiary.model.TurmaAluno;
import br.com.classdiary.model.TurmaDisciplina;
import br.com.classdiary.service.AlunoService;
import br.com.classdiary.service.ChamadaService;
import br.com.classdiary.service.DisciplinaService;
import br.com.classdiary.service.TurmaService;
import br.com.classdiary.util.Frequencia;

@Controller
@RequestMapping(value= "/chamada")
public class ChamadaController {
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private ChamadaService chamadaService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView chamada(Locale locale, Model model) {
		
		ModelAndView modelView = new ModelAndView();
		
		modelView.addObject("turmas", turmaService.listar());	
		modelView.setViewName("chamada/listar");
		
		return modelView;		
		
	}
	
	
	@RequestMapping(value="/turmadisciplinas/{turmaId}", method = RequestMethod.GET)
	@ResponseBody	
	public List<Disciplina> turmaDisciplinas(Locale locale, Model model, @PathVariable("turmaId") Long turmaId) {
				
		//busca a turma
		Turma turma = turmaService.findById(turmaId);
		

		List<TurmaDisciplina> listaTurmaDisciplinas = turmaService.listarDisciplinas(turma);
		List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		
		//retorna a lista de disciplinas da turma, para faciliar o controle na view
		for(TurmaDisciplina turmaDisciplina : listaTurmaDisciplinas){
			Disciplina disciplina = turmaDisciplina.getDisciplina();
			listaDisciplinas.add(disciplina);
		}
		
		return listaDisciplinas;
		
	}
	
	
	@RequestMapping(value="/disciplinaaulas/{turmaId}/{disciplinaId}", method = RequestMethod.GET)
	@ResponseBody	
	public int[] displinaAulas(Locale locale, Model model, @PathVariable("turmaId") Long turmaId, @PathVariable("disciplinaId") Long disciplinaId) {
		
		Turma turma = turmaService.findById(turmaId);
		Disciplina disciplina = disciplinaService.findById(disciplinaId);
		
		//busca a turma
		TurmaDisciplina turmaDisciplina = turmaService.findByTurmaDisciplina(turma, disciplina);
		
		int[] aulas = montarAulas(turmaDisciplina);
		
		return aulas;
		
	}
	
	@RequestMapping(value = "/pesquisar", method = RequestMethod.POST)
	public ModelAndView pesquisar(Locale locale, ModelMap model, Long turmaId, Long disciplinaId, int aulaId) {
		
		ModelAndView modelView = new ModelAndView();
		
		//busca os alunos da turma
		Turma turma = turmaService.findById(turmaId);
		Disciplina disciplina = disciplinaService.findById(disciplinaId);
		
		TurmaDisciplina turmaDisciplina = turmaService.findByTurmaDisciplina(turma, disciplina);
		
		int[] aulas = montarAulas(turmaDisciplina);
		
		modelView.addObject("turmaId", turmaId);
		modelView.addObject("disciplinaId", disciplinaId);
		modelView.addObject("aulaId", aulaId);
		modelView.addObject("alunos", turmaService.listarAlunos(turma));
		modelView.addObject("turmas", turmaService.listar());	
		modelView.addObject("disciplinas", turmaService.listarDisciplinas(turma));	
		modelView.addObject("aulas", aulas);
		modelView.addObject("frequencias", chamadaService.listarFrequencia(turma, disciplina, aulaId));	
		
		
		modelView.setViewName("chamada/listar");
		
		return modelView;		
	
		
	}
	
	@RequestMapping(value="/frequencia", method = RequestMethod.POST)
	@ResponseBody	
	public String frequencia(Locale locale, Model model, Long alunoId, Frequencia frequencia, Long turmaId, Long disciplinaId, int aulaId) {
			
		//ModelAndView modelView = new ModelAndView();
		String message;	
		
		Turma turma = turmaService.findById(turmaId);		
		Disciplina disciplina = disciplinaService.findById(disciplinaId);
		Aluno aluno = alunoService.findById(alunoId);
		
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
			
			//modelView.addObject("message", "Frequencia atualizada com sucesso!");
			message = "Frequencia atualizada com sucesso para aluno: " + aluno.getNome();
			
		} catch (Exception  e) {
			//modelView.addObject("messageError", e.getMessage());
			message = "Ocorreu um erro, frequencia não foi salva. <br>" + e.getMessage();
		}
		
		
		//int[] aulas = montarAulas(disciplina);
		
		//modelView.addObject("turmaId", turmaId);
		//modelView.addObject("disciplinaId", disciplinaId);
		//modelView.addObject("aulaId", aulaId);
		//modelView.addObject("alunos", turmaService.listarAlunos(turma));
		//modelView.addObject("turmas", turmaService.listar());	
		//modelView.addObject("disciplinas", turma.getDisciplinas());	
		//modelView.addObject("aulas", aulas);
		//modelView.addObject("frequencias", chamadaService.listarFrequencia(turma, disciplina, aulaId));	
		
		
		//modelView.setViewName("chamada/listar");
		
		return message;				
		
	}
	
	private int[] montarAulas(TurmaDisciplina turmaDisciplina){
		//retorna a lista de disciplinas da turma		
		int numeroAulas = turmaDisciplina.getNumeroAulas();
		
		int[] aulas = new int[numeroAulas];
		
		for(int i=0; i<numeroAulas;i++){			
			aulas[i] = (i + 1); 
		}
		
		return aulas;
	}
	
}
