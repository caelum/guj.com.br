<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<c:url value="mostViewedJavaTopics/list.java"/>">
		Notícias mais vistas
	</a>
	<br/>
	<br/>
	
	<a href="<c:url value="mostRepliedJavaTopics/list.java"/>">
		Notícias mais comentadas
	</a>
	<br/>
	<br/>
	
	<a href="<c:url value="mostRepliedNonJavaTopics/list.java"/>">
		Tópicos mais comentados
	</a>
	<br/>
	<br/>
	
	<a href="<c:url value="mostViewedNonJavaTopics/list.java"/>">
		Tópicos mais vistos
	</a>
</body>
</html>