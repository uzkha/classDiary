<script src="${pageContext.request.contextPath}/resources/js/validation.js" type="text/javascript"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="nav nav-tabs">
	<li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/')">Inicio</a></li>
	<li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/editar/${turmaId}')">Turma</a></li>	
	<li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/disciplinas/${turmaId}')">Disciplinas</a></li> 
    <li role="presentation" class="active"><a href="#">Alunos</a></li>
</ul>
<div id="container-large">
	<div class="panel panel-default">
		<div class="panel-heading">Manuteção Turma - Alunos</div>
		<div class="panel-body">
		<c:if test="${mensage != null}">
			<div id="result-form" class="alert alert-success" role="alert">
				${mensagem}
			</div>
		</c:if>
		<c:if test="${mensagemError != null}">
			<div id="result-form" class="alert alert-danger" role="alert">
				${mensagemErro}
			</div>
		</c:if>
			<form id="formTurmaAluno" class="form-horizontal" role="form" 
				action="/classdiary/turma/salvarDisciplina" method="post">	
				<select name="alunos" multiple>
					<c:forEach var="aluno" items="${alunos}" varStatus="id">
						<c:set var="selected" value="null"/>
						<c:forEach var="alunoTurma" items="${alunosTurma}" varStatus="id">
							<c:if test="${alunoTurma.aluno.id == aluno.id}">
								<c:set var="selected" value="selected"/>	
							</c:if>
						</c:forEach>											
						<option ${selected} value="${aluno.id}">${aluno.nome}</option>	
					</c:forEach>					
				</select>			
				<input type="hidden" name="turmaId" value="${turmaId}">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">						
						<a class="btn btn-default" onclick="ajaxGet('/classdiary/turma/alunos/${turmaId}')">Cancelar</a>
						<button type="submit" class="btn btn-default">Salvar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>