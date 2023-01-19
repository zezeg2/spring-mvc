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
    $("#upload-button").on('click', () => {
      let formData = new FormData($("#upload-form")[0]);
      $.ajax({
        url: 'upload',
        type: 'post',
        data: formData,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        dataType: 'json',
        success: (data) => {
          $("#upload-result").html(data)
        },
        error: (data) => {
          $("#upload-result").html("업로드 실패")
        }
      });
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

<form id="upload-form">
    업로드 파일 : <input type="file" id="file-input">
    <input type="button" id="upload-button" value="파일 업로드">
</form>
<div id="upload-result"></div>

<button id="info-btn">info</button>
<button id="list-btn">list</button>
<div id="login-process"></div>
<hr>
<div id="member-info"></div>
<hr>
<div id="member-list"></div>
</body>
</html>
