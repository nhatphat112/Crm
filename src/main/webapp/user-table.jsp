<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="linkHeaderCSS.jsp"/>
</head>

<body>
<!-- Preloader -->
<div class="preloader">
    <div class="cssload-speeding-wheel"></div>
</div>
<div id="wrapper">
    <!-- Navigation -->
    <jsp:include page="navigation.jsp"></jsp:include>
    <!-- /.navbar-header -->
    <!-- /.navbar-top-links -->
    <!-- /.navbar-static-side -->
    </nav>
    <!-- Left navbar-header -->
    <jsp:include page="left-navbar-header.jsp"></jsp:include>
    <!-- Left navbar-header end -->
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Danh sách thành viên</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                    <a href="<c:url value="/user/add"/>" class="btn btn-sm btn-success">Thêm mới</a>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <%--        announce--%>
            <div class="notification">
                <p>Announce:</p>
                <a href="#" style="display: none" class="notification-link">${message}</a>
                <strong class="notification-content">${message}</strong>
                <button class="close-btn">Close</button>
            </div>
            <!-- /row -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <div class="table-responsive">
                            <table class="table" id="example">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Full Name</th>
                                    <th>Email</th>
                                    <th>Role</th>
                                    <th>Country</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${userList}" var="item">
                                    <tr>
                                        <td>${item.getId()}</td>
                                        <td>${item.getFullName()}</td>
                                        <td>${item.getEmail()}</td>
                                        <td>${item.getRolesModel().getName()}</td>
                                        <td>${item.getCountry()}</td>
                                        <td>
                                            <div >
                                                <form action=
                                                          <c:url value="/user/mod"/>  method="post">
                                                    <input type="hidden" value="${item.getId()}" name="id">
                                                    <input type="hidden" value="get" name="methodCustom">
                                                    <input type="hidden" value="toDoGet" name="to">
                                                    <button type="submit" class="btn btn-sm btn-primary">Sửa</button>
                                                </form>
                                            </div>
                                            <div class="">
                                                <button userId="${item.getId()}"
                                                        class="btn btn-sm btn-danger btn-delete-user">Xóa
                                                </button>
                                            </div>
                                            <div class="">
                                                <form action=
                                                          <c:url value="/user/details"/> method="post">
                                                    <input type="hidden" value="${item.getId()}" name="id">
                                                    <button type="submit" class="btn btn-sm btn-info">Xem</button>
                                                </form>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
        <footer class="footer text-center"> 2018 &copy; myclass.com</footer>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<!-- jQuery -->
<jsp:include page="linkJavaScript.jsp"></jsp:include>
<script src="<c:url value="/js/user-table.js"/> "></script>

<script>
    $(document).ready(function () {
        $('#example').DataTable();
    });
</script>
</body>

</html>