<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-01-13
  Time: 오후 3:17
  To change this template use File | Settings | File Templates.
--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
  $(document).ready(() => {

  });
</script>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>index 페이지</h2>
<h3>
    <ul>
        <li><a href="<%= request.getContextPath()%>/login">로그인</a></li>
        <li><a href="<%= request.getContextPath()%>/member-list?page=1">회원 전체 조회(로그인 필요없음)</a></li>
        <li><a href="<%= request.getContextPath()%>/register">회원가입(로그인 필요없음)</a></li>
    </ul>
</h3>
</body>
</html>
