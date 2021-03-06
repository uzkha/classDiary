<script src="${pageContext.request.contextPath}/resources/js/validation.js" type="text/javascript"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="container-large">
	<div class="panel panel-default">
		<div class="panel-heading">Chamada</div>
		<div class="panel-body">
			<div id="retornoFrequencia"></div>
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
					<div class="panel-heading">Sele??o Turma/Disciplina/Aula</div>
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
											<c:forEach var="turmaDisciplina" items="${disciplinas}" varStatus="id">
												<c:set var="selected" value=""/>
												<c:if test="${turmaDisciplina.disciplina.id == disciplinaId}">
													<c:set var="selected" value="selected"/>
												</c:if>		
												<option ${selected} value="${turmaDisciplina.disciplina.id}">${turmaDisciplina.disciplina.nome}</option>
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
					<tr">						
						<td>${turmaaluno.aluno.nome}</td>						
						<td>
							
							<c:set var="presenca" value="null"/>
							<c:set var="falta" value="null"/>
							<c:set var="justificativa" value="null"/>
													
							<c:forEach var="frequencia" items="${frequencias}" varStatus="id">
								<c:if test="${frequencia.aluno.id == turmaaluno.aluno.id }">								
									<c:choose>
										<c:when test="${frequencia.frequencia == 'Presenca'}"><c:set var="presenca" value="checked"/></c:when>
										<c:when test="${frequencia.frequencia == 'Falta'}"><c:set var="falta" value="checked"/></c:when>
										<c:when test="${frequencia.frequencia == 'Justificativa'}"><c:set var="justificativa" value="checked"/></c:when>
									</c:choose>
								</c:if>
							</c:forEach>
						
						    <input ${presenca} type="radio" name="${turmaaluno.aluno.id}" onclick="setarFrequencia(this)" value="Presenca" >Presen?a
							<input ${falta} type="radio" name="${turmaaluno.aluno.id}" onclick="setarFrequencia(this)" value="Falta" >Falta
							<input ${justificativa} type="radio" name="${turmaaluno.aluno.id}" onclick="setarFrequencia(this)" value="Justificativa">Justificativa
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

		var turmaId = $('#turma').val();
		var disciplinaId = $('#disciplina').val();
		var aulaId = $('#aula').val();

		dataPost = {};
		dataPost.alunoId = alunoId;
		dataPost.turmaId = turmaId;
		dataPost.frequencia = frequencia;
		dataPost.disciplinaId = disciplinaId;
		dataPost.aulaId = aulaId;
		
		//busca disciplinas da turma
		$.ajax({
			type: "POST",
			url: "/classdiary/chamada/frequencia",
			data: dataPost,
			success: function( data )
			{
				//$( ".view_principal" ).html( data );
				var sucesso = data.search("sucesso");

				if(sucesso > 0){
					$( "#retornoFrequencia" ).removeClass( "alert alert-danger" );
					$( "#retornoFrequencia" ).addClass( "alert alert-success" );							
				}else{
					$( "#retornoFrequencia" ).removeClass( "alert alert-success" );
					$( "#retornoFrequencia" ).addClass( "alert alert-danger" );	
					evento.checked = null;
				}							
				
				$( "#retornoFrequencia" ).html( data ); 
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {		    
				
				var mensagem = XMLHttpRequest.responseText;		
				
				var naoFunction = function() {
					return;
				};		
				//mostra modal com erro
				showError(mensagem, naoFunction, 'Erro de Requisi??o');
				
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
					showError(mensagem, naoFunction, 'Erro de Requisi??o');
					
				 }
			});

			
		}
	}
	
	function loadAulas(){	
		clearAulas();
		clearPesquisa();
		var turma = $('#turma option:selected').index();
		var disciplina = $('#disciplina option:selected').index();
		if(disciplina > 0){ 			 
			//busca disciplinas da turma
			$.ajax({
				type: "GET",
				url: "/classdiary/chamada/disciplinaaulas/"+turma+"/"+disciplina,
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
					showError(mensagem, naoFunction, 'Erro de Requisi??o');
					
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
		//$('tr[id*="tr_"]').remove();
		$("#tablePesquisa td").remove();
	}
</script>
