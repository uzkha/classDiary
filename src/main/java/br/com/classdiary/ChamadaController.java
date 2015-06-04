package br.com.classdiary;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

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
		//retorna a lista de disciplinas da turma
		return turma.getDisciplinas();		
		
	}
	
	
	@RequestMapping(value="/disciplinaaulas/{disciplinaId}", method = RequestMethod.GET)
	@ResponseBody	
	public int[] displinaAulas(Locale locale, Model model, @PathVariable("disciplinaId") Long disciplinaId) {
				
		//busca a turma
		Disciplina disciplina = disciplinaService.findById(disciplinaId);
		
		int[] aulas = montarAulas(disciplina);
		
		return aulas;
		
	}
	
	@RequestMapping(value = "/pesquisar", method = RequestMethod.POST)
	public ModelAndView pesquisar(Locale locale, ModelMap model, Long turmaId, Long disciplinaId, int aulaId) {
		
		ModelAndView modelView = new ModelAndView();
		
		//busca os alunos da turma
		Turma turma = turmaService.findById(turmaId);
		Disciplina disciplina = disciplinaService.findById(disciplinaId);

		
		int[] aulas = montarAulas(disciplina);
		
		modelView.addObject("turmaId", turmaId);
		modelView.addObject("disciplinaId", disciplinaId);
		modelView.addObject("aulaId", aulaId);
		modelView.addObject("alunos", turmaService.listarAlunos(turma));
		modelView.addObject("turmas", turmaService.listar());	
		modelView.addObject("disciplinas", turma.getDisciplinas());	
		modelView.addObject("aulas", aulas);
		modelView.addObject("frequencias", chamadaService.listarFrequencia(turma, disciplina, aulaId));	
		
		
		modelView.setViewName("chamada/listar");
		
		return modelView;		
	
		
	}
	
	@RequestMapping(value="/frequencia", method = RequestMethod.POST)
	@ResponseBody	
	public ModelAndView frequencia(Locale locale, Model model, Long alunoId, Frequencia frequencia, Long turmaId, Long disciplinaId, int aulaId) {
			
		ModelAndView modelView = new ModelAndView();
				
		Turma turma = turmaService.findById(turmaId);		
		Disciplina disciplina = disciplinaService.findById(disciplinaId);
		Aluno aluno = alunoService.findById(alunoId);
		
		try {
			
			Chamada chamada = new Chamada();
			chamada.setAluno(aluno);
			chamada.setAula(aulaId);
			chamada.setDisciplina(disciplina);
			chamada.setFrequencia(frequencia);
			chamada.setTurma(turma);
			
			chamadaService.salvar(chamada);
			
			modelView.addObject("message", "Frequencia atualizada com sucesso!");
			
		} catch (ServiceException e) {
			modelView.addObject("messageError", e.getMessage());
		}
		
		
		int[] aulas = montarAulas(disciplina);
		
		modelView.addObject("turmaId", turmaId);
		modelView.addObject("disciplinaId", disciplinaId);
		modelView.addObject("aulaId", aulaId);
		modelView.addObject("alunos", turmaService.listarAlunos(turma));
		modelView.addObject("turmas", turmaService.listar());	
		modelView.addObject("disciplinas", turma.getDisciplinas());	
		modelView.addObject("aulas", aulas);
		modelView.addObject("frequencias", chamadaService.listarFrequencia(turma, disciplina, aulaId));	
		
		
		modelView.setViewName("chamada/listar");
		
		return modelView;				
		
	}
	
	private int[] montarAulas(Disciplina disciplina){
		//retorna a lista de disciplinas da turma		
		int numeroAulas = disciplina.getNumeroAula();
		
		int[] aulas = new int[numeroAulas];
		
		for(int i=0; i<numeroAulas;i++){			
			aulas[i] = (i + 1); 
		}
		
		return aulas;
	}
	
}
