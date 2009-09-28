<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${isModerator}">

	<c:set var="title">Categorias</c:set>

	<%@ include file="../header.jsp"%>


	<div id="content">

	<div class="articles box"><b class="spiffy"><b
		class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b
		class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
	<div class="spiffyfg inner-content">
	<h2><a href="#">Categorias</a></h2>

	<br/>

	<form action="<c:url value="/category.add.logic"/>" method="POST">
	
	 <c:if test="${errors.size$0 > 0}">
	   		Campos obrigatórios:
			<div id="errors">
				<ul>
				        <c:forEach var="error" items="${errors.iterator}">
				                <li>${error.key}</li>
				        </c:forEach>
				</ul>
			</div>
		    <br/>
	    </c:if>

	Nome: <input class="artigo rounded" size="45" name="category.name"
		tabindex="1" type="text" /> <input type="submit" value="Enviar" /></form>

	</div>
	<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b
		class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>

	</div>


	<div style="float: left; width: 12%; margin-left: 8px;"><!-- OAS AD 'x04' begin -->
	<SCRIPT LANGUAGE="JavaScript">
		<!--
		OAS_AD('x04');
		//-->
		</SCRIPT> <!-- OAS AD 'x04' end --></div>

	</div>


	<%@ include file="../footer.jsp"%>
</c:if>
