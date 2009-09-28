<div class="right-bar">
  <div class="articles rounded">
    <h2><a href="<c:url value="article.list.logic"/>">Artigos</a></h2>
    <ul>
      <c:forEach items="${articlesBox}" var="article">
        <li>
          <c:choose>
          <c:when test="${article.exclusive }">
            <span class="post"><a href="<c:url value="/article/show?id=${article.id}"/>">${article.title}</a></span>
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
      </c:forEach>
    </ul>
  </div>
  <br/><br/>
  <div class="posts rounded">
    <h2>Posts de Blogs</h2>
    <ul>
      <c:forEach items="${postsBox }" var="post">
        <li>
          <span class="post"><a href="${post.link }" target="_blank">${post.title}</a></span>
          <span class="data">em <span class="blog">${post.blog.title}</span>, <fmt:formatDate pattern="dd/MM/yyyy" value="${post.date}"/></span>
          <c:if test="${not empty post.summary}">
            <br/>
            <span class="summary">${post.summary}</span>
          </c:if>
        </li>
      </c:forEach>
    </ul>
  </div>
</div>