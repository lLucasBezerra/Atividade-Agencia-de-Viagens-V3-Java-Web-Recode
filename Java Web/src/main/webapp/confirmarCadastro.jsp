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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
      integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
   
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
     
	<h1>oi</h1>
	
	
	
	
	
	
	
	
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