<script src="${pageContext.request.contextPath}/resources/js/validation.js" type="text/javascript"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="container-large">
	<div class="panel panel-default">
		<div class="panel-heading">Manuteção Sala</div>
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
			<form id="formSala" class="form-horizontal" role="form" 
				action="/classdiary/sala/salvar" method="post">
				<div class="form-group">
					<label for="lbnome" class="col-sm-2 control-label">Nome</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="nome"
							placeholder="insira o nome" name="nome"
							value="${sala.nome}">
					</div>
				</div>
				<div class="form-group">
					<label for="lblatitude" class="col-sm-2 control-label">Latitude</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="latitude"
						placeholder="insira o latitude"
							name="latitude" value="${sala.latitude}">
					</div>
				</div>
				<div class="form-group">
					<label for="lbendlongitude" class="col-sm-2 control-label">Longitude</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="longitude"
						placeholder="insira o longitude"
							name="longitude" value="${sala.longitude}">
					</div>
				</div>				
				<input type="hidden" name="id" value="${sala.id}">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">						
						<a class="btn btn-default" onclick="ajaxGet('/classdiary/sala/')">Cancelar</a>
						<button type="submit" class="btn btn-default">Salvar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>