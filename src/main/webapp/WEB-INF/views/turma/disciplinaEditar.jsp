<script src="${pageContext.request.contextPath}/resources/js/validation.js" type="text/javascript"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="nav nav-tabs">
  <li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/')">Inicio</a></li>
  <li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/editar/${turmaId}')">Turma</a></li>	
   <li role="presentation" class="active"><a href="#">Disciplinas</a></li>
   <li role="presentation"><a href="javascript:;" onclick="ajaxGet('/classdiary/turma/alunos/${turmaId}')">Alunos</a></li> 
</ul>
<div id="container-large">
	<div class="panel panel-default">
		<div class="panel-heading">Manuteção Turma - Disciplinas</div>
		<div class="panel-body">
		<c:if test="${mensage != null}">
			<div id="result-form" class="alert alert-success" role="alert">
				${mensage}
			</div>
		</c:if>
		<c:if test="${mensageError != null}">
			<div id="result-form" class="alert alert-danger" role="alert">
				${mensageErro}
			</div>
		</c:if>
			<form id="formTurmaDisciplina" class="form-horizontal" role="form" 
				action="/classdiary/turma/salvarDisciplina" method="post">	
				
				<div class="form-group">
					<label for="lbDisciplina" class="col-sm-2 control-label">Disciplina</label>
					<div class="col-sm-4">
						<select ${disabled} name="disciplinaId" class="form-control">
							<option value="">(Nenhum)</option>
							<c:forEach var="disciplina" items="${disciplinas}" varStatus="id">
								<c:choose>
									<c:when test="${disciplina.id == turmaDisciplina.disciplina.id}">
										<option selected value="${disciplina.id}">${disciplina.nome}</option>
									</c:when>
									<c:otherwise>
										<option value="${disciplina.id}">${disciplina.nome}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="lbProfessor" class="col-sm-2 control-label">Professor</label>
					<div class="col-sm-4">
						<select  name="professorId" class="form-control">
							<option value="">(Nenhum)</option>
							<c:forEach var="professor" items="${professores}" varStatus="id">
								<c:choose>
									<c:when test="${professor.id == turmaDisciplina.professor.id}">
										<option selected value="${professor.id}">${professor.nome}</option>
									</c:when>
									<c:otherwise>
										<option value="${professor.id}">${professor.nome}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="lbaula" class="col-sm-2 control-label">Numero de Aulas</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="numeroAulas"
							placeholder="insira o número de aulas" name="numeroAulas"
							value="${turmaDisciplina.numeroAulas}">
					</div>
				</div>		
				<c:if test="${turmaDisciplina.turmaDisciplinaId > 0}">
					<input type="hidden" name="disciplinaId" id="disciplinaIdII" value="${turmaDisciplina.disciplina.id}">
				</c:if>
				<input type="hidden" name="turmaDisciplinaId" id="turmaDisciplinaId" value="${turmaDisciplina.turmaDisciplinaId}">				
				<input type="hidden" name="turmaId" id="turmaId" value="${turmaId}">
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">						
						<a class="btn btn-default" onclick="ajaxGet('/classdiary/turma/disciplinas/${turmaId}')">Cancelar</a>
						<button type="submit" class="btn btn-default">Salvar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>