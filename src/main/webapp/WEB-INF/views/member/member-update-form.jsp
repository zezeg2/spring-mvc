<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-01-17
  Time: 오후 3:00
  To change this template use File | Settings | File Templates.
--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
  $(document).ready(() => {
    $("#update-btn").onclick()
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
<h1>회원정보 수정</h1>
<form action="<%=request.getContextPath() %>/register"  method="post">
    <input type="hidden" name="id" value="${sessionScope.auth.id()}"><br>
    암호: <input type="password" id="pw" name="pw" value="${prevInfo.pw()}"><br>
    이메일: <input type="email" id="email" name="email" value="${prevInfo.email()}"><br>
    폰번호: <input type="tel" id="phone" name="phone"value="${prevInfo.phone()}"><br>
    주소: <input type="text" id="address" name="address"value="${prevInfo.address()}"><br>
    <input id="update-btn" type="submit" value="수정하기">
</form>
</body>
</html>
