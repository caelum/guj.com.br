<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type='text/javascript' src='http://d1.openx.org/spcjs.php?id=5184&amp;target=_blank'></script>
<meta http-equiv="content-type" content="text/html;charset=ISO-8859-1" />
<title>GUJ - Not&iacute;cias, artigos e o maior f&oacute;rum brasileiro sobre Java</title>
<link href="<c:url value="/stylesheets/guj3.css"/>" media="screen" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="<c:url value="/stylesheets/guj3-ie.css"/>" media="screen" rel="stylesheet" type="text/css" />
<![endif]-->
  
<script type="text/javascript" src="<c:url value="/javascripts/jquery-1.3.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/javascripts/jquery.dimensions.min.js"/>"></script>
<script type="text/javascript">
$().ready(function() {
	$("#returnPath").val(document.location);
});
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
        <a href="<c:url value="/home.index.logic"/>" id="logo">GUJ</a>
        <h2>Not&iacute;cias, artigos e o maior f&oacute;rum brasileiro sobre Java</h2></a>
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
      
      <li class="topics">
        <a href="<c:url value="/recentTopics/list.java"/>">t&oacute;picos recentes</a>
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
		<input class="campo rounded" name="search_keywords" value="Pesquisar" onFocus="if (this.value == 'Pesquisar') { this.value = '';}" onBlur="if (this.value == '') { this.value = 'Pesquisar';}" tabindex="1"/>
      	<input type="image" class="botao" style="border: 0px;" src="<c:url value="/images/guj/botao_ok.gif"/>" tabindex="2">
    </form>
  </div>
  
  <div id="submenu">
    <div class="container1">
      <div class="container2">
          
      	<c:choose>
      		<c:when test="${logged}">
              <a id="latest" class="mainmenu" href="<c:url value="/recentTopics/list.java"/>"><img src="<c:url value="/templates/default/images/icon_mini_recentTopics.gif"/>" alt="[Recent Topics]" border="0"/>
                T&Oacute;PICOS RECENTES
              </a> &nbsp;
	      	  <a id="myprofile" class="mainmenu" href="<c:url value="/user/edit/${userSession.userId}.java"/>"><img src="<c:url value="/templates/default/images/icon_mini_profile.gif"/>" border="0" alt="[Profile]" /> MEUS DADOS</a>&nbsp; 
              <a id="privatemessages" class="mainmenu" href="<c:url value="/pm/inbox.java"/>"><img src="<c:url value="/templates/default/images/icon_mini_message.gif"/>" border="0" alt="[Message]" />
                MENSAGENS PRIVADAS (${userSession.privateMessages})
              </a>&nbsp;
              <a id="mymessages" class="mainmenu" href="<c:url value="/posts/listByUser/${userSession.userId}.java"/>"><img src="<c:url value="/templates/default/images/icon_mini_message.gif"/>" border="0" alt="Minhas Mensagens" />
                MINHAS MENSAGENS
              </a>&nbsp;
              <a id="favorites" class="mainmenu" href="<c:url value="/bookmarks/list/${userSession.userId}.java"/>"><img src="<c:url value="/templates/default/images/icon_mini_message.gif"/>" border="0" alt="Favoritos" />
                   FAVORITOS
              </a>&nbsp;
            
	      		<a href="<c:url value="/user/logout.java"/>"><img src="<c:url value="/templates/default/images/icon_mini_login.gif"/>" border="0" alt="Logout" /> LOGOUT</a> 
      		</c:when>
      		<c:otherwise>
	      		Bem vindo ao GUJ. <a href="<c:url value="/user/insert.java"/>">Crie seu login</a>, 
	          	ou digite-o para logar no site.
              
	          	<form class="login" action="<c:url value="/jforum.java?module=user&action=validateLogin"/>" method="POST">
                    <input type="hidden" name="returnPath" id="returnPath"/>
	            	Usu&aacute;rio: <input class="campo rounded" size="8" name="username" tabindex="3" />
					Senha: <input class="campo rounded" type="password" size="8" name="password" tabindex="4"/>
	            	<input type="image" class="botao" src="<c:url value="/images/guj/botao_entrar.gif"/>" tabindex="5" name="login" value="Login">
	          	</form>
      		</c:otherwise>
      	</c:choose>
      </div>
    </div>
  </div>

  
