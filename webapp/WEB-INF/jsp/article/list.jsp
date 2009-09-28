<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="title">Todos os artigos</c:set>

<%@ include file="/header.jsp" %>
<style type="text/css">@import url(<c:url value="/stylesheets/tagging.css"/>);</style>

<div id="content">

<div class="articles box">
	<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
	<div class="spiffyfg inner-content">
		<h2><a href="#">Categorias</a> |
		</h2>
		
		<div style="margin-left: 25px;">
			<c:forEach items="${categories}" var="category">
				<c:if test="${category.articles.size$0 > 0}">
					<a href="#category_${category.id}">${category.name}</a> |
				</c:if> 
			</c:forEach>
			
		</div>
		
		<br/><br/>
		
	<script type="text/javascript">
		function addTag(articleId) {
			var tags = prompt('Informe as tags, separando-as por vírgula');
			
			if (tags != null && tags.length > 0) {
				$.post('<c:url value="/articles/"/>' + articleId + '/tags',
					{tags: tags },
					function() {
						var s = '';
						var p = tags.split(',');
						
						for (var i = 0; i < p.length; i++) {
							s += '<span class="tagging-list-item tag tag_front"><b><a href="<c:url value="/articles/"/>'+ p[i].replace(/^\s*|\s*$/g, "") +'">' + p[i] + '</a></b></span>';
						}
					
						$("#tags_" + articleId).append(s);
					}
				);
			}
		}
	</script>
		<ul>
			<c:forEach items="${categories}" var="category">
				<c:if test="${category.articles.size$0 > 0}">
					<div class="categoryHeader">${category.name}<a name="category_${category.id}"></a></div>
					<div class="categoryArticles">
					<c:forEach items="${category.articles}" var="article">
					 <c:if test="${article.approved}">
						<li>
							<c:choose>
								<c:when test="${article.exclusive}">
									<span class="post" style="font-size: 110%; font-weight: bold;"><a href="<c:url value="/articles/${article.id}"/>">${article.title}</a></span>
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
							
							<div class="tagging-container">
								<div class="tagging">
									<span class="tagging-image"></span>
									<span class="tagging-list" id="tags_${article.id}">
										<c:forEach items="${article.tags}" var="tag">
											<span class="tagging-list-item tag tag_front">
												<b><a class="tagging-link"  href="<c:url value="/articles/${tag.name}"/>">${tag.name}</a>&nbsp;</a></b>
											</span>
										</c:forEach>
									</span>
								</div>
								
								<c:if test="${logged}">
									&nbsp;<strong><a href="#tag" onClick="addTag(${article.id});" style="font-size: 75%;">[Adicionar tag]</a></strong>
								</c:if>
							</div>
							<br/><br>
						</li>
					</c:if>
					</c:forEach>
					</div>
				</c:if>
			</c:forEach>
		  </ul>
	</div>
	<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>

</div>
</div>

<%@ include file="/footer.jsp" %>
