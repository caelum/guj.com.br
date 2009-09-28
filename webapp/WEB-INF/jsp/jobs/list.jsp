<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/header.jsp" %>

<div id="content">
	<div class="jobs box">
		<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
		
		<div class="spiffyfg inner-content">
			<h2><a href="#">Ofertas de Empregos</a></h2>
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

<%@ include file="/footer.jsp" %>