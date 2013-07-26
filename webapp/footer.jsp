<iframe src="<c:url value="/ping_session.jsp" />" height="0" width="0" frameborder="0" scrolling="no"></iframe>
<div class="footer">
	<div class="container">
		
		<ul class="footer-list">
			<li class="sponsor-box">
				<a class="footer-logo first-logo" href="http://www.alura.com.br">ALURA</a>
				<ul class="footer-sub-list">
					<li><a class="footer-link" href="http://www.alura.com.br">Cursos online de tecnologia</a></li>
				</ul>
			</li>
			<li class="sponsor-box">
				<a class="footer-logo second-logo" href="http://www.caelum.com.br">CAELUM</a>
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
				<a class="footer-logo third-logo" href="http://www.casadocodigo.com.br/">CASA DO CÓ“DIGO</a>
				<ul class="footer-sub-list">
					<li><a class="footer-link" href="http://www.casadocodigo.com.br">Conheça também os livros da Casa do Código</a></li>
				</ul>
			</li>
		<!-- <li class="sponsor-box"><a class="footer-logo online-logo" href="#">Caelum Online</a></li> -->
		</ul>
	</div>
	<div class="dark-footer">
		<ol class="main-tags main-tags-first">
			<%@include file="/tag_cloud.jsp" %>
		</ol>
		<ol class="main-tags main-tags-second">
			<li><a class="main-tag" href="http://www.lumpa.com.br/tag/photoshop">photoshop</a></li>
			<li><a class="main-tag" href="http://www.lumpa.com.br/tag/illustrator">illustrator</a></li>
			<li><a class="main-tag" href="http://www.lumpa.com.br/tag/inDesign">inDesign</a></li>
			<li><a class="main-tag" href="http://www.lumpa.com.br/tag/tipografia">tipografia</a></li>
			<li><a class="main-tag" href="http://www.lumpa.com.br/tag/vetorização">vetorização</a></li>
		</ol>
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
		<span class="footer-muted-message">Publicidade: guj@guj.com.br &#8212 Desenvolvido por <a class="footer-link" href="http://www.caelum.com.br">Caelum</a> &#8212 GUJ: desde 2002</span>
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
