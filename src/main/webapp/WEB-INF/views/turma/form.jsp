<script src="${pageContext.request.contextPath}/resources/js/validation.js" type="text/javascript"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="nav nav-tabs">
  <li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/')">Inicio</a></li>
  <li role="presentation" class="active"><a href="#">Turma</a></li>
  <li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/disciplinas/${turma.id}')">Disciplinas</a></li>	 
</ul>

<div id="container-large">
	<div class="panel panel-default">
		<div class="panel-heading">Manuteção Turma</div>
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
			<form id="formTurma" class="form-horizontal" role="form" 
				action="/classdiary/turma/salvar" method="post">
				<div class="form-group">
					<label for="lbnome" class="col-sm-2 control-label">Nome</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="nome"
							placeholder="insira o nome" name="nome"
							value="${turma.nome}">
					</div>
				</div>											
				<input type="hidden" name="id" value="${turma.id}">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">						
						<a class="btn btn-default" onclick="ajaxGet('/classdiary/turma/')">Cancelar</a>
						<button type="submit" class="btn btn-default">Salvar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>