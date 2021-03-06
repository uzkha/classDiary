<script src="${pageContext.request.contextPath}/resources/js/validation.js" type="text/javascript"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="container-large">
	<div class="panel panel-default">
		<div class="panel-heading">Manute??o Professor</div>
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
			<form id="formProfessor" class="form-horizontal" role="form" 
				action="/classdiary/professor/salvar" method="post">
				<div class="form-group">
					<label for="lbnome" class="col-sm-2 control-label">Nome</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="nome"
							placeholder="insira o nome" name="nome"
							value="${professor.nome}">
					</div>
				</div>
				<div class="form-group">
					<label for="lbemail" class="col-sm-2 control-label">Email</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="email"
						placeholder="insira o email"
							name="email" value="${professor.email}">
					</div>
				</div>
				<div class="form-group">
					<label for="lbenderenco" class="col-sm-2 control-label">Endere?o</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="endereco"
						placeholder="insira o endereco"
							name="endereco" value="${professor.endereco}">
					</div>
				</div>
				<div class="form-group">
					<label for="lbenderenco" class="col-sm-2 control-label">Telefone</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="telefone"
						placeholder="insira o telefone"
							name="telefone" value="${professor.telefone}">
					</div>
				</div>
				<input type="hidden" name="id" value="${professor.id}">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">						
						<a class="btn btn-default" onclick="ajaxGet('/classdiary/professor/')">Cancelar</a>
						<button type="submit" class="btn btn-default">Salvar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>