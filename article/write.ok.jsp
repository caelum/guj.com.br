<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="title">Criação de artigos</c:set>

<%@ include file="../header.jsp" %>
<style type="text/css">@import url(<c:url value="/stylesheets/tagging.css"/>);</style>

<div id="content">

<div class="articles box">
	<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
	<div class="spiffyfg inner-content">
		<h2><a href="#">Criação de artigos</a></h2>
		<br><br>
	
	<c:choose>
		<c:when test="${not logged}">Você deve estar logado(a) para escrever um artigo.</c:when>
		<c:otherwise>
		
		<form method="POST" action="<c:url value="/article.save.logic"/>" enctype="multipart/form-data">
	    
	    <div class="categoryHeader">Informações do autor</div>  <br/>
	   
	   <c:if test="${errors.size$0 > 0}">
	   		Campos obrigatórios:
			<div id="errors">
				<font color="red">
				<ul>
				        <c:forEach var="error" items="${errors.iterator}">
				                <li>${error.key}</li>
				        </c:forEach>
				</ul>
				</font>
			</div>
		    <br/>
	    </c:if>
	
		<input type="hidden" value="${article.id}" name="article.id" />    
		
	    Título:    <input class="artigo rounded" size="45" name="article.title" value="${article.title}" type="text"/> <br/><br/>
		Subtítulo: <input class="artigo rounded" type="text" size="45" name="article.subtitle" value="${article.subtitle}" tabindex="2"/> <br/><br/>
	    Nome:      <input class="artigo rounded" type="text" size="15" name="article.author" value="${article.author}" tabindex="3"/> <br/><br/>
		Email:     <input class="artigo rounded" type="text" size="15" name="article.authorEmail" value="${article.authorEmail}" tabindex="4"/> <br/><br/>

		<br/>
		<div class="categoryHeader">Edição do artigo</div>
		
		<script type="text/javascript" src="<c:url value="/tiny_mce/tiny_mce.js"/>"></script>
		<script type="text/javascript">
		tinyMCE.init({
			// General options
			mode : "textareas",
			theme : "advanced",
			plugins : "safari,spellchecker,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,imagemanager,filemanager",
		
			// Theme options
			theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleselect,formatselect,fontselect,fontsizeselect",
			theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
			theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
			theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,spellchecker,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,blockquote,pagebreak,|,insertfile,insertimage",
			theme_advanced_toolbar_location : "top",
			theme_advanced_toolbar_align : "left",
			theme_advanced_statusbar_location : "bottom",
			theme_advanced_resizing : true,
		
			
		});
		</script>
		
			<textarea name="content" style="width:100%" >${article.content}</textarea>
		<br/>
		
		<div class="categoryHeader">Anexar arquivos (*.zip)</div>
		<br/>		
				Imagens (não deve possuir diretórios): <input type="file" name="images"/>
				<br/>
				<br/>
				C&oacute;digos: <input type="file" name="codes"/>
	
		
		<br/><br/>
			<input type="image" class="botao" src="<c:url value="/images/guj/botao_enviar.gif"/>" tabindex="5" />
		</form>
		</c:otherwise>
	</c:choose>
	
	</div>
	<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>

</div>

	<div style="float: left; width: 12%; margin-left: 8px;">
		<!-- OAS AD 'x04' begin -->
		<SCRIPT LANGUAGE="JavaScript">
		<!--
		OAS_AD('x04');
		//-->
		</SCRIPT>
		<!-- OAS AD 'x04' end -->
	</div>

</div>

<%@ include file="../footer.jsp" %>
