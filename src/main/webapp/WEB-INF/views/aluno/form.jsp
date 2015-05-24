<script src="${pageContext.request.contextPath}/resources/js/validation.js" type="text/javascript"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="container-large">
	<div class="panel panel-default">
		<div class="panel-heading">Manuteção Aluno</div>
		<div class="panel-body">
			<div class="error">${message}</div>
			<form id="formAluno" class="form-horizontal" role="form" 
				action="/classdiary/aluno/salvar" method="post">
				<div class="form-group">
					<label for="lbnome" class="col-sm-2 control-label">Nome</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="nome"
							placeholder="insira o nome" name="nome"
							value="${aluno.nome}">
					</div>
				</div>
				<div class="form-group">
					<label for="lbnomemae" class="col-sm-2 control-label">Cidade</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="cidade"
						placeholder="insira a cidade"
							name="cidade" value="${aluno.cidade}">
					</div>
				</div>
				<input type="hidden" name="id" value="${aluno.id}">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">						
						<a class="btn btn-default" onclick="ajaxGet('/classdiary/aluno/')">Cancelar</a>
						<button type="submit" class="btn btn-default">Salvar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>