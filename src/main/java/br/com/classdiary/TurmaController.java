package br.com.classdiary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javassist.bytecode.stackmap.BasicBlock.Catch;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Turma;
import br.com.classdiary.model.TurmaAluno;
import br.com.classdiary.service.DisciplinaService;
import br.com.classdiary.service.TurmaService;


@Controller
@RequestMapping(value= "/turma")
public class TurmaController {
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ModelAndView listar(Locale locale, Model model) {
		
		ModelAndView modelView = new ModelAndView();
		
		modelView.addObject("turmas", turmaService.listar());	
		modelView.setViewName("turma/listar");
		
		return modelView;		
		
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public ModelAndView adicionar(Locale locale, Model model) {
		
		ModelAndView modelView = new ModelAndView();	
		modelView.setViewName("turma/form");
		
		return modelView;		
		
	}
	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(Locale locale, ModelMap model, Turma turma) {
		
		ModelAndView modelView = new ModelAndView();
		
		try{		
			
			//recupera lista caso update
			if(turma.getId() != null){
				Turma turmaOld = turmaService.findById(turma.getId());				
				turmaOld.setNome(turma.getNome());
				turmaService.salvar(turmaOld);				
			}else{
				turmaService.salvar(turma);						
			}			
			
				
			modelView.addObject("turmas", turmaService.listar());
			modelView.addObject("message", "Cadastro efetuado/alterado com sucesso!");
			modelView.setViewName("turma/listar");
			
			return modelView;		
			
		}catch (ServiceException e){
			
			modelView.addObject("turma", turma);
			modelView.addObject("messageError", e.getMessage());
			modelView.setViewName("turma/form");
			return modelView;			
		}
		
		
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(Locale locale, Model model, @PathVariable("id") Long id) {
		
		ModelAndView modelView = new ModelAndView();	
		
		Turma turma = turmaService.findById(id);
		modelView.addObject("turma", turma);
		modelView.setViewName("turma/form");
		
		return modelView;		
		
	}
	
	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.GET)
	public ModelAndView deletar(Locale locale, Model model, @PathVariable("id") Long id) {
		
		ModelAndView modelView = new ModelAndView();	
		modelView.setViewName("turma/listar");
		
		try{
			
			turmaService.deletar(id);
			modelView.addObject("turmas", turmaService.listar());
			modelView.addObject("message", "Cadastro removido com sucesso!");
						
		
		}catch(ServiceException e){
			
			modelView.addObject("turmas", turmaService.listar());
			modelView.addObject("messageError", e.getMessage());
		
		}
		
		return modelView;		
		
		
	}

	
	@RequestMapping(value = "/disciplinas/{id}", method = RequestMethod.GET)
	public ModelAndView disciplinas(Locale locale, Model model, @PathVariable("id") Long id) {
		
		ModelAndView modelView = new ModelAndView();	
		
		Turma turma = turmaService.findById(id);
		modelView.addObject("disciplinas", turma.getDisciplinas());
		modelView.addObject("turmaId", id);
		modelView.setViewName("turma/listaDisciplinas");
		
		return modelView;		
		
	}
	
	
	@RequestMapping(value = "/disciplinasEditar/{id}", method = RequestMethod.GET)
	public ModelAndView disciplinasEditar(Locale locale, Model model, @PathVariable("id") Long id) {
		
		ModelAndView modelView = new ModelAndView();	
		
		Turma turma = turmaService.findById(id);
		modelView.addObject("disciplinasTurma", turma.getDisciplinas());
		modelView.addObject("disciplinas", disciplinaService.listar());
		modelView.addObject("turmaId", id);
		modelView.setViewName("turma/disciplinaEditar");
		
		return modelView;		
		
	}
	
	
	@RequestMapping(value = "/disciplinaDeletar/{id}/{disciplinaId}", method = RequestMethod.GET)
	public ModelAndView disciplinasDeletar(Locale locale, Model model, @PathVariable("id") Long id, @PathVariable("disciplinaId") Long disciplinaId) {
		
		ModelAndView modelView = new ModelAndView();	
		
		Turma turma = turmaService.findById(id);
		Disciplina disciplina = disciplinaService.findById(disciplinaId);
		
		//remove o item da lista
		turma.getDisciplinas().remove(disciplina);
		
		try{
			turmaService.salvar(turma);
			modelView.addObject("message", "Disciplina removida com sucesso!");
		}catch (ServiceException e){
			modelView.addObject("messageError", e.getMessage());
		}
				
		modelView.addObject("disciplinas", turma.getDisciplinas());		
		modelView.addObject("turmaId", id);
		modelView.setViewName("turma/listaDisciplinas");
		
		return modelView;		
		
	}
	
	
	@RequestMapping(value = "/salvarDisciplina", method = RequestMethod.POST)
	public ModelAndView salvarDisciplina(Locale locale, ModelMap model, Long turmaId, Long[] disciplinas ) {
		
		ModelAndView modelView = new ModelAndView();
		
		try{				
			
			ArrayList<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
			Turma turma = turmaService.findById(turmaId);
			
			//limpa a lista de disciplinas
			turma.getDisciplinas().clear();
			
			 for (int i = 0; i < disciplinas.length; i++) {
				 
				 Disciplina disciplina = disciplinaService.findById(disciplinas[i]);				 
				 listaDisciplina.add(disciplina);
			  }
			
			//seta a lista de disciplinas na turma
			turma.setDisciplinas(listaDisciplina);
			
			
			//salvar turma disciplina
			turmaService.salvar(turma);		
			
			modelView.addObject("disciplinas", turma.getDisciplinas());
			modelView.addObject("turmaId", turma.getId());
			modelView.addObject("message", "Cadastro alterado com sucesso!");
			modelView.setViewName("turma/listaDisciplinas");
			
			return modelView;		
			
		}catch (ServiceException e){
			
			Turma turma = turmaService.findById(turmaId);
			modelView.addObject("disciplinasTurma", turma.getDisciplinas());
			modelView.addObject("disciplinas", disciplinaService.listar());
			modelView.addObject("turmaId", turma.getId());
			modelView.addObject("messageError", e.getMessage());
			modelView.setViewName("turma/disciplinaEditar");
			
			return modelView;		
		}		
	}
	
	
	@RequestMapping(value = "/alunos/{id}", method = RequestMethod.GET)
	public ModelAndView alunos(Locale locale, Model model, @PathVariable("id") Long id) {
		
		ModelAndView modelView = new ModelAndView();	
		
		Turma turma = turmaService.findById(id);
		
		modelView.addObject("alunos", turmaService.listarAlunos(turma));
		modelView.addObject("turmaId", id);
		modelView.setViewName("turma/listaAlunos");
		
		return modelView;		
		
	}
}
