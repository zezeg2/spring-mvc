<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-01-13
  Time: 오전 10:51
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
    <title>Login</title>
</head>
<body>
<%
    if (session.getAttribute("info")!= null)
%>
<form action="<%=request.getContextPath()%>/login" method="post">
    아이디 : <input type="text" id="id" name="id">
    패스워드 : <input type="password" id="pw" name="pw">
    <input type="submit">
</form>
</body>
</html>
