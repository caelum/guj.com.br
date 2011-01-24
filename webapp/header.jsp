<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=ISO-8859-1" />

<c:if test="${empty rmSection}">
	<c:set var="rmSection" value="guj/internas"/>
</c:if>

<c:if test="${empty title}">
	<c:set var="title">Not&iacute;cias, artigos e o maior f&oacute;rum brasileiro sobre Java - Home</c:set>
</c:if>

<title>GUJ - ${title}</title>
<link href="<c:url value="/stylesheets/guj3.css?20090222"/>" media="screen" rel="stylesheet" type="text/css" />

<!-- OAS SETUP begin -->
<SCRIPT LANGUAGE=JavaScript>
<!--
//configuration
OAS_url = 'http://media.realmedia.com.br/RealMedia/ads/';
OAS_listpos = 'Top,Right,x01,x04';
OAS_query = '';
OAS_sitepage = '${rmSection}';
//end of configuration
OAS_version = 10;
OAS_rn = '001234567890'; OAS_rns = '1234567890';
OAS_rn = new String (Math.random()); OAS_rns = OAS_rn.substring (2, 11);
function OAS_NORMAL(pos) {
	document.write('<A HREF="' + OAS_url + 'click_nx.ads/' + OAS_sitepage + '/1' + OAS_rns + '@' + OAS_listpos + '!' + pos + OAS_query + '" TARGET=_top>');
	document.write('<IMG SRC="' + OAS_url + 'adstream_nx.ads/' + OAS_sitepage + '/1' + OAS_rns + '@' + OAS_listpos + '!' + pos + OAS_query + '" BORDER=0></A>');
}
//-->
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
OAS_version = 11;
if (navigator.userAgent.indexOf('Mozilla/3') != -1)
OAS_version = 10;
if (OAS_version >= 11)
document.write('<SCR'+ 'IPT LANGUAGE=JavaScript1.1 SRC="' + OAS_url + 'adstream_mjx.ads/' + OAS_sitepage + '/1' + OAS_rns + '@' + OAS_listpos + OAS_query + '"></SC'+'RIPT>');
//-->
</SCRIPT><SCRIPT LANGUAGE="JavaScript">
<!-- 
document.write('')
function OAS_AD(pos) {
if (OAS_version >= 11)
	OAS_RICH(pos);
else
	OAS_NORMAL(pos);
}
//-->
</SCRIPT>
<!-- OAS SETUP end -->
<!--[if IE]>
<link href="<c:url value="/stylesheets/guj3-ie.css?20090222"/>" media="screen" rel="stylesheet" type="text/css" />
<![endif]-->
<script type="text/javascript" src="<c:url value="/javascripts/jquery-1.3.1.min.js?20090222"/>"></script>
<script type="text/javascript" src="<c:url value="/javascripts/jquery.dimensions.min.js?20090222"/>"></script>
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
			<!-- OAS AD 'Top' begin -->
			<SCRIPT LANGUAGE="JavaScript">
			<!--
			OAS_AD('Top');
			//-->
			</SCRIPT>
			<!-- OAS AD 'Top' end -->
    	</div>
      <div class="container2">
        <a href="<c:url value="/"/>" id="logo">GUJ</a>
        <h2>Not&iacute;cias, artigos e o maior f&oacute;rum brasileiro sobre Java</h2></a>
      </div>
    </div>
  </div>

  <div id="menu">
    <ul>
      <li class="home">
        <a href="<c:url value="/"/>">home</a>
      </li>
      <li class="forum">
        <a href="<c:url value="/forums/list.java"/>">f&oacute;rum</a>
      </li>
      
      <li class="noticias">
        <a href="<c:url value="/noticias"/>">not&iacute;cias</a>
      </li>
      
      <li class="topics">
        <a href="<c:url value="/recentTopics/list.java"/>">t&oacute;picos recentes</a>
      </li>
        
      <li class="empregos">
		<a href="<c:url value="/jobs"/>">empregos</a>
      </li>
    
    	<!--
      <li class="blogs">
        <a href="${pageContext.request.contextPath}/posts">blogs</a>
      </li>
      -->
    </ul>
    
    <form action="<c:url value="/search"/>" id="cse-search-box" class="busca">
	    <input type="hidden" name="cx" value="partner-pub-9448585618971060:4001950301" />
	    <input type="hidden" name="cof" value="FORID:10" />
	    <input type="hidden" name="ie" value="UTF-8" />
	    <input class="campo_rounded" type="text" name="q" size="31" tabindex="1" />
	    <input type="image" class="botao" style="border: 0px;" src="<c:url value="/images/guj/botao_ok.gif"/>" tabindex="2">
	</form>

<script type="text/javascript" src="http://www.google.com.br/coop/cse/brand?form=cse-search-box&amp;lang=en"></script>
	<iframe src="http://www.facebook.com/plugins/like.php?href=http%3A%2F%2Fwww.facebook.com%2FGUJ.com.br&amp;layout=button_count&amp;show_faces=false&amp;width=200&amp;action=like&amp;colorscheme=light&amp;height=21" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:200px; height:21px; position:relative; top:7px;" allowTransparency="true"></iframe>
  </div>
  
  <div id="submenu">
    <div class="container1">
      <div class="container2">
          
      	<c:choose>
      		<c:when test="${logged}">
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
	      		<c:if test="${not newsletterParticipant}">
	      			<a href="<c:url value="/newsletter/"/>?_method=POST&gujUserId=${userSession.userId}">Desejo receber as Newsletters do GUJ com meu e-mail</a>
	      			<a href="javascript:entraNewsletter();">Com outro e-mail</a>
	      			<div id="entraNewsletter" style="display: none; position: absolute;">
	      				<form action="<c:url value="/newsletterWithConfirmation/"/>" method="post">
	      					<input type="hidden" name="gujUserId" value="${userSession.userId}"/>
	      					<input type="text" name="email">
							<input type="submit" value="Participar">
	      				</form>
	      			</div>
	      		</c:if>
      		</c:when>
      		<c:otherwise>
	      		Bem vindo ao GUJ. <a href="<c:url value="/user/insert.java"/>" style="color: #4382B0;">Crie seu login</a>, 
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

  
<script language="javascript">
	function entraNewsletter() {
		$('#entraNewsletter').toggle();
	}
</script>