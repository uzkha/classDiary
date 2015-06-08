package br.com.classdiary.controllerapi;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import br.com.classdiary.model.Turma;
import br.com.classdiary.service.TurmaService;


@Controller
@RequestMapping(value= "/api/turma")
public class ApiTurmaController {
	
	@Autowired
	private TurmaService turmaService;
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json", value="getTurmas")
	@ResponseBody
    public  Collection<Turma> getTurmas() {
		return turmaService.listar();
    }
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json", value="getTurmasByName/{nome}")
	@ResponseBody
    public  List<Turma> getTurmasByName(@PathVariable("nome") String nome) {	
		nome = nome + "%";
		return turmaService.findByName(nome);		
    }
	

	
}
