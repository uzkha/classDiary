package br.com.classdiary;

import java.util.Date;
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

import br.com.classdiary.model.Aluno;
import br.com.classdiary.service.AlunoService;
import br.com.classdiary.util.Auxiliar;


@Controller
@RequestMapping(value= "/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ModelAndView listar(Locale locale, Model model) {
		
		ModelAndView modelView = new ModelAndView();
		
		modelView.addObject("alunos", alunoService.listar());	
		modelView.setViewName("aluno/listar");
		
		return modelView;		
		
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public ModelAndView adicionar(Locale locale, Model model) {
		
		ModelAndView modelView = new ModelAndView();	
		modelView.setViewName("aluno/form");
		
		return modelView;		
		
	}
	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(Locale locale, ModelMap model, Aluno aluno) {
		
		ModelAndView modelView = new ModelAndView();
		
		try{				
			
			aluno.setSenha(Auxiliar.converterToMD5(aluno.getSenha()));
			aluno.setChave(Auxiliar.converterToMD5(aluno.getMatricula().toString()));
			
			alunoService.salvar(aluno);			
			modelView.addObject("alunos", alunoService.listar());
			modelView.addObject("message", "Cadastro efetuado/alterado com sucesso!");
			modelView.setViewName("aluno/listar");
			
			return modelView;		
			
		}catch (ServiceException e){
			
			modelView.addObject("aluno", aluno);
			modelView.addObject("messageError", e.getMessage());
			modelView.setViewName("aluno/form");
			return modelView;			
		}
		
		
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(Locale locale, Model model, @PathVariable("id") Long id) {
		
		ModelAndView modelView = new ModelAndView();	
		
		Aluno aluno = alunoService.findById(id);
		aluno.setSenha(null); //limpa conteudo MD5
		
		modelView.addObject("aluno", aluno);
		modelView.setViewName("aluno/form");
		
		return modelView;		
		
	}
	
	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.GET)
	public ModelAndView deletar(Locale locale, Model model, @PathVariable("id") Long id) {
		
		ModelAndView modelView = new ModelAndView();	
		modelView.setViewName("aluno/listar");
		
		try{
			
			alunoService.deletar(id);
			modelView.addObject("alunos", alunoService.listar());
			modelView.addObject("message", "Cadastro removido com sucesso!");
						
		
		}catch(ServiceException e){
			
			modelView.addObject("alunos", alunoService.listar());
			modelView.addObject("messageError", e.getMessage());
		
		}
		
		return modelView;		
		
		
	}

}
