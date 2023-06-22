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
    <div class="notification">
        <p>Announce:</p>
        <a href="#" style="display: none" class="notification-link">${message}</a>
        <strong>${message}</strong>
        <button class="close-btn">Close</button>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Thêm mới công việc</h4>
                </div>
            </div>




            <!-- /.row -->
            <!-- .row -->
            <div class="row">
                <div class="col-md-2 col-12"></div>
                <div class="col-md-8 col-xs-12">
                    <div class="white-box">
                        <form method="post" action="<c:url value="/task/add"/>" class="form-horizontal form-material">
                            <div class="form-group">
                                <label class="col-md-12">Dự án</label>
                                <div class="col-md-12">
                                    <select name="projectId" class="form-control form-control-line">
                                        <c:forEach items="${projectList}" var="item">
                                            <option value="${item.getId()}">${item.getProjectName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Tên công việc</label>
                                <div class="col-md-12">
                                    <input required name="jobName" type="text" value="${jobName}" placeholder="Tên công việc"
                                           class="form-control form-control-line">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Người thực hiện</label>
                                <div class="col-md-12">
                                    <select name="userId" class="form-control form-control-line">
                                        <c:forEach items="${userList}" var="item">
                                            <option value="${item.getId()}">${item.getFullName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày bắt đầu</label>
                                <div class="col-md-12">
                                    <input name="dateBegin" required value="${dateBegin}" type="text" placeholder="dd/MM/yyyy"
                                           class="form-control form-control-line">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày kết thúc</label>
                                <div class="col-md-12">
                                    <input name="dateEnd" required value="${dateEnd}" type="text" placeholder="dd/MM/yyyy"
                                           class="form-control form-control-line">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Trạng thái công việc</label>
                                <div class="col-md-12">
                                    <select name="statusId" class="form-control form-control-line">
                                        <c:forEach items="${statusList}" var="item">
                                            <option value="${item.getId()}">${item.getStatusName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn-success">Lưu lại</button>
                                    <a href="<c:url value="/task"/>" class="btn btn-primary">Quay lại</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-2 col-12"></div>
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

</body>

</html>