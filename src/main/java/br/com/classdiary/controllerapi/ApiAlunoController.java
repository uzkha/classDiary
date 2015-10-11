package br.com.classdiary.controllerapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.classdiary.model.Aluno;
import br.com.classdiary.model.Retorno;
import br.com.classdiary.service.AlunoService;
import br.com.classdiary.util.Auxiliar;


@Controller
@RequestMapping(value= "/api/aluno")
public class ApiAlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json", value="validarMatricula/{matricula}/{senha}")
	@ResponseBody
    public  Retorno validarMatricula(@PathVariable("matricula") Long matricula, @PathVariable("senha") String senha) {
		
		Aluno aluno = alunoService.validarMatricula(matricula, Auxiliar.converterToMD5(senha));
		Retorno retorno = new Retorno();
		
		if(aluno.getId() == null){
			retorno.setSucesso(false);
			retorno.setDescricao("Matricula/Senha invalido(s)");
		}else{				
			retorno.setSucesso(true);
			retorno.setDescricao(aluno.getChave());
		}
		
		return retorno;
		
    }

}
