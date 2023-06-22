<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.ListJobModel" %>
<%@ page import="Model.ProjectsModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/linkHeaderCSS.jsp"/>
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
                    <h4 class="page-title">Danh sách công việc</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                    <a href="<c:url value="/task/add"/>" class="btn btn-sm btn-success">Thêm mới</a>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <%--        announce--%>
            <div class="notification">
                <p>Announce:</p>
                <a href="#" style="display: none" class="notification-link">${message}</a>
                <strong>${message}</strong>
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
                                    <th>STT</th>
                                    <th>Tên Công Việc</th>
                                    <th>Dự Án</th>
                                    <th>Người Thực Hiện</th>
                                    <th>Ngày Bắt Đầu</th>
                                    <th>Ngày Kết Thúc</th>
                                    <th>Trạng Thái</th>
                                    <th>Hành Động</th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${listJobArrayList}" var="item" varStatus="loop">
                                    <tr>
                                        <td>${loop.index+1}</td>
                                        <td>${item.getJob().getJobName()}</td>
                                        <td>${item.getProject().getProjectName()}</td>
                                        <td>${item.getUsers().getFullName()}</td>
                                        <td>${item.getDateBegin()}</td>
                                        <td>${item.getDateEnd()}</td>
                                        <td>${item.getStatus().getStatusName()}</td>
                                        <td>
                                            <a href="<c:url value="/task/mod"/>?userId=${item.getUsers().getId()}&jobId=${item.getJob().getId()}&statusId=${item.getStatus().getId()}&projectId=${item.getProject().getId()}&methodCustom=get" class="btn btn-sm btn-primary">Sửa</a>
<%--                                            <a href="<c:url value="/task/delete"/>?userId=${item.getUsers().getId()}&jobId=${item.getJob().getId()}&statusId=${item.getStatus().getId()}&projectId=${item.getProject().getId()}&methodCustom=post" class="btn btn-sm btn-danger">Xóa</a>--%>
                                            <button userId="${item.getUsers().getId()}" jobId="${item.getJob().getId()}"statusId="${item.getStatus().getId()}" projectId="${item.getProject().getId()}"methodCustom="post" class="btn btn-sm btn-danger btn-delete-task" >Xoá</button>

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
        <jsp:include page="/linkJavaScript.jsp"/>
<script>
    $(document).ready(function () {
        $('#example').DataTable();
    });
</script>
<script src="<c:url value="/js/task.js"/> "></script>
</body>

</html>