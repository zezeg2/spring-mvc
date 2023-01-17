<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-01-13
  Time: 오전 11:39
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
<h1>나의 회원관리 프로그램입니다.</h1>
<h2>${info}</h2>
<h3>
    <ul>
        <li><a href="<%= request.getContextPath()%>/logout">로그아웃</a></li>
        <li><a href="<%= request.getContextPath()%>/member-list?page=1">회원 전체 조회(로그인 필요없음)</a></li>
        <li><a href="<%= request.getContextPath()%>/member-update">회원정보 수정</a></li>
        <li><a href="<%= request.getContextPath()%>/member-delete">회원 탈퇴</a></li>
    </ul>
</h3>
</body>
</html>
