<script src="${pageContext.request.contextPath}/resources/js/validation.js" type="text/javascript"></script>
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
						<form id="formChamadaPesquisa" class="form-horizontal" role="form" 
						action="/classdiary/chamada/pesquisar" method="post">					
							<div class="form-group">
									<label for="lbTurma" class="col-sm-2 control-label">Turma</label>
									<div class="col-sm-10">
										<select name="turmaId" id="turma" class="form-control required" onchange="loadDisciplinas()">
											<option value="">(Nenhum)</option> 		    
											<c:forEach var="turma" items="${turmas}" varStatus="id">
												<c:set var="selected" value=""/>
												<c:if test="${turma.id == turmaId}">
													<c:set var="selected" value="selected"/>
												</c:if>									
												<option ${selected} value="${turma.id}">${turma.nome}</option>										
											</c:forEach>
										</select>
									</div>
								</div>						
								<div class="form-group">
									<label for="lbDisciplina" class="col-sm-2 control-label">Disciplina</label>
									<div class="col-sm-10">
										<select name="disciplinaId" id="disciplina" class="form-control required" onchange="loadAulas()">
											<option value="">(Nenhum)</option>
											<c:forEach var="disciplina" items="${disciplinas}" varStatus="id">
												<c:set var="selected" value=""/>
												<c:if test="${disciplina.id == disciplinaId}">
													<c:set var="selected" value="selected"/>
												</c:if>		
												<option ${selected} value="${disciplina.id}">${disciplina.nome}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="lbAula" class="col-sm-2 control-label">Aula</label>
									<div class="col-sm-10">
										<select name="aulaId" id=aula class="form-control required" onchange="pesquisar()">
											<option value="">(Nenhum)</option>
											<c:forEach var="aula" items="${aulas}" varStatus="id">
												<c:set var="selected" value=""/>
												<c:if test="${aula == aulaId}">
													<c:set var="selected" value="selected"/>
												</c:if>		
												<option ${selected} value="${aula}">${aula}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">									
									<button type="submit" class="btn btn-default">Pesquisar</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		
			<table id="tablePesquisa" class="table table-striped table-hover">
				<tr id="tr">
					<th>Aluno</th>
					<th>Frequencia</th>					
				</tr>
				<c:forEach var="turmaaluno" items="${alunos}" varStatus="id">
					<tr id="tr_${turmaaluno.aluno.id}">						
						<td>${turmaaluno.aluno.nome}</td>						
						<td>
						    <input type="radio" name="${turmaaluno.aluno.id}" onclick="setarFrequencia(this)" value="Presenca" >Presente
							<input type="radio" name="${turmaaluno.aluno.id}" onclick="setarFrequencia(this)" value="Falta" >Falto
							<input type="radio" name="${turmaaluno.aluno.id}" onclick="setarFrequencia(this)" value="Justificativa">Justifico
						</td>						
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	function setarFrequencia(evento){
		var frequencia = evento.value;
		var alunoId    = evento.name;

		alert($('#turma').val());

		dataPost = {};
		dataPost.idAluno = 1;
		dataPost.idTuram = 14;
		
		//busca disciplinas da turma
		$.ajax({
			type: "GET",
			url: "/classdiary/chamada/frequencia/"+alunoId+"/"+frequencia,
			data: null,
			success: function( data )
			{
				$( ".view_principal" ).html( data ); 
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
	

	function loadDisciplinas(){	
		clearDisciplinas();
		clearAulas();
		clearPesquisa();
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
		clearPesquisa();
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
	function pesquisar(){
		var aula = $('#aula option:selected').index();
		if(aula > 0){
			$( "#formChamadaPesquisa" ).submit();
		}else{
			clearPesquisa();
		}
	}
	
	function clearDisciplinas(){
		$('#disciplina').children().remove().end().append('<option selected value="">(Nenhum)</option>') ;
	}
	function clearAulas(){
		$('#aula').children().remove().end().append('<option selected value="">(Nenhum)</option>') ;
	}
	function clearPesquisa(){
		$('tr[id*="tr_"]').remove();
	}
</script>
