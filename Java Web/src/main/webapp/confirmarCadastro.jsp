<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
    <!-- Bootstrap CSS v5.2.0-beta1 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
   
    <link rel="stylesheet" href="./style.css">
    <title>Confirmando Cadastro</title>
</head>
<body>
	<section class="container-fluid">
        <!-- CABEÇALHO DA PAGINA ----------------------------------------------------------------------------->
        <!-- barra de navegação e imagem de fundo -->
        <header class="container-fluid">
  
          <nav>
            <!-- """"logo"""" -->
            <a class="logo" href="./index.html">Viagens & Cinema</a>
  
            <!-- botão responsivo da navbar -->
            <buttom class="hamburger" id="hamburger" type="buttom">
              <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="white" class="bi bi-list" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
              </svg>
            </buttom>
  
            <!-- links -->
            <ul class="navHub" id="navHub">
              <li><a href="./index.html">HOME</a></li>
              <li><a href="./promocoes.html">PROMOÇÕES</a></li>
              <li><a href="./destino.html">DESTINOS</a></li>
              <li><a href="./contato.html">CONTATOS</a></li>
            </ul>
          </nav>
  
        </header>
     </section>
     
	
	
	<section class="container">
	<h1 class="titulo">Confirmar cadastro</h1>
	<form id="frmConfirmar" action="" method="GET">
	<div class="row">
		<h3 class="titulo">Seus dados</h3>
		
		<div class="mb-3">
  			<input type="text" class="form-control" name="idCliente" aria-describedby="helpId" value="${cliente.getId()}" readonly>
  			<small id="helpId" class="form-text text-muted">Seu Id</small>
		</div>
		<div class="col-sm">
			<div class="mb-3">
  				<input type="text" class="form-control habilitar" name="cpf" aria-describedby="helpId" value="${cliente.getCpf()}" disabled>
  				<small id="helpId" class="form-text text-muted">CPF</small>
			</div>
			<div class="mb-3">
  				<input type="text" class="form-control habilitar" name="origem" aria-describedby="helpId" value="${cliente.getOrigem()}" disabled>
  				<small id="helpId" class="form-text text-muted">Origem</small>
			</div>
		</div>
		
		<div class="col-sm">
		
			<div class="mb-3">
	  			<input type="date" class="form-control habilitar" name="dataVolta" aria-describedby="helpId" value="${cliente.getDataVolta()}" disabled>
  				<small id="helpId" class="form-text text-muted">Data de Volta</small>
			</div>
			<div class="mb-3">
  				<input type="date" class="form-control habilitar" name="dataIda" aria-describedby="helpId" value="${cliente.getDataIda()}" disabled>
  				<small id="helpId" class="form-text text-muted">Data de Ida</small>
			</div>
			
		</div>
		
	</div>
	
	<div class="row">
		<h3 class="titulo">Destino selecionado</h3>
		<div class="mb-3">
			<select class="form-select habilitar" name="idDestino" aria-label="Default select example" disabled>
  				<option selected value="${cliente.getDestino().getId()}"> ${cliente.getDestino().getId()} </option>
				<c:forEach items="${destinos}" var="destinos">
					 <option value="${destinos.getId()}"> ${destinos.getId()} -- ${destinos.getPais()} -- ${destinos.getCidade()}</option>
				</c:forEach>
  		
			</select>
  			<small id="helpId" class="form-text text-muted">Id do destino selecionado</small>
		</div>
		<div class="col-sm">
			<div class="mb-3">
  				<input type="text" class="form-control" name="pais" aria-describedby="helpId" value="${cliente.getDestino().getPais()}" readonly>
  				<small id="helpId" class="form-text text-muted">País</small>
			</div>
			<div class="mb-3">
  				<input type="text" class="form-control" name="obraR" aria-describedby="helpId" value="${cliente.getDestino().getObraR()}" readonly>
  				<small id="helpId" class="form-text text-muted">Obra Relacionada</small>
			</div>
			<div class="mb-3">
	  			<input type="text" class="form-control" name="cidade" aria-describedby="helpId" value="${cliente.getDestino().getCidade()}" readonly>
  				<small id="helpId" class="form-text text-muted">Cidade</small>
			</div>
		</div>
		
		<div class="col-sm">
			<c:set var="promocao" value="${cliente.getDestino().getPromo().getId()}"/>
     			<c:if test="${promocao == 0}">
      				<input value="Não há promoção" readonly>
   			</c:if>
   			<c:if test="${promocao != 0}">
   				<div class="mb-3">
  					<input type="text" class="form-control" name="promocao" aria-describedby="helpId" value="${promocao}" readonly>
  					<small id="helpId" class="form-text text-muted">Promoção</small>
				</div>
			</c:if>
		</div>
	</div>
	
	<div class="row">
	
		<h3 class="titulo">Companhia que será usada</h3>
		<div class="col-sm mb-3">
				<select class="form-select habilitar"  name="idVoo" aria-label="Default select example" disabled>
  					<option selected value="${cliente.getDestino().getVoo().getId()}"> ${cliente.getDestino().getVoo().getId()} </option>
					<c:forEach items="${voos}" var="voos">
					 <option value="${voos.getId()}"> ${voos.getId()} -- ${voos.getCompanhia()} -- ${voos.getPrecoVoo()}</option>
				</c:forEach>
  		
			</select>
  			<small id="helpId" class="form-text text-muted">Id da companhia selecionada</small>
		</div>
		<div class="col-sm mb-3">
  			<input type="text" class="form-control" name="companhia" aria-describedby="helpId" value="${cliente.getDestino().getVoo().getCompanhia()}" readonly>
  			<small id="helpId" class="form-text text-muted">Companhia Aérea</small>
		</div>
		<div class="col-sm mb-3">
  			<input type="text" class="form-control" name="preco" aria-describedby="helpId" value="${cliente.getDestino().getVoo().getPrecoVoo()}" readonly>
  			<small id="helpId" class="form-text text-muted">Preço</small>
		</div>
		
	</div>
	<button type="button" class="btn btn-primary" onClick="edicao()">Editar</button>
	<input id="confirmar" class="btn btn-success" type="submit" value="Confirmar">
	<input id="editar" class="btn btn-primary" type="submit" value="Atualizar" style="display:none">
	<input id="excluir" class="btn btn-danger" type="submit" value="Excluir">
	
	</form>
	
	</section>
	
	
	
	
	
	
	
	 <!-- Footer -->
  <section class="container-fluid">
     <footer class="py-3 my-4">
       <ul class="nav justify-content-center border-bottom pb-3 mb-3">
         <li class="nav-item"><a href="./index.html" class="nav-link px-2 text-muted">Home</a></li>
         <li class="nav-item"><a href="./promocoes.html" class="nav-link px-2 text-muted">Promoções</a></li>
         <li class="nav-item"><a href="./destino.html" class="nav-link px-2 text-muted">Destinos</a></li>
         <li class="nav-item"><a href="./contato.html" class="nav-link px-2 text-muted">Contatos</a></li>
       </ul>
       <p class="text-center text-muted">© 2022 Viagens & Cinema</p>
     </footer>
  </section>
	
	
	

	<script>
    let input = document.getElementsByClassName("habilitar");
    let atualizar = document.getElementById("editar");
    function edicao(){
    	
        for(let i=0; i < input.length; i++){
            input[i].disabled=false;
        }
        atualizar.style.removeProperty("display");
        document.getElementById("confirmar").style.display="none";

    }
    document.getElementById("editar").onclick = function(){
     	document.getElementById("frmConfirmar").action ="editar";
    }
    
    document.getElementById("excluir").onclick = function(){
        document.getElementById("frmConfirmar").action ="excluir";
    }
    document.getElementById("confirmar").onclick = function(){
        document.getElementById("frmConfirmar").action ="confirmar";
    }
	</script>
	
	 <!-- script só para o hamburgão :) --------------------------------------------------->
  <script>
    const hamburger = document.getElementById("hamburger");
    const navHub = document.getElementById("navHub");

    hamburger.addEventListener("click", () =>{
      navHub.classList.toggle("show");
    });
  </script>
  <!-- ------------------------------------------------------------------------------->
	
	  <!-- Bootstrap JavaScript Libraries -->
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
    integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
    crossorigin="anonymous"></script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js"
    integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy"
    crossorigin="anonymous"></script>
</body>
</html>