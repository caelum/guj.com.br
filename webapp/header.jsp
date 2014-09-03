<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="content-type" content="text/html;charset=ISO-8859-1" />
<meta name="description" content="GUJ - O portal de desenvolvedores do Brasil">

<meta property="og:title" content="GUJ - O portal de desenvolvedores do Brasil">
<meta property="og:site_name" content="guj.com.br">
<meta property="og:url" content="http://www.guj.com.br">
<meta property="og:type" content="forum">
<meta property="og:image" content="<c:url value="/imgs/guj-logo.png"/>">

<c:if test="${empty rmSection}">
	<c:set var="rmSection" value="guj/internas"/>
</c:if>

<c:if test="${empty title}">
	<c:set var="title">O portal de desenvolvedores do Brasil</c:set>
</c:if>

<title>GUJ - ${title}</title>
<link href="<c:url value="/stylesheets/guj3.css?20130424"/>" media="screen" rel="stylesheet" type="text/css" />

<script type='text/javascript'>
var googletag = googletag || {};
googletag.cmd = googletag.cmd || [];
(function() {
    var gads = document.createElement('script');
    gads.async = true;
    gads.type = 'text/javascript';
    var useSSL = 'https:' == document.location.protocol;
    gads.src = (useSSL ? 'https:' : 'http:') + 
    '//www.googletagservices.com/tag/js/gpt.js';
    var node = document.getElementsByTagName('script')[0];
    node.parentNode.insertBefore(gads, node);
})();
</script>

<script type='text/javascript'>
googletag.cmd.push(function() {
    googletag.defineSlot('/102249611/banner-direito', [300, 250], 'div-gpt-ad-1409592167217-0').addService(googletag.pubads());
    googletag.defineSlot('/102249611/banner-topo', [728, 90], 'div-gpt-ad-1409775796804-0').addService(googletag.pubads());
    googletag.pubads().enableSingleRequest();
    googletag.enableServices();
});
</script>

<!--[if IE]>
<link href="<c:url value="/stylesheets/guj3-ie.css?20090222"/>" media="screen" rel="stylesheet" type="text/css" />
<![endif]-->
<script type="text/javascript" src="<c:url value="/javascripts/jquery-1.3.1.min.js?20090222"/>"></script>
<script type="text/javascript" src="<c:url value="/javascripts/jquery.dimensions.min.js?20090222"/>"></script>
<script type="text/javascript">
$().ready(function() {
	$("#returnPath").val(document.location);
});
</script>
</head>

<body>
  <div id="header">
    <div class="container1">
        <!-- banner-topo -->
        <div class="headerBanner" id='div-gpt-ad-1409779595469-0'>
            <script type='text/javascript'>
                googletag.cmd.push(function() { googletag.display('div-gpt-ad-1409779595469-0'); });
            </script>
        </div>
      <div class="container2">
        <a href="<c:url value="/"/>" id="logo" data-slogan="${title}">GUJ</a>
      </div>
    </div>
  </div>
