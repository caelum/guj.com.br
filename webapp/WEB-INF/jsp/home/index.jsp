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
			    <a href="<c:url value="/noticias"/>">Not&iacute;cias</a>
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
<!-- 			  <br /> -->
<!-- 			  <h2> -->
<%-- 		      	  <a href="<c:url value="/recentTopics/list.java"/>">Siga-nos no twitter</a> --%>
<!-- 		  	  </h2> -->
<!-- 			  <a href="http://twitter.com/guj_noticias" target="_blank"> -->
<!-- 			  <div id="twitterWidget"> -->
<!-- 		  			<div id="twitterInfoWrapper"> -->
<!-- 		  				<div style="float:left"><img id="gujTwitterAvatar" /></div> -->
<!-- 		  				<div id="gujFollowers"><p>@guj_noticias</p></div> -->
<!-- 		  			</div> -->
<!-- 		  			<div id="conteudoUltimoTweet"> -->
		  				
<!-- 		  			</div> -->
<!-- 			  </div> -->
<!-- 			  </a> -->
<!-- 			  <div style="clear:both"></div> -->
			  <script type="text/javascript">
						function twitterWidget(selector, user) {
			
						    var element = $(selector);
			
						    $.getJSON(
						      "http://api.twitter.com/1/users/show/" + user + ".json?callback=?",
						      function(data) {
						            $("#gujTwitterAvatar").attr("src", data.profile_image_url );
						            $("#gujFollowers").append("<p>" + data.followers_count + " seguidores</p>");
						            $("#conteudoUltimoTweet").append("\"" + data.status.text + "\"");
						      }
						    );
						  }
						  twitterWidget("#twitterWidget","guj_noticias");
			  </script>
			  <p align="center">
				<script type="text/javascript" language="JavaScript">
					OAS_AD('Right');
				</script>
			</p>
		</div>
		
		<b class="spiffy"><b class="spiffy5"></b><b class="spiffy4"></b><b class="spiffy3"></b><b class="spiffy2"><b></b></b><b class="spiffy1"><b></b></b></b>
	</div>

	<div class="articles box">
		<b class="spiffy"><b class="spiffy1"><b></b></b><b class="spiffy2"><b></b></b><b class="spiffy3"></b><b class="spiffy4"></b><b class="spiffy5"></b></b>
		<div class="spiffyfg">
			  <div style="text-align: center;">
			  	<iframe src="http://www.facebook.com/plugins/likebox.php?href=http%3A%2F%2Fwww.facebook.com%2FGUJ.com.br&amp;width=370&amp;colorscheme=light&amp;show_faces=true&amp;stream=false&amp;header=false&amp;height=267" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:370px; height:267px;" allowTransparency="true"></iframe>
			  	
			  	
			  	<iframe src="http://www.facebook.com/plugins/recommendations.php?site=www.guj.com.br&amp;width=370&amp;height=300&amp;header=true&amp;colorscheme=light" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:370px; height:300px;" allowTransparency="true"></iframe>
			  	
			  </div>
			  <br />
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
	<div class="twitter box">
		<script src="http://widgets.twimg.com/j/2/widget.js"></script>
<script>
new TWTR.Widget({
  version: 2,
  type: 'search',
  search: '#guj -javalivros',
  interval: 6000,
  title: '',
  subject: 'O que falam do #GUJ?',
  width: 'auto',
  height: 300,
  theme: {
    shell: {
      background: '#8ec1da',
      color: '#ffffff'
    },
    tweets: {
      background: '#ffffff',
      color: '#444444',
      links: '#1985b5'
    }
  },
  features: {
    scrollbar: false,
    loop: true,
    live: true,
    hashtags: true,
    timestamp: true,
    avatars: true,
    toptweets: true,
    behavior: 'default'
  }
}).render().start();
</script>
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