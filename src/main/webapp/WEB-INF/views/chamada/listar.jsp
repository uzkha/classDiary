<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="container-large">
	<div class="panel panel-default">
		<div class="panel-heading">Chamada</div>
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
	
			<div id="container-large">
				<div class="panel panel-default col-sm-8">
					<div class="panel-heading">Seleção Turma/Disciplina/Aula</div>
					<div class="panel-body">						
						<div class="form-group">
							<label for="lbTurma" class="col-sm-2 control-label">Turma</label>
							<div class="col-sm-10">
								<select name="turmaId" id="turma" class="form-control" onchange="loadDisciplinas()">
									<option value="0">(Nenhum)</option> 		    
									<c:forEach var="turma" items="${turmas}" varStatus="id">
										<option value="${turma.id}">${turma.nome}</option>
									</c:forEach>
								</select>
							</div>
						</div>						
						<div class="form-group">
							<label for="lbDisciplina" class="col-sm-2 control-label">Disciplina</label>
							<div class="col-sm-10">
								<select name="disciplinaId" id="disciplina" class="form-control" onchange="loadAulas()">
									<option value="0">(Nenhum)</option>
									<c:forEach var="disciplina" items="${disciplinas}" varStatus="id">
										<option value="${disciplina.id}">${disciplina.nome}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="lbAula" class="col-sm-2 control-label">Aula</label>
							<div class="col-sm-10">
								<select name="aulaId" id=aula class="form-control">
									<option value="0">(Nenhum)</option>
									<c:forEach var="aula" items="${aulas}" varStatus="id">
										<option value="${aula.id}">${aula.nome}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
		
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
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	function loadDisciplinas(){	
		clearDisciplinas();
		clearAulas();
		var turma = $('#turma option:selected').index();
		if(turma > 0){ 			 
			//busca disciplinas da turma
			$.ajax({
				type: "GET",
				url: "/classdiary/chamada/turmadisciplinas/"+turma,
				data: null,
				success: function( data )
				{
					$.each(data, function(key){  
                        $('#disciplina').append($('<option></option>').val(data[key].id).html(data[key].nome));  
                    });  
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {		    
					
					var mensagem = XMLHttpRequest.responseText;		
					
					var naoFunction = function() {
						return;
					};		
					//mostra modal com erro
					showError(mensagem, naoFunction, 'Erro de Requisição');
					
				 }
			});

			
		}
	}
	function loadAulas(){	
		clearAulas();
		var disciplina = $('#disciplina option:selected').index();
		if(disciplina > 0){ 			 
			//busca disciplinas da turma
			$.ajax({
				type: "GET",
				url: "/classdiary/chamada/disciplinaaulas/"+disciplina,
				data: null,
				success: function( data )
				{
					$.each(data, function(key, val){ 		
                        $('#aula').append($('<option></option>').val(val).html(val));  
                    });  
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {		    
					
					var mensagem = XMLHttpRequest.responseText;		
					
					var naoFunction = function() {
						return;
					};		
					//mostra modal com erro
					showError(mensagem, naoFunction, 'Erro de Requisição');
					
				 }
			});

			
		}
	}

	function clearDisciplinas(){
		$('#disciplina').children().remove().end().append('<option selected value="0">(Nenhum)</option>') ;
	}
	function clearAulas(){
		$('#aula').children().remove().end().append('<option selected value="0">(Nenhum)</option>') ;
	}
</script>
