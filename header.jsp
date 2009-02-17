<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <script type='text/javascript' src='http://d1.openx.org/spcjs.php?id=5184&amp;target=_blank'></script>
  <meta http-equiv="content-type" content="text/html;charset=ISO-8859-1" />
  <title>
    GUJ - Not&iacute;cias, artigos e o maior f&oacute;rum brasileiro sobre Java
  </title>
  <link href="<c:url value="/stylesheets/guj3.css"/>" media="screen" rel="stylesheet" type="text/css" />
  <link href="<c:url value="/stylesheets/SyntaxHighlighter.css"/>" media="screen" rel="stylesheet" type="text/css" />
	<script src="<c:url value="/javascripts/captcha.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/javascripts/shCore.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/javascripts/shBrushJava.js"/>" type="text/javascript"></script>
    <script type="text/javascript" src="<c:url value="/javascripts/jquery-1.3.1.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/javascripts/jquery.corners.min.js"/>"></script>
    <script type="text/javascript">
    $().ready(function() {
		$("#returnPath").val(document.location);
	});

	window.onload = function () {
		$("div.rounded").corners("6px");
	}
  </script>  
  
</head>

<body>
  <div id="header">
    <div class="container1">
    	<div id="banner">
			<script type='text/javascript'><!--// <![CDATA[
			    OA_show(18786);
			// ]]> --></script>
    	</div>
      <div class="container2">
        <h1>GUJ</h1>
        <h2>Not&iacute;cias, artigos e o maior f&oacute;rum brasileiro sobre Java</h2>
      </div>
    </div>
  </div>

  <div id="menu">
    <ul>
      <li class="home">
        <a href="<c:url value="/home.index.logic"/>">home</a>
      </li>
      <li class="forum">
        <a href="<c:url value="/forums/list.java"/>">f&oacute;rum</a>
      </li>
      <li class="artigos">
        <a href="<c:url value="/article.list.logic"/>">artigos</a>
      </li>
      <li class="noticias">
        <a href="<c:url value="/forums/show/17.java"/>">not&iacute;cias</a>
      </li>
      
      <!--
      <li class="empregos">
        <a href="${pageContext.request.contextPath}/jobs">empregos</a>
      </li>
      <li class="blogs">
        <a href="${pageContext.request.contextPath}/posts">blogs</a>
      </li>
      -->
    </ul>
    <form class="busca" method="GET" action="<c:url value="/jforum.java?module=search&action=search"/>">
		<input type="hidden" name="module" value="search"/>
		<input type="hidden" name="action" value="search"/>
		<input type="hidden" name="returnPath" id="returnPath"/>
		<input class="campo rounded" name="search_keywords" value="Pesquisar" onFocus="if (this.value == 'Pesquisar') { this.value = '';}" onBlur="if (this.value == '') { this.value = 'Pesquisar';}" size="10" tabindex="1"/>
      	<input type="image" class="botao" style="border: 0px;" src="<c:url value="/images/guj/botao_ok.gif"/>" tabindex="2">
    </form>
  </div>
  
  <div id="submenu">
    <div class="container1">
      <div class="container2">
      	<c:choose>
      		<c:when test="${logged}">
	      		Bem vindo ao GUJ. Autenticado como ${username}
	      		<a href="<c:url value="/user/logout.java"/>">Fazer logout</a> 
      		</c:when>
      		<c:otherwise>
	      		Bem vindo ao GUJ. <a href="<c:url value="/user/insert.java"/>">Crie seu login</a>, 
	          	ou digite-o para logar no site.
	          
	          	<form class="login" action="<c:url value="/jforum.java?module=user&action=validateLogin"/>" method="POST">
	            	<input class="campo rounded" size="8" name="username" tabindex="3" onFocus="if (this.value == 'usu&aacute;rio') { this.value = '';}" onBlur="if (this.value == '') { this.value = 'usu&aacute;rio';}" value="usu&aacute;rio"/>
					<input class="campo rounded" type="text" size="8" name="password" tabindex="4" onFocus="if (this.value == 'senha') { this.value = ''; this.type='password';}" onBlur="if (this.value == '') { this.value = 'senha'; this.type='text'}" value="senha"/>
	            	<input type="image" class="botao" src="<c:url value="/images/guj/botao_entrar.gif"/>" tabindex="5" name="login" value="Login">
	          	</form>
      		</c:otherwise>
      	</c:choose>
      </div>
    </div>
  </div>

  