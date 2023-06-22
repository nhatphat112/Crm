<%--
  Created by IntelliJ IDEA.
  User: peanhatphat
  Dates: 25/04/2023
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<nav class="navbar navbar-default navbar-static-top m-b-0">
  <div class="navbar-header">
    <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse">
      <i class="fa fa-bars"></i>
    </a>
    <div class="top-left-part">
      <a class="logo" href=<c:url value="/dashboard"/> >
        <b>
          <img src=<c:url value="/plugins/images/pixeladmin-logo.png"/> alt="home" />
        </b>
        <span class="hidden-xs">
                                <img src=<c:url value="/plugins/images/pixeladmin-text.png"/> alt="home" />
                            </span>
      </a>
    </div>
    <ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
      <li>
        <form role="search" class="app-search hidden-xs">
          <input type="text" placeholder="Search..." class="form-control">
          <a href="">
            <i class="fa fa-search"></i>
          </a>
        </form>
      </li>
    </ul>
    <ul class="nav navbar-top-links navbar-right pull-right">
      <li>
        <div class="dropdown">
          <a class="profile-pic dropdown-toggle" data-toggle="dropdown" href="#">
            <img  src=<c:url value="/plugins/images/users/genu.jpg"/> alt="user-img" width="34" class="img-circle" />
            <b class="hidden-xs">Cybersoft</b>
            <b></b>
          </a>
          <ul class="dropdown-menu">
            <li><a href="#">Xin Chào, ${username}</a></li>
            <li><a href=<c:url value="/profile"/>>Thông tin cá nhân</a></li>
            <li class="divider"></li>
            <li><a href=<c:url value="/profile/logout"/> "">Đăng xuất</a></li>

          </ul>
        </div>
      </li>
    </ul>
  </div>
  <!-- /.navbar-header -->
  <!-- /.navbar-top-links -->
  <!-- /.navbar-static-side -->
</nav>
</html>
