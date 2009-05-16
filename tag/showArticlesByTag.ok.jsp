<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
			
<c:set var="title">Lista de artigos com a tag ${name}</c:set>

<%@ include file="../header.jsp" %>

<div id="content">

<div class="articles box">
	<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
	<div class="spiffyfg inner-content">
		<h2><a href="#">Lista de artigos com a tag '${name}'</a></h2>

		<br/><br/>
		<ul>
		<c:forEach items="${articles}" var="article">
		<li>
			<span class="post" style="font-size: 110%; font-weight: bold;">
				<a href="<c:url value="/article.show.logic?id=${article.id}"/>">${article.title}</a>
			</span>
								
			<span class="data">em <fmt:formatDate pattern="dd/MM/yyyy" value="${article.date}"/></span>
			<br/>
							
			<c:if test = "${not empty article.subtitle}">
				<span class="content">${article.subtitle}</span>
			</c:if>
		</li>	
		<br/>
		</c:forEach>
		</ul>
		
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