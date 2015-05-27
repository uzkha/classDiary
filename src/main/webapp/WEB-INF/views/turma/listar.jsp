<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

			<a onclick="ajaxGet('/classdiary/turma/adicionar')" href="javascript:;"><img
				src="${pageContext.request.contextPath}/resources/img/add.png"
				width="24" height="24" data-toggle="tooltip"
				title="Adicionar Turma"></a>

			<table class="table table-striped table-hover">
				<tr>
					<th>Id</th>
					<th>Nome</th>							
					<th>&nbsp</th>		
					<th>&nbsp</th>
					<th>&nbsp</th>
				</tr>
				<c:forEach var="turma" items="${turmas}" varStatus="id">
					<tr>
						<td>${turma.id}</td>
						<td>${turma.nome}</td>										
						
						<td><a onclick="ajaxGet('/classdiary/turma/disciplinas/${turma.id}')" href="javascript:;"><img
								src="${pageContext.request.contextPath}/resources/img/disciplina.png"
								width="18" height="18" data-toggle="tooltip" title="Disciplinas"></a>
						</td>
						
						<td><a onclick="ajaxGet('/classdiary/turma/editar/${turma.id}')" href="javascript:;"><img
								src="${pageContext.request.contextPath}/resources/img/update.png"
								width="18" height="18" data-toggle="tooltip" title="Editar"></a>
						</td>

						<td><a href="javascript:;"
							onclick="deleteItem(event, '${turma.nome}', '/classdiary/turma/deletar/${turma.id}')"><img
								src="${pageContext.request.contextPath}/resources/img/delete.png"
								height="18" data-toggle="tooltip" title="Excluir"></a></td>


					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
