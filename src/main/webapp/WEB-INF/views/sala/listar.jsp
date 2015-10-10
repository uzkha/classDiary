<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="container-large">
	<div class="panel panel-default">
		<div class="panel-heading">Salas</div>
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

			<a onclick="ajaxGet('/classdiary/sala/adicionar')" href="javascript:;"><img
				src="${pageContext.request.contextPath}/resources/img/add.png"
				width="24" height="24" data-toggle="tooltip"
				title="Adicionar Agencia"></a>

			<table class="table table-striped table-hover">
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Latitude</th>
					<th>Longitude</th>
					<th>&nbsp</th>
					<th>&nbsp</th>
				</tr>
				<c:forEach var="sala" items="${salas}" varStatus="id">
					<tr>
						<td>${sala.id}</td>
						<td>${sala.nome}</td>						
						<td>${sala.latitude}</td>
						<td>${sala.longitude}</td>

						<td><a onclick="ajaxGet('/classdiary/sala/editar/${sala.id}')" href="javascript:;"><img
								src="${pageContext.request.contextPath}/resources/img/update.png"
								width="18" height="18" data-toggle="tooltip" title="Editar"></a>
						</td>

						<td><a href="javascript:;"
							onclick="deleteItem(event, '${sala.nome}', '/classdiary/sala/deletar/${sala.id}')"><img
								src="${pageContext.request.contextPath}/resources/img/delete.png"
								height="18" data-toggle="tooltip" title="Excluir"></a></td>


					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>