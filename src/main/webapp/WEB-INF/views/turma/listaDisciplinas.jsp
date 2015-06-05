<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="nav nav-tabs">
	<li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/')">Inicio</a></li>
	<li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/editar/${turmaId}')">Turma</a></li>	
	<li role="presentation" class="active"><a href="#">Disciplinas</a></li> 
	<li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/alunos/${turmaId}')">Alunos</a></li>
</ul>
<div id="container-large">
	<div class="panel panel-default">
		<div class="panel-heading">Turmas</div>
		<div class="panel-body">
		<c:if test="${message != null}">
			<div id="result-form" class="alert alert-success" role="alert">
				${message}
			</div>
		</c:if>
		<c:if test="${messageError != null}">
			<div id="result-form" class="alert alert-danger" role="alert">
				${messageError}
			</div>
		</c:if>

			<a onclick="ajaxGet('/classdiary/turma/disciplinaAdicionar/${turmaId}')" href="javascript:;"><img
				src="${pageContext.request.contextPath}/resources/img/add.png"
				width="24" height="24" data-toggle="tooltip"
				title="Editar"></a>

			<table class="table table-striped table-hover">
				<tr>
					<th>Id</th>
					<th>Disciplina</th>		
					<th>Professor</th>
					<th>Aulas</th>									
					<th>&nbsp</th>	
				</tr>
				<c:forEach var="turmaDisciplina" items="${disciplinas}" varStatus="id">
					<tr>
						<td>${turmaDisciplina.disciplina.id}</td>
						<td>${turmaDisciplina.disciplina.nome}</td>	
						<td>${turmaDisciplina.professor.nome}</td>
						<td>${turmaDisciplina.numeroAulas}</td>										
						
						<td><a onclick="ajaxGet('/classdiary/turma/disciplinaEditar/${turmaId}/${turmaDisciplina.disciplina.id}')" href="javascript:;"><img
								src="${pageContext.request.contextPath}/resources/img/update.png"
								width="18" height="18" data-toggle="tooltip" title="Editar"></a>
						</td>
					
						<td><a href="javascript:;"
							onclick="deleteItem(event, '${turmaDisciplina.disciplina.nome}', '/classdiary/turma/disciplinaDeletar/${turmaId}/${turmaDisciplina.disciplina.id}')"><img
								src="${pageContext.request.contextPath}/resources/img/delete.png"
								height="18" data-toggle="tooltip" title="Excluir"></a></td>

					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
