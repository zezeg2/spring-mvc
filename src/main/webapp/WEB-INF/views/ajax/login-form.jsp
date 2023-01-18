<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-01-18
  Time: 오후 1:27
  To change this template use File | Settings | File Templates.
--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
  $(document).ready(() => {
    $("#login").on('click', () => {
      $.ajax({
        url: 'login',
        type: 'post',
        data: {'id': $("#id").val(), 'pw': $("#pw").val()},
        dataType: 'json',
        success: (data) => {
          $("#login-process").html("<h1>" + data.process + "</h1>")
        },
        error: (request, status, error) => {
        }
      });
    })
    $("#info-btn").on('click', () => {
      $.ajax({
        url: 'info',
        type: 'post',
        data: {'id': $("#id").val(), 'pw': $("#pw").val()},
        dataType: 'json',
        success: (data) => {
          $("#member-info").html("<h1>" + data.id + "</h1>")
            .append("<h1>" + data.name + "</h1>")
            .append("<h1>" + data.email + "</h1>")
            .append("<h1>" + data.phone + "</h1>")
            .append("<h1>" + data.address + "</h1>")
        },
        error: (request, status, error) => {
          $("#member-info").html("<h1>login error</h1>")
        }
      });
    })
    $("#list-btn").on('click', () => {
      $.ajax({
        url: 'list',
        type: 'post',
        data: {'id': $("#id").val(), 'pw': $("#pw").val()},
        dataType: 'json',
        success: (data) => {
          $("#member-list").html("")
          data.forEach(particle => {
            $("#member-list").append("<h1>" + particle.id + "</h1>")
              .append("<h1>" + particle.name + "</h1>")
              .append("<h1>" + particle.email + "</h1>")
              .append("<h1>" + particle.phone + "</h1>")
              .append("<h1>" + particle.address + "</h1>")
              .append("---------------------------------------")
          })
        },
        error: (request, status, error) => {
          $("#member-info").html("<h1>login error</h1>")
        }
      });
    })
    $("#page-list-btn").on('click', () => {
      totalPage =
    })
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
<form action="<%=request.getContextPath()%>/login" method="post">
    아이디 : <input type="text" id="id" name="id">
    패스워드 : <input type="password" id="pw" name="pw">
    <input type="button" id="login" value="login">
</form>

<button id="info-btn">info</button>
<button id="list-btn">list</button>
<div id="login-process"></div>
<hr>
<div id="member-info"></div>
<hr>
<div id="member-list"></div>
</body>
</html>
