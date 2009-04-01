<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="rmSection" value="guj/home"/>

<%@ include file="../header.jsp" %>
<script type="text/javascript" src="<c:url value="/javascripts/guj.js"/>"></script>

<script type="text/javascript">
$().ready(function() {
	alignHomeBoxes();
});
</script>

<div id="content">
	<div id="home">
	<div class="forum box">
		<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
		<div class="spiffyfg">
		  <h2>
		    <a href="<c:url value="/recentTopics/list.java"/>">Forum - &Uacute;ltimas mensagens</a>
		  </h2>
		  <ul>
		  	<c:forEach items="${forum}" var="topic">
		  		<li>
		  			<a href="${topic.link}">${fn:escapeXml(topic.title)}</a>
			      	<span class="autor">por ${topic.creator},
			      	em <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${topic.date}"/></span>
			    </li>
	  		</c:forEach>
		  </ul>
		</div>
		
		<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>
	</div>

	<div class="news box">
		<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
		<div class="spiffyfg">
			  <h2>
			    <a href="<c:url value="/forums/show/17.java"/>">Not&iacute;cias</a>
			  </h2>
			  <ul>
			  	<c:forEach items="${news}" var="n">
			  		<li>
			  			<a href="${n.link}">${fn:escapeXml(n.title)}</a>
				      	<span class="autor">por ${n.creator},
				      	em <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${n.date}"/></span>
				    </li>
		  		</c:forEach>
			  </ul>
			  
			  <p align="center">
				<!-- OAS AD 'Right' begin -->
				<SCRIPT LANGUAGE="JavaScript">
				<!--
				OAS_AD('Right');
				//-->
				</SCRIPT>
				<!-- OAS AD 'Right' end -->
			</p>
		</div>
		
		<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>
	</div>

	<div class="articles box">
		<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
		<div class="spiffyfg">
			  <h2><a href="<c:url value="/article.list.logic"/>">Artigos</a></h2>
			  <ul>
		    	<c:forEach items="${articles}" var="article">
					<li>
						<h3><a href="<c:url value="/article.show.logic?id=${article.id}"/>">${fn:escapeXml(article.title)}</a></h3>
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

	<div class="posts box">
		<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
		<div class="spiffyfg">
			  <h2><a href="#">Posts de blogs</a></h2>
			  <ul>
			  	<c:forEach items="${posts}" var="post">
			  		<li>
			      		<span class="post"><a href="${post.link}" target="_blank">${fn:escapeXml(post.title)}</a></span>
			      		<span class="data">em <span class="blog">${post.blog.title}</span>, <fmt:formatDate pattern="dd/MM/yyyy" value="${post.date}"/></span>
			    	</li>
			  	</c:forEach>
			  </ul>
		</div>
		<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>			
	</div>

	<div class="infoq box">
		<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
		<div class="spiffyfg">
			<h2><a href="http://www.infoq.com/br">Infoq Brasil</a></h2>
		  	<ul>
		  		<c:forEach items="${infoq}" var="item">
			  		<li>
			  			<a href="${item.link}">${fn:escapeXml(item.title)}</a>
				      	<span class="autor">por ${item.creator}</span>
				      	<span class="data">em <fmt:formatDate pattern="dd/MM/yyyy" value="${item.date}"/></span>
				    </li>
		  		</c:forEach>
			  </ul>
		</div>
		<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>
		</div>
</div>
  </div>

<%@ include file="../footer.jsp" %>