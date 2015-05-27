$(document).ready(function () {
    $("#formAluno").validate({
    	
    	 //envia o formulario via ajax
        submitHandler: function(form){
        	
			var dados = $( form ).serialize();      
			
			$.ajax({
				type: "POST",
				url: "/classdiary/aluno/salvar",
				data: dados,
				success: function( data )
				{
					$( ".view_principal" ).html( data );
				}
			});

			return false;
		},
    	
        // Define as regras
        rules: {
            "nome": {
                // campoNome será obrigatório (required) e terá tamanho mínimo (minLength)
                required: true, minlength: 3
           },
            "email": {
                // campoNome será obrigatório (required) e terá tamanho mínimo (minLength)
                required: true, email:true
            },
            "telefone": {
                // campoNome será obrigatório (required) e terá tamanho mínimo (minLength)
                required: true, 
            },
            "endereco": {
                // campoNome será obrigatório (required) e terá tamanho mínimo (minLength)
                required: true,
            },
         },
    });

});

$(document).ready(function () {
    $("#formProfessor").validate({
    	
    	 //envia o formulario via ajax
        submitHandler: function(form){
        	
			var dados = $( form ).serialize();      
			
			$.ajax({
				type: "POST",
				url: "/classdiary/professor/salvar",
				data: dados,
				success: function( data )
				{
					$( ".view_principal" ).html( data );
				}
			});

			return false;
		},
    	
        // Define as regras
        rules: {
            "nome": {
                // campoNome será obrigatório (required) e terá tamanho mínimo (minLength)
                required: true, minlength: 3
           },
            "email": {
                // campoNome será obrigatório (required) e terá tamanho mínimo (minLength)
                required: true, email:true
            },
            "telefone": {
                // campoNome será obrigatório (required) e terá tamanho mínimo (minLength)
                required: true, 
            },
            "endereco": {
                // campoNome será obrigatório (required) e terá tamanho mínimo (minLength)
                required: true,
            },
         },
    });

});

$(document).ready(function () {
    $("#formDisciplina").validate({
    	
    	 //envia o formulario via ajax
        submitHandler: function(form){
        	
			var dados = $( form ).serialize();      
			
			$.ajax({
				type: "POST",
				url: "/classdiary/disciplina/salvar",
				data: dados,
				success: function( data )
				{
					$( ".view_principal" ).html( data );
				}
			});

			return false;
		},
    	
        // Define as regras
        rules: {
            nome: {
                // campoNome será obrigatório (required) e terá tamanho mínimo (minLength)
                required: true, minlength: 3
            },                      
        },
    });

});



$(document).ready(function () {
    $("#formTurma").validate({
    	
    	 //envia o formulario via ajax
        submitHandler: function(form){
        	
			var dados = $( form ).serialize();      
			
			$.ajax({
				type: "POST",
				url: "/classdiary/turma/salvar",
				data: dados,
				success: function( data )
				{
					$( ".view_principal" ).html( data );
				}
			});

			return false;
		},
    	
        // Define as regras
        rules: {
            nome: {
                // campoNome será obrigatório (required) e terá tamanho mínimo (minLength)
                required: true, minlength: 3
            },                      
        },
    });

});

function show(titulo, mensagem, simFunction, naoFunction, simMensagem, naoMensagem) {
	
	$('#h-componente-sim-nao-titulo').html(titulo);
	$('#p-componente-sim-nao-mensagem').html(mensagem);
	if (simMensagem != null)
		$('#btn-componente-sim-nao-sim').html(simMensagem);
	if (naoMensagem != null)
		$('#btn-componente-sim-nao-nao').html(naoMensagem);
	
	
	simFunctionInternal = function() {		
		$('#div-componente-sim-nao').modal('toggle');
	};
	
	$('#btn-componente-sim-nao-sim').unbind('click');
	$('#btn-componente-sim-nao-sim').click(simFunctionInternal);
	
	if (simFunction != null)
		$('#btn-componente-sim-nao-sim').click(simFunction);
	
	$('#btn-componente-sim-nao-nao').unbind('click');
	if (simFunction != null)
		$('#btn-componente-sim-nao-nao').click(naoFunction);
	
	if (simMensagem != null)
		$('#btn-componente-sim-nao-sim').html(simMensagem);
	else
		$('#btn-componente-sim-nao-sim').html('sim');
	
	if (naoMensagem != null)
		$('#btn-componente-sim-nao-nao').html(naoMensagem);
	else
		$('#btn-componente-sim-nao-nao').html('nao');
	
	$('#div-componente-sim-nao').modal('toggle');
	
}

function showError(mensagem, naoFunction) {
	
	$('#p-componente-falha-requisicao-mensagem').html(mensagem);
	
	$('#btn-componente-falha-requisicao-nao').unbind('click');
	
	$('#btn-componente-falha-requisicao-nao').click(naoFunction);
			
	$('#div-componente-falha-requisicao').modal('toggle');
	
}


function confirmDelete(descricaoItem, simFunction, naoFunction) {
	titulo = 'Confirmar';
	mensagem = 'Excluir: ' + ' <span style="font-weight: bold;">' + descricaoItem +'</span>?';
	show(titulo, mensagem, simFunction, naoFunction);
}

function deleteItem(event, descricao, link){
	event.preventDefault();
		
	simFunction = function() {
		//window.location.href = link;
		ajaxGet(link);
	};
	
	naoFunction = function() {
		return;
	};
	
	confirmDelete(descricao, simFunction, naoFunction);
}

function ajaxGet(url){
		
	//ajax via get
	$.get(url)
	
	.fail(function(data) {
		//alert(data.responseText);
		
		if (data.status === 404){
			var mensagem ="<strong>Error 404</strong><br><br>" + "Página: <strong>" + url + "</strong> não encontrada.";
		}else{		
			var mensagem = data.responseText;
		}
		
		var naoFunction = function() {
			return;
		};		
		//mostra modal com erro
		showError(mensagem, naoFunction);
		
	}) // fim fail
	
	.done(function(data) {
		$( ".view_principal" ).html( data ); // carrega a view principal do sistema
	});

	
	//retorna false para nao prosseguir o envio do link via get padrao HTML
	return false;
	
}

$(document).ready(function() {
		//carregamento da aplicacao ou refresh
	//seta a home via ajax
	$(window).on('load', function(){
		var url = "/classdiary/home";
		$.get(url)
		.done(function(data) {
			$( ".view_principal" ).html( data );
		});	
	});
});
