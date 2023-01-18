<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-01-18
  Time: 오전 11:13
  To change this template use File | Settings | File Templates.
--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
  $(document).ready(() => {

  });
</script>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>다운로드 파일 리스트</h2>
<ul>
    <c:forEach items="${list}" var="file" >
        <li><h3><a href="file-download?file=${file}">${file}</a></h3></li>
    </c:forEach>
</ul>

</body>
</html>
