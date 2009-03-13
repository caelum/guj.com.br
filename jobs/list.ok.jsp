<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>

<div id="content">
	<div class="jobs box">
		<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
		
		<div class="spiffyfg inner-content">
			<h2><a href="#">Ofertas de Empregos</a></h2>
			<div style="padding: 20px;">
				<strong>Promo&ccedil;&atilde;o GUJ e NetCarreiras</strong><br>
				<em>
				Voc&ecirc; que &eacute; usu&aacute;rio do GUJ tem descontos especiais para ser um profissional associado ao NetCarreiras. 
				Acessando o site atrav&eacute;s do link <a href="http://netcarreiras.com/promo/guj" target="_new">http://netcarreiras.com/promo/guj</a>
				voc&ecirc; ganha entre 10% e 30% de desconto na assinatura dos planos do site.
				</em>
			</div> 
			<ul>
				<c:forEach items="${jobs}" var="job">
					<li>
						<span class="post"><a href="${job.link}" target="_new">${job.title}</a></span>
						<br/>
						
						<c:choose>
							<c:when test="${job.description.length$0 > 200}">
								${job.description.substring$2[0][200]}
							</c:when>
							<c:otherwise>
								${job.description}
							</c:otherwise>
						</c:choose>
						
						<br/>
					</li>
				</c:forEach>
	  		</ul>
		</div>
		
		<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>
	</div>
</div>

<%@ include file="../footer.jsp" %>