<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="rmSection" value="guj/home"/>

<%@ include file="/header.jsp" %>
<style type="text/css">@import url(<c:url value="/stylesheets/opencloud.css"/>);</style>

<div id="content">
	<div id="home">
		<div class="forum box big-box">
			<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
			<div class="spiffyfg">
			  <h2>
			    <a href='<c:url value="/perguntas"/>'>Novo GUJ (beta)</a>
			  </h2>
			  <ul>
				  	<c:forEach items="${brutalQuestions}" var="question">
					<li>
						<a href="${question.link}">${fn:escapeXml(question.title)}</a>
						-  <tags:prettyTime time="${question.date}"/>
					</li>
				</c:forEach>
				</ul>
				
				<%@include file="/tag_cloud.jsp" %>
			</div>
			<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>
		</div>
		
		
		<div class="news box big-box">
			<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
			<div class="spiffyfg">
				  <h2>
				    <a href="<c:url value="/noticias"/>">Not&iacute;cias</a>
				  </h2>
				  <ul>
				  	<c:forEach items="${news}" var="n">
				  		<li>
				  			<a href="${n.link}">${fn:escapeXml(n.title)}</a>
						-  <tags:prettyTime time="${n.date}"/>
					    </li>
			  		</c:forEach>
				  </ul>
				  <p align="center" class="oas_ad">
					<script type="text/javascript" language="JavaScript">
						OAS_AD('Right');
					</script>
				</p>
			</div>
			
			<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>
		</div>
	
		<div class="forum box big-box">
			<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
			<div class="spiffyfg">
			  <h2>
			    <a href="<c:url value="/recentTopics/list.java"/>">Forum - &Uacute;ltimas mensagens</a>
			  </h2>
			  <ul>
			  	<c:forEach items="${forum}" var="topic">
			  		<li>
			  			<a href="${topic.link}">${fn:escapeXml(topic.title)}</a>
						-  <tags:prettyTime time="${topic.date}"/>
				    </li>
		  		</c:forEach>
			  </ul>
			</div>
			
			<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>
		</div>
		<div class="posts box small-box">
			<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
			<div class="spiffyfg">
				  <h2><a href="<c:url value="/jobs"/>">Ofertas de Empregos</a></h2>
				  <ul>
				  	<c:forEach items="${jobs}" var="job">
				  		<li>
				      		<span class="post"><a href="${job.link}" target="_blank">${fn:escapeXml(job.title)}</a>,</span>
				      		<span class="data"> - <tags:prettyTime time="${job.date}"/></span>
				    	</li>
				  	</c:forEach>
				  </ul>
			</div>
			<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>			
		</div>
		
		<div class="facebook box small-box">
			<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
			<div class="spiffyfg">
				  <div style="text-align: center;">
				  	<iframe src="http://www.facebook.com/plugins/likebox.php?href=http%3A%2F%2Fwww.facebook.com%2FGUJ.com.br&amp;width=370&amp;colorscheme=light&amp;show_faces=true&amp;stream=false&amp;header=false&amp;height=267" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:370px; height:267px;" allowTransparency="true"></iframe>
				  </div>
				  <br />
			</div>
			<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>
		</div>
		
		<div class="infoq box small-box">
			<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
			<div class="spiffyfg">
				<h2><a href="http://www.infoq.com/br">Infoq Brasil</a></h2>
			  	<ul>
			  		<c:forEach items="${infoq}" var="item">
				  		<li>
				  			<a href="${item.link}">${fn:escapeXml(item.title)}</a>
					      	<span class="data">- <tags:prettyTime time="${item.date}"/></span>
					    </li>
			  		</c:forEach>
				  </ul>
			</div>
			<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>
		</div>
	</div>
</div>

<%@ include file="/footer.jsp" %>