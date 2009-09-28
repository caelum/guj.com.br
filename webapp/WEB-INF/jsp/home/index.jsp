<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="rmSection" value="guj/home"/>

<%@ include file="/header.jsp" %>
<style type="text/css">@import url(<c:url value="/stylesheets/opencloud.css"/>);</style>
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
				<script type="text/javascript"><!--
					google_ad_client = "pub-7098584793170190";
					/* GUJ Quadrado Home */
					google_ad_slot = "3282687954";
					google_ad_width = 300;
					google_ad_height = 250;
					//-->
					</script> 
					<script type="text/javascript" src="http://pagead2.googlesyndication.com/pagead/show_ads.js"> 
				</script> 
			</p>
		</div>
		
		<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>
	</div>

	<div class="articles box">
		<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
		<div class="spiffyfg">
			  <h2><a href="<c:url value="/articles"/>">Artigos</a></h2>
			 <ul>
		    	<c:forEach items="${articles}" var="article">
					<li>
						<h3><a href="<c:url value="/articles/${article.id}"/>">${fn:escapeXml(article.title)}</a></h3>
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

	<div class="posts box">
		<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
		<div class="spiffyfg">
			  <h2><a href="<c:url value="/jobs"/>">Ofertas de Empregos</a></h2>
			  <ul>
			  	<c:forEach items="${jobs}" var="job">
			  		<li>
			      		<span class="post"><a href="${job.link}" target="_blank">${fn:escapeXml(job.title)}</a>,</span>
			      		<span class="data">em <fmt:formatDate pattern="dd/MM/yyyy" value="${job.date}"/></span>
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

<%@ include file="/footer.jsp" %>