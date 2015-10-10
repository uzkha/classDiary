package br.com.classdiary;

import java.util.Locale;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.classdiary.model.Sala;
import br.com.classdiary.service.SalaService;


@Controller
@RequestMapping(value= "/sala")
public class SalaController {
	
	@Autowired
	private SalaService salaService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ModelAndView listar(Locale locale, Model model) {
		
		ModelAndView modelView = new ModelAndView();
		
		modelView.addObject("salas", salaService.listar());	
		modelView.setViewName("sala/listar");
		
		return modelView;		
		
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public ModelAndView adicionar(Locale locale, Model model) {
		
		ModelAndView modelView = new ModelAndView();	
		modelView.setViewName("sala/form");
		
		return modelView;		
		
	}
	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(Locale locale, ModelMap model, Sala sala) {
		
		ModelAndView modelView = new ModelAndView();
		
		try{				
			
			salaService.salvar(sala);			
			modelView.addObject("salas", salaService.listar());
			modelView.addObject("message", "Cadastro efetuado/alterado com sucesso!");
			modelView.setViewName("sala/listar");
			
			return modelView;		
			
		}catch (ServiceException e){
			
			modelView.addObject("sala", sala);
			modelView.addObject("messageError", e.getMessage());
			modelView.setViewName("sala/form");
			return modelView;			
		}
		
		
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(Locale locale, Model model, @PathVariable("id") Long id) {
		
		ModelAndView modelView = new ModelAndView();	
		
		Sala sala = salaService.findById(id);
		modelView.addObject("sala", sala);
		modelView.setViewName("sala/form");
		
		return modelView;		
		
	}
	
	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.GET)
	public ModelAndView deletar(Locale locale, Model model, @PathVariable("id") Long id) {
		
		ModelAndView modelView = new ModelAndView();	
		modelView.setViewName("sala/listar");
		
		try{
			
			salaService.deletar(id);
			modelView.addObject("salas", salaService.listar());
			modelView.addObject("message", "Cadastro removido com sucesso!");
						
		
		}catch(ServiceException e){
			
			modelView.addObject("salas", salaService.listar());
			modelView.addObject("messageError", e.getMessage());
		
		}
		
		return modelView;		
		
		
	}

}
