<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="container-large">
	<div class="panel panel-default">
		<div class="panel-heading">Alunos</div>
		<div class="panel-body">
			<div class="error">${message}</div>

			<a onclick="ajaxGet('/classdiary/aluno/adicionar')" href="javascript:;"><img
				src="${pageContext.request.contextPath}/resources/img/add.png"
				width="24" height="24" data-toggle="tooltip"
				title="Adicionar Agencia"></a>

			<table class="table table-striped table-hover">
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Telefone</th>
					<th>Email</th>
					<th>&nbsp</th>
					<th>&nbsp</th>
				</tr>
				<c:forEach var="aluno" items="${alunos}" varStatus="id">
					<tr>
						<td>${aluno.id}</td>
						<td>${aluno.nome}</td>						
						<td>${aluno.telefone}</td>
						<td>${aluno.email}</td>

						<td><a onclick="ajaxGet('/classdiary/aluno/editar/${aluno.id}')" href="javascript:;"><img
								src="${pageContext.request.contextPath}/resources/img/update.png"
								width="18" height="18" data-toggle="tooltip" title="Editar"></a>
						</td>

						<td><a href="javascript:;"
							onclick="deleteItem(event, '${aluno.nome}', '/classdiary/aluno/deletar/${aluno.id}')"><img
								src="${pageContext.request.contextPath}/resources/img/delete.png"
								height="18" data-toggle="tooltip" title="Excluir"></a></td>


					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>