package br.com.classdiary;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.classdiary.service.TurmaService;

@Controller
@RequestMapping(value= "/chamada")
public class ChamadaController {
	
	@Autowired
	private TurmaService turmaService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ModelAndView chamada(Locale locale, Model model) {
		
		ModelAndView modelView = new ModelAndView();
		
		modelView.addObject("turmas", turmaService.listar());	
		modelView.setViewName("chamada/listar");
		
		return modelView;		
		
	}
	
}
