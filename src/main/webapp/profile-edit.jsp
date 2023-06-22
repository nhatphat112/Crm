<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="linkHeaderCSS.jsp"></jsp:include>
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
                        <h4 class="page-title">Sửa mới công việc</h4>
                    </div>
                </div>
                <%--        announce--%>
                <div class="notification">
                    <p>Announce:</p>
                    <a href="#" style="display: none" class="notification-link">${message}</a>
                    <strong>${message}</strong>
                    <button class="close-btn">Close</button>
                </div>
                <!-- /.row -->
                <!-- .row -->
                <div class="row">
                    <div class="col-md-2 col-12"></div>
                    <div class="col-md-8 col-xs-12">
                        <div class="white-box">
                            <form action="<c:url value="/profile/mod"/> " method="get" class="form-horizontal form-material">
                                <div class="hidden">
                                    <input value="${workOn.getUsers().getId()}" name="userId">
                                    <input value="${workOn.getStatus().getId()}" name="statusId">
                                    <input value="post" name="methodCustom">


                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Tên dự án</label>
                                    <div class="col-md-12">
                                        <input type="text"  readonly value="${workOn.getProjects().getProjectName()}" class="form-control form-control-line">
                                        <input type="hidden" name="projectId" readonly value="${workOn.getProjects().getId()}" class="form-control form-control-line">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Tên công việc</label>
                                    <div class="col-md-12">
                                        <input type="text" readonly value="${workOn.getJobs().getJobName()}" class="form-control form-control-line">
                                        <input type="hidden" name="jobId" readonly value="${workOn.getJobs().getId()}" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Ngày bắt đầu</label>
                                    <div class="col-md-12">
                                        <input type="text"  readonly value="${workOn.getDateBegin()}" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Ngày kết thúc</label>
                                    <div class="col-md-12">
                                        <input type="text" readonly value="${workOn.getDateEnd()}" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Trạng thái</label>
                                    <div class="col-md-12">
                                        <select name="statusIdUpdate" class="form-control form-control-line">
                                            <c:forEach items="${statusList}" var="item">
                                                <option value="${item.getId()}" >${item.getStatusName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button type="submit" class="btn btn-success">Lưu lại</button>
                                        <a href="<c:url value="/profile"/> " class="btn btn-primary">Quay lại</a>
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
            <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <jsp:include page="linkJavaScript.jsp"/>
</body>

</html>