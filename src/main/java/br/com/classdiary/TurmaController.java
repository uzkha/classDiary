package br.com.classdiary;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.hibernate.mapping.AuxiliaryDatabaseObject;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.classdiary.model.Aluno;
import br.com.classdiary.model.Disciplina;
import br.com.classdiary.model.Professor;
import br.com.classdiary.model.Sala;
import br.com.classdiary.model.Turma;
import br.com.classdiary.model.TurmaAluno;
import br.com.classdiary.model.TurmaDisciplina;
import br.com.classdiary.service.AlunoService;
import br.com.classdiary.service.DisciplinaService;
import br.com.classdiary.service.ProfessorService;
import br.com.classdiary.service.SalaService;
import br.com.classdiary.service.TurmaService;
import br.com.classdiary.util.Auxiliar;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


@Controller
@RequestMapping(value= "/turma")
public class TurmaController {
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
    private ServletContext servletContext;
	
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
		modelView.addObject("disciplinas", turmaService.listarDisciplinas(turma));
		modelView.addObject("turmaId", id);
		modelView.setViewName("turma/listaDisciplinas");
		
		return modelView;		
		
	}
	
	
	@RequestMapping(value = "/disciplinaEditar/{id}/{disciplinaId}", method = RequestMethod.GET)
	public ModelAndView disciplinasEditar(Locale locale, Model model, @PathVariable("id") Long id, @PathVariable("disciplinaId") Long disciplinaId) {
		
		ModelAndView modelView = new ModelAndView();	
		
		Turma turma = turmaService.findById(id);
		Disciplina disciplina = disciplinaService.findById(disciplinaId);
		
		TurmaDisciplina turmaDisciplina = turmaService.findByTurmaDisciplina(turma, disciplina);
		
		modelView.addObject("disabled", "disabled");
		modelView.addObject("disciplinas", disciplinaService.listar());
		modelView.addObject("turmaId", id);
		modelView.addObject("professores", professorService.listar());
		modelView.addObject("salas", salaService.listar());
		modelView.addObject("turmaDisciplina", turmaDisciplina);
		modelView.addObject("data", Auxiliar.dateToString(turmaDisciplina.getDataInicio()));
		
		modelView.setViewName("turma/disciplinaEditar");
		
		return modelView;		
		
	}
		
	
	private void gerarQrCode(String turmaId, String disciplinaId) {
		
		
		
		int size = 480; // tamanho defaul qrcode	
		//String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0_Tomcat8080\\webapps\\classdiary\\resources\\qrcode\\"; 
		String path = "D:\\Apps\\workspace\\classdiary\\src\\main\\webapp\\resources\\qrcode"; //servletContext.getContextPath();
		
		String nomeArquivo = path + turmaId + "_" + disciplinaId + ".jpg";
		String qrCode = "turma=" + turmaId + ";disciplina=" + disciplinaId + ";";
		
		try {
		    
		    FileOutputStream f = new FileOutputStream(nomeArquivo);
		    ByteArrayOutputStream out = QRCode.from(qrCode)
		            .to(ImageType.JPG)
		            .withSize(size, size)
		            .stream();
		     
		    
		    f.write(out.toByteArray());
		    f.close();
		    
		    
		} catch (IOException ex) {
		    ex.printStackTrace();		   
		}
		
	}

	@RequestMapping(value = "/disciplinaAdicionar/{id}", method = RequestMethod.GET)
	public ModelAndView disciplinasAdicionar(Locale locale, Model model, @PathVariable("id") Long id) {
		
		ModelAndView modelView = new ModelAndView();	
		
		modelView.addObject("disciplinas", disciplinaService.listar());
		modelView.addObject("turmaId", id);
		modelView.addObject("professores", professorService.listar());
		modelView.addObject("salas", salaService.listar());
		modelView.setViewName("turma/disciplinaEditar");
		
		return modelView;		
		
	}
	
	
	@RequestMapping(value = "/disciplinaDeletar/{id}/{disciplinaId}", method = RequestMethod.GET)
	public ModelAndView disciplinasDeletar(Locale locale, Model model, @PathVariable("id") Long id, @PathVariable("disciplinaId") Long disciplinaId) {
		
		ModelAndView modelView = new ModelAndView();	
		
		Turma turma = turmaService.findById(id);
		Disciplina disciplina = disciplinaService.findById(disciplinaId);
		TurmaDisciplina turmaDisciplina = turmaService.findByTurmaDisciplina(turma, disciplina);
		
		
		try{
			turmaService.deletarDisciplina(turmaDisciplina);
			modelView.addObject("message", "Disciplina removida com sucesso!");
		}catch (ServiceException e){
			modelView.addObject("messageError", e.getMessage());
		}
				
		modelView.addObject("disciplinas", turmaService.listarDisciplinas(turma));		
		modelView.addObject("turmaId", id);
		modelView.setViewName("turma/listaDisciplinas");
		
		return modelView;		
		
	}
	
	
	@RequestMapping(value = "/salvarDisciplina", method = RequestMethod.POST)
	public ModelAndView salvarDisciplina(Locale locale, ModelMap model, Long turmaId, Long disciplinaId, Long professorId, Long salaId, String data, TurmaDisciplina turmaDisciplina) {
		
		ModelAndView modelView = new ModelAndView();
		Turma turma = turmaService.findById(turmaId);	
		
		try{				
			
			
			Disciplina disciplina = disciplinaService.findById(disciplinaId);
			Professor professor = professorService.findById(professorId);
			Sala sala = salaService.findById(salaId);
			
			turmaDisciplina.setDataInicio(Auxiliar.stringToDate(data));
			
			turmaDisciplina.setDisciplina(disciplina);	
			turmaDisciplina.setTurma(turma);
			turmaDisciplina.setProfessor(professor);
			turmaDisciplina.setSala(sala);
		
										 
			turmaService.salvarDisciplina(turmaDisciplina);
			
			gerarQrCode(turma.getId().toString(), disciplina.getId().toString());
				 
			modelView.addObject("disciplinas", turmaService.listarDisciplinas(turma));
			modelView.addObject("turmaId", turma.getId());
			modelView.addObject("message", "Cadastro alterado com sucesso!");
			modelView.setViewName("turma/listaDisciplinas");
			
			return modelView;		
			
		}catch (Exception e){
			
			
			modelView.addObject("turmaDisciplina", turmaDisciplina);
			modelView.addObject("disciplinas", disciplinaService.listar());
			modelView.addObject("professores", professorService.listar());
			modelView.addObject("salas", salaService.listar());
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
	
	
	@RequestMapping(value = "/alunosEditar/{id}", method = RequestMethod.GET)
	public ModelAndView alunosEditar(Locale locale, Model model, @PathVariable("id") Long id) {
		
		ModelAndView modelView = new ModelAndView();	
		
		Turma turma = turmaService.findById(id);
		
		modelView.addObject("alunosTurma", turmaService.listarAlunos(turma));
		modelView.addObject("alunos", alunoService.listar());
		modelView.addObject("turmaId", id);
		modelView.setViewName("turma/alunoEditar");
		
		return modelView;		
		
	}
	
	
	
	@RequestMapping(value = "/salvarAluno", method = RequestMethod.POST)
	public ModelAndView salvarAluno(Locale locale, ModelMap model, Long turmaId, Long[] alunos ) {
		
		ModelAndView modelView = new ModelAndView();
		
		try{				
				
			Turma turma = turmaService.findById(turmaId);
			
			//limpa a lista de disciplinas
			List<TurmaAluno> listaTurmaAluno = new ArrayList<TurmaAluno>();		
			listaTurmaAluno = turmaService.listarAlunos(turma);			
			turmaService.deletarAluno(listaTurmaAluno);
			
			//percorre os alunos selecionados para inserção
			for (int i = 0; i < alunos.length; i++) {

				Aluno aluno = alunoService.findById(alunos[i]);

				TurmaAluno turmaAluno = new TurmaAluno();
				turmaAluno.setAluno(aluno);
				turmaAluno.setTurma(turma);

				turmaService.salvarAluno(turmaAluno);

			}
	
			
			modelView.addObject("alunos", turmaService.listarAlunos(turma));
			modelView.addObject("turmaId", turma.getId());
			modelView.addObject("message", "Cadastro alterado com sucesso!");
			modelView.setViewName("turma/listaAlunos");
			
			return modelView;		
			
		}catch (ServiceException e){
			
			Turma turma = turmaService.findById(turmaId);
			modelView.addObject("alunosTurma", turmaService.listarAlunos(turma));
			modelView.addObject("alunos", alunoService.listar());
			modelView.addObject("turmaId", turma.getId());
			modelView.addObject("messageError", e.getMessage());
			modelView.setViewName("turma/alunoEditar");
			
			return modelView;		
		}		
	}
	
	
	@RequestMapping(value = "/alunoDeletar/{id}/{alunoId}", method = RequestMethod.GET)
	public ModelAndView alunoDeletar(Locale locale, Model model, @PathVariable("id") Long id, @PathVariable("alunoId") Long alunoId) {
		
		ModelAndView modelView = new ModelAndView();	
		
		Turma turma = turmaService.findById(id);
		Aluno aluno = alunoService.findById(alunoId);
		
		TurmaAluno turmaAluno = turmaService.findByTurmaAluno(turma, aluno);
		
		try{
			turmaService.deletarAluno(turmaAluno);
			modelView.addObject("message", "Aluno removido com sucesso!");
		}catch (ServiceException e){
			modelView.addObject("messageError", e.getMessage());
		}
				
		modelView.addObject("alunos", turmaService.listarAlunos(turma));		
		modelView.addObject("turmaId", id);
		modelView.setViewName("turma/listaAlunos");
		
		return modelView;		
		
	}
	
}
