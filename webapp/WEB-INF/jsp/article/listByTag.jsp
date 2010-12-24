<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="title">Todos os artigos da tag ${tag.name}</c:set>

<%@ include file="/header.jsp" %>
<style type="text/css">@import url(<c:url value="/stylesheets/tagging.css"/>);</style>

<div id="content">

<div class="articles box">
	<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
	<div class="spiffyfg inner-content">

		<h2><a href="#">Todos artigos da tag <em>"${tag.name}"</em></a></h2>
		<span style="font-size: 90%; margin-left: 10px;"><a href="<c:url value="/articles"/>">Clique aqui</a> para voltar à listagem de todos os artigos</span>
		<br/><br/>
		<ul>
			<div class="categoryArticles">
				<c:forEach items="${articles}" var="article">
					<li>
						<span class="post" style="font-size: 110%; font-weight: bold;"><a href="<c:url value="/articles/${article.id}"/>">${article.title}</a></span>
						<span class="data">em <fmt:formatDate pattern="dd/MM/yyyy" value="${article.date}"/></span>
						<br/>
						
						<c:if test = "${not empty article.subtitle}">
							<span class="content">${article.subtitle}</span>
						</c:if>
						
						<div class="tagging-container">
							<div class="tagging">
								<span class="tagging-image"></span>
								<span class="tagging-list" id="tags_${article.id}">
									<c:forEach items="${article.tags}" var="tag">
										<span class="tagging-list-item tag tag_front">
											<b><a class="tagging-link"  href="<c:url value="/articles/tag/${tag.name}"/>">${tag.name}</a>&nbsp;</a></b>
										</span>
									</c:forEach>
								</span>
							</div>
						</div>
						<br/><br>
					</li>
				</c:forEach>
			</div>
		  </ul>
	</div>
	<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>
</div>
</div>
<%@ include file="/footer.jsp" %>
