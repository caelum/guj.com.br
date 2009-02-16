<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../header.jsp" %>

<div class="articles box rounded intern">
  <h2><a href="#">Artigos</a></h2>
  
  <ul>
    <c:forEach items="${articles}" var="article">
      <li>
        <c:choose>
          <c:when test="${article.exclusive}">
            <span class="post"><a href="<c:url value="/article.show.logic?id=${article.id}"/>">${article.title}</a></span>
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
      <br/>
      </li>
      
      <br>
    </c:forEach>
  </ul>
</div>

<%@ include file="../footer.jsp" %>