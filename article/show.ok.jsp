<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="title">${article.title}</c:set>

<%@ include file="../header.jsp" %>
<link href="<c:url value="/stylesheets/SyntaxHighlighter.css"/>" media="screen" rel="stylesheet" type="text/css" />
<style type="text/css">@import url(<c:url value="/stylesheets/tagging.css"/>);</style>
<script src="<c:url value="/javascripts/shCore.js"/>" type="text/javascript"></script>
<script src="<c:url value="/javascripts/shBrushJava.js"/>" type="text/javascript"></script>

<script type="text/javascript">
window.onload = function () {
	dp.SyntaxHighlighter.HighlightAll('code');
}
</script>

<div id="content">
<div class="box">
<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
	<div class="spiffyfg inner-content">
  <span class="article">
    <h2><a href="#">${article.title}</a>
    <c:if test="${isAuthor or isModerator}">- 
    	<a href="<c:url value="/article.open.logic?id=${article.id}"/>">Editar</a>
    </c:if>
    <c:if test="${isModerator and article.approved eq true}">-
    	<a href="<c:url value="/approve.disaproove.logic?id=${article.id}"/>"> <img src="<c:url value="/images/guj/thumb_down.gif" />" border="0" name="approveDown" title="Desaprovar" /></a>
    </c:if>								 
    </h2>
  </span>
  
  <div class="miolo">
    <span class="data">em <fmt:formatDate pattern="dd/MM/yyyy" value="${article.date}"/></span>
    <span class="autor">, por <a href="mailto:${article.authorEmail}">${article.author}</a></span>
    <br/>
  
    <c:if test="${not empty article.content}">
	    <div style="float: left; padding: 10px;">
			<!-- OAS AD 'Right' begin -->
			<SCRIPT LANGUAGE="JavaScript">
			<!--
			OAS_AD('Right');
			//-->
			</SCRIPT>
			<!-- OAS AD 'Right' end -->
		</div>
	      
	      <span class="content">${article.formatedContent}</span>
    </c:if>

    <span class="pdf">
    <c:if test="${not empty article.pdf}">
      <br/>
      <span class="content">${article.subtitle}</span>
      <br/><br/>
      
      <div style="border-left: 3px solid #ccc; padding-left: 10px;">
			<!-- OAS AD 'Right' begin -->
			<SCRIPT LANGUAGE="JavaScript">
			<!--
			OAS_AD('Right');
			//-->
			</SCRIPT>
			<!-- OAS AD 'Right' end -->
      </div>
      
      <h3>Este artigo est&aacute; em formato PDF</h3>
      <a href="${article.pdf }">Para baixar, imprimir ou abrir, basta seguir este link!</a>
    </c:if>
    </span>
  </div>
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