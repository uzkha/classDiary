<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Class Diary</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/shop-item.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/admin.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
   	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js" type="text/javascript"></script>
	
   	<!-- validation -->
	<script	src="${pageContext.request.contextPath}/resources/js/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/resources/js/jquery-validation/dist/localization/messages_pt_BR.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/validation.js" type="text/javascript"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
	
</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/classdiary">Class Diary</a>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <div class="col-md-3">                
                <div class="list-group">
                    <a href="#" class="list-group-item active">Diario de Classe</a>
                    <a href="#" class="list-group-item">Chamada</a>
                </div>
                <div class="list-group">
                    <a href="#" class="list-group-item active">Cadastros</a>
                    <a href="javascript:;" onclick="ajaxGet('/classdiary/aluno/')" class="list-group-item">Alunos</a>
                    <a href="javascript:;" onclick="ajaxGet('/classdiary/disciplina/')" class="list-group-item">Disciplinas</a>
                    <a href="javascript:;" onclick="ajaxGet('/classdiary/professor/')" class="list-group-item">Professor</a>
                    <a href="#" class="list-group-item">Turma</a>
                </div>
            </div>  
			
			<div class="col-md-9">			
				<div class="container-fluid principal">
					<!-- carrega a pagina enviada pelo controller -->
					<div class="view_principal"></div>
				</div>
			<!-- /.container-fluid -->
			</div>		

        </div>
		
    </div>
    <!-- /.container -->

    <div class="container">

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; WEB 3.0 Imed 2015</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script> -->
    
</body>


<div id="div-componente-sim-nao" class="modal fade">

	<div class="modal-dialog modal-sm">

		<div class="modal-content">

			<div class="modal-header">

				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>			
				<h4 id="h-componente-sim-nao-titulo" class="modal-title">TITULO</h4>

			</div>

			<div class="modal-body">

				<p id="p-componente-sim-nao-mensagem">MENSAGEM_EXCLUSAO</p>

			</div>

			<div class="modal-footer">

				<button id="btn-componente-sim-nao-nao" type="button"
					class="btn btn-default" data-dismiss="modal">Nao</button>

				<button id="btn-componente-sim-nao-sim" type="button"
					class="btn btn-primary">Sim</button>

			</div>

		</div>

	</div>

</div>

<div id="div-componente-falha-requisicao" class="modal fade">

	<div class="modal-dialog modal-sm">

		<div class="modal-content">

			<div class="modal-header">

				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>			
				<h4 id="h-componente-falha-requisicao-titulo" class="modal-title">Falha na Requisição</h4>

			</div>

			<div class="modal-body">

				<p id="p-componente-falha-requisicao-mensagem">MENSAGEM</p>

			</div>

			<div class="modal-footer">

				<button id="btn-componente-falha-requisicao-fechar" type="button"
					class="btn btn-default" data-dismiss="modal">Fechar</button>

			</div>

		</div>

	</div>

</div>

</html>

