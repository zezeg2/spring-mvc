<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-01-18
  Time: 오전 10:05
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
<form action="file-upload" method="post" enctype="multipart/form-data">
    작성자 : <input type="text" name="name">
    설명 : <input type="text" name="description">
    파일1 : <input type="file" name="file1">
    파일2 : <input type="file" name="file2">
    <input type="submit" value="파일전송">
</form>
</body>
</html>
