<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-01-17
  Time: 오후 5:12
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
<h2>회원 탈퇴</h2>
<form action="<%=request.getContextPath() %>/member-delete"  method="post">
    암호: <input type="password" name="pw"><br>
    <input type="submit" value="탈퇴 확인">
</form>
</body>
</html>
