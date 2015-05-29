<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="nav nav-tabs">
	<li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/')">Inicio</a></li>
	<li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/editar/${turmaId}')">Turma</a></li>	
	<li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/disciplinas/${turmaId}')">Disciplinas</a></li>
	<li role="presentation" class="active"><a href="#">Alunos</a></li> 

</ul>
<div id="container-large">
	<div class="panel panel-default">
		<div class="panel-heading">Alunos</div>
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

			<a onclick="ajaxGet('/classdiary/turma/alunosEditar/${turmaId}')" href="javascript:;"><img
				src="${pageContext.request.contextPath}/resources/img/update.png"
				width="24" height="24" data-toggle="tooltip"
				title="Editar"></a>

			<table class="table table-striped table-hover">
				<tr>
					<th>Id</th>
					<th>Aluno</th>							
					<th>&nbsp</th>	
				</tr>
				<c:forEach var="turmaaluno" items="${alunos}" varStatus="id">
					<tr>
						<td>${turmaaluno.aluno.id}</td>
						<td>${turmaaluno.aluno.nome}</td>										

						<td><a href="javascript:;"
							onclick="deleteItem(event, '${turmaaluno.aluno.nome}', '/classdiary/turma/alunoDeletar/${turmaId}/${turmaaluno.aluno.id}')"><img
								src="${pageContext.request.contextPath}/resources/img/delete.png"
								height="18" data-toggle="tooltip" title="Excluir"></a></td>

					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
