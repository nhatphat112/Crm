<%@ page import="Model.UsersModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href=<c:url value="/css/customStyle.css"/> rel="stylesheet">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container">
  <div class="row mt-5">
    <div class="col-md-5 m-auto mt-5">
      <h3 class="text-center">ĐĂNG NHẬP HỆ THỐNG</h3>
<%--        message --%>
        <%--        announce--%>
       <c:if test="${not empty message}">
           <div class="notification">
               <p>Announce:</p>
               <a href="#" style="display: none" class="notification-link">${message}</a>
               <strong>${message}</strong>
               <button class="close-btn">Close</button>
           </div>
       </c:if>
      <div class="p-4 border mt-4">
        <form action="<c:url value="/profile/login"/> " method="post">
            <div class="form-group">
              <label>Email</label>
              <input  type="email" class="form-control" name="email" value="${email}" required>
            </div>
            <div class="form-group">
              <label>Mật khẩu</label>
              <input type="password" class="form-control" name="password" value="${password}" required>
            </div>
            <button type="submit" class="btn btn-primary">Đăng nhập</button>
          </form>
      </div>
      </div>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<%-- Import các tệp script của Bootstrap --%>
<script src=<c:url value= "/js/announces.js"/>></script>

</body>
</html>
