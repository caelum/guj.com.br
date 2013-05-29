
<!-- OAS AD 'x01' begin -->
<SCRIPT LANGUAGE="JavaScript">
<!--
OAS_AD('x01');
//-->
</SCRIPT>
<!-- OAS AD 'x01' end -->

<iframe src="<c:url value="/ping_session.jsp" />" height="0" width="0" frameborder="0" scrolling="no"></iframe>
<div class="footer">
	<div class="container">
		
		<ul class="footer-list">
			<li class="sponsor-box">
				<a class="footer-logo guj-logo" href="<c:url value="/" />">GUJ</a>
				<ul class="footer-sub-list">
					<li>Publicidade? guj@guj.com.br</li>
				</ul>
			</li>
			<li class="sponsor-box">
				<a class="footer-logo caelum-logo" href="http://www.caelum.com.br">CAELUM</a>
				<ul class="footer-sub-list">
					<li><a class="footer-link" href="http://www.caelum.com.br/cursos/java/">Java</a></li>
					<li><a class="footer-link" href="http://www.caelum.com.br/cursos/mobile/">Mobile</a></li>
					<li><a class="footer-link" href="http://www.caelum.com.br/cursos/web/">Front-End</a></li>
					<li><a class="footer-link" href="http://www.caelum.com.br/cursos/agile/">Agile</a></li>
					<li><a class="footer-link" href="http://www.caelum.com.br/cursos/rails/">Rails</a></li>
					<li><a class="footer-link" href="http://www.caelum.com.br/cursos/dotnet/">.NET</a></li>
				</ul>
			</li>
			<li class="sponsor-box">
				<a class="footer-logo cdc-logo" href="http://www.casadocodigo.com.br/">CASA DO CÓ“DIGO</a>
				<ul class="footer-sub-list">
					<li>Conheça também os livros da <a class="footer-link" href="http://www.casadocodigo.com.br">Casa do Código</a></li>
				</ul>
			</li>
		<!-- <li class="sponsor-box"><a class="footer-logo online-logo" href="#">Caelum Online</a></li> -->
		</ul>
		
		<%@include file="/tag_cloud.jsp" %>

		<span class="footer-menu">
			<span class="footer-menu-label">GUJ Respostas: </span>
			
			<a class="footer-link" href="<c:url value="/nao-resolvido" />" class="unsolved-link">Não solucionadas</a>
			<a class="footer-link" href="<c:url value="/sem-respostas" />">Sem respostas</a>
		</span>
		<span class="footer-menu">
			<span class="footer-menu-label">GUJ Fórum: </span>
			<a class="footer-link" href="<c:url value="/forums/list.java" />">Índice de Fóruns</a>
			<a class="footer-link" href="<c:url value="/recentTopics/list.java" />">Tópicos Recentes</a>
		</span>
		<span class="footer-muted-message">Desenvolvido por <a class="footer-link" href="http://www.caelum.com.br">Caelum</a> &#8212 GUJ: desde 2002</span>
	</div>
</div>
 
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
var pageTracker = _gat._getTracker("UA-132222-1");
pageTracker._initData();
pageTracker._trackPageview();
</script>
  
</body>
</html>
