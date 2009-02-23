<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../header.jsp" %>

<div id="content">

<div class="articles box">
	<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
	<div class="spiffyfg inner-content">
		<h2><a href="#">Categorias</a></h2>
		<div style="margin-left: 25px;">
			<c:forEach items="${categories}" var="category">
				<c:if test="${category.articles.size$0 > 0}">
					<a href="#category_${category.id}">${category.name}</a> |
				</c:if> 
			</c:forEach>
		</div>
		
		<br><br>
	
		<h2><a href="#">Todos artigos</a></h2>
		<ul>
			<c:forEach items="${categories}" var="category">
				<c:if test="${category.articles.size$0 > 0}">
					<div class="categoryHeader">${category.name}<a name="category_${category.id}"></a></div>
					<div class="categoryArticles">
					<c:forEach items="${category.articles}" var="article">
						<li>
							<c:choose>
								<c:when test="${article.exclusive}">
									<span class="post"><a href="<c:url value="/article.show.logic?id=${article.id}"/>">${article.title}</a></span>
								</c:when>
								<c:otherwise>
									<span class="post"><a href="#">${article.title}</a></span>
								</c:otherwise>
							</c:choose>
	
							<span class="data">em <fmt:formatDate pattern="dd/MM/yyyy" value="${article.date}"/></span>
							<br/>
							
							<c:if test = "${not empty article.subtitle}">
								<span class="content">${article.subtitle}</span>
							</c:if>
							<br/>
						</li>
					</c:forEach>
					</div>
				</c:if>
			</c:forEach>
		  </ul>
	</div>
	<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>
</div>
</div>
<%@ include file="../footer.jsp" %>
