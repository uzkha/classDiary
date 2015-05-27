package br.com.classdiary;

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
import br.com.classdiary.service.DisciplinaService;


@Controller
@RequestMapping(value= "/disciplina")
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ModelAndView listar(Locale locale, Model model) {
		
		ModelAndView modelView = new ModelAndView();
		
		modelView.addObject("disciplinas", disciplinaService.listar());	
		modelView.setViewName("disciplina/listar");
		
		return modelView;		
		
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public ModelAndView adicionar(Locale locale, Model model) {
		
		ModelAndView modelView = new ModelAndView();	
		modelView.setViewName("disciplina/form");
		
		return modelView;		
		
	}
	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(Locale locale, ModelMap model, Disciplina disciplina) {
		
		ModelAndView modelView = new ModelAndView();
		
		try{				
			
			disciplinaService.salvar(disciplina);			
			modelView.addObject("disciplinas", disciplinaService.listar());
			modelView.addObject("message", "Cadastro efetuado/alterado com sucesso!");
			modelView.setViewName("disciplina/listar");
			
			return modelView;		
			
		}catch (ServiceException e){
			
			modelView.addObject("disciplina", disciplina);
			modelView.addObject("messageError", e.getMessage());
			modelView.setViewName("disciplina/form");
			return modelView;			
		}
		
		
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(Locale locale, Model model, @PathVariable("id") Long id) {
		
		ModelAndView modelView = new ModelAndView();	
		
		Disciplina disciplina = disciplinaService.findById(id);
		modelView.addObject("disciplina", disciplina);
		modelView.setViewName("disciplina/form");
		
		return modelView;		
		
	}
	
	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.GET)
	public ModelAndView deletar(Locale locale, Model model, @PathVariable("id") Long id) {
		
		ModelAndView modelView = new ModelAndView();	
		modelView.setViewName("disciplina/listar");
		
		try{
			
			disciplinaService.deletar(id);
			modelView.addObject("disciplinas", disciplinaService.listar());
			modelView.addObject("message", "Cadastro removido com sucesso!");
						
		
		}catch(ServiceException e){
			
			modelView.addObject("disciplinas", disciplinaService.listar());
			modelView.addObject("messageError", e.getMessage());
		
		}
		
		return modelView;		
		
		
	}

}
