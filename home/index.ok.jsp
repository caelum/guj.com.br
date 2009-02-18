<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>

  <div id="content">
  	<div id="home">
	<div class="forum box rounded">
	  <h2>
	    <a href="<c:url value="/forums/list.java"/>">Forum - &Uacute;ltimas mensagens</a>
	  </h2>
	  <ul>
	  	<c:forEach items="${forum}" var="topic">
	  		<li>
	  			<a href="${topic.link}">${topic.title}</a>
		      	<span class="autor">por ${topic.creator},
		      	em <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${topic.date}"/></span>
		    </li>
  		</c:forEach>
	  </ul>
	</div>

	<div class="news box rounded">
	  <h2>
	    <a href="<c:url value="/forums/show/17.java"/>">Not&iacute;cias</a>
	  </h2>
	  <ul>
	  	<c:forEach items="${news}" var="n">
	  		<li>
	  			<a href="${n.link}">${n.title}</a>
		      	<span class="autor">por ${n.creator},
		      	em <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${n.date}"/></span>
		    </li>
  		</c:forEach>
	  </ul>
	</div>
	
	<div class="articles box rounded">
	  <h2><a href="#">Artigos</a></h2>
	  <ul>
    	<c:forEach items="${articles}" var="article">
			<li>
				<h3><a href="<c:url value="/article/show?id=${article.id}"/>">${article.title}</a></h3>
		      	<span class="content">
		      		<c:choose>
		      			<c:when test="${article.subtitle.length$0 > 200}">
		      				${article.subtitle.substring$2[0][200]}...
		      			</c:when>
		      			<c:otherwise>
		      				${article.subtitle}
		      			</c:otherwise>
		      		</c:choose>
		      	</span>
			</li>	    	
    	</c:forEach>
	  </ul>
	</div>

	<div class="posts box rounded">
	  <h2><a href="#">Posts de blogs</a></h2>
	  <ul>
	  	<c:forEach items="${posts}" var="post">
	  		<li>
	      		<span class="post"><a href="${post.link}" target="_blank">${post.title}</a></span>
	      		<span class="data">em <span class="blog">${post.blog.title}</span>, <fmt:formatDate pattern="dd/MM/yyyy" value="${post.date}"/></span>
	    	</li>
	  	</c:forEach>
	  </ul>
	</div>

	<div class="infoq box rounded">
		<h2><a href="http://www.infoq.com/br">Infoq Brasil</a></h2>
	  	<ul>
	  		<c:forEach items="${infoq}" var="item">
		  		<li>
		  			<a href="${item.link}">${item.title}</a>
			      	<span class="autor">por ${item.creator}</span>
			      	<span class="data">em <fmt:formatDate pattern="dd/MM/yyyy" value="${item.date}"/></span>
			    </li>
	  		</c:forEach>
		  </ul>
		</div>
</div>
  </div>

<%@ include file="../footer.jsp" %>