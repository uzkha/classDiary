package br.com.classdiary;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Turma;
import br.com.classdiary.service.DisciplinaService;
import br.com.classdiary.service.TurmaService;

@Controller
@RequestMapping(value= "/chamada")
public class ChamadaController {
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
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
		
		//retorna a lista de disciplinas da turma		
		int numeroAulas = disciplina.getNumeroAula();
		
		int[] aulas = new int[numeroAulas];
		
		for(int i=0; i<numeroAulas;i++){			
			aulas[i] = (i + 1); 
		}
		
		return aulas;
		
	}
	
}
