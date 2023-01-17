<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-01-17
  Time: 오전 11:06
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
<h1>전체 회원정보 조회</h1>
<ul>
    <c:forEach items="${memberList}" var="member">
        <li>아이디 : ${member.id} | 회원 이름 : ${member.name} | 이메일 : ${member.email} | 휴대전화 : ${member.phone} | 주소 : ${member.address} | 주소 : ${member.indate}</li>
    </c:forEach>
</ul>


<% for (int i = 1; i <= Integer.valueOf(request.getAttribute("totalPage").toString()); i++) { %>
<a href="?page=<%=i%>"><%=i%> |</a>
<%}%>
<a href="<%=request.getContextPath()%>/member-info">뒤로가기</a>

</body>
</html>
