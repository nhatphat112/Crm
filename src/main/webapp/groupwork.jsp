
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.ProjectsModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

<head>
        <jsp:include page="/linkHeaderCSS.jsp"/>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>


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
                        <h4 class="page-title">Danh sách dự án</h4>
                    </div>


                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                        <a href="<c:url value="/groupwork/add"/>" class="btn btn-sm btn-success">Thêm mới</a>
                    </div>

                    <!-- /.col-lg-12 -->
                </div>
                <!-- /row -->
                <div class="notification">
                    <p>Announce:</p>
                    <a href="#" style="display: none" class="notification-link">${message}</a>
                    <strong class="notification-content">${message}</strong>
                    <button class="close-btn">Close</button>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <div class="table-responsive">
                                <table class="table" id="example">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên Dự Án</th>
                                            <th>Tên Trưởng Nhóm</th>
                                            <th>Ngày Bắt Đầu</th>
                                            <th>Ngày Kết Thúc</th>
                                            <th>Hành Động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${projectModelArrayList}" var="item" varStatus="loop">
                                            <tr>
                                                <td>${loop.index+1}</td>
                                                <td>${item.getProjectName()}</td>
                                                <td>${item .getUser().getFullName()}</td>
                                                <td>${item.getDateBegin()}</td>
                                                <td>${item.getDateEnd()}</td>
                                                <td>
                                                    <div style="display: inline-block">
                                                        <form action="<c:url value= "/groupwork/mod"/>" method="post">
                                                            <input type="hidden" name ="id" value="${item.getId()}">
                                                            <input type="hidden" name ="methodCustom" value="get">
                                                            <input type="hidden" name ="userProjectId" value="${item.getUser().getId()}">


                                                            <button class="btn btn-sm btn-primary" type="submit">Sửa</button>
                                                        </form>
                                                    </div>
<%--                                                    <a href="" class="btn btn-sm btn-danger">Xóa</a>--%>
                                                   <div style="display: inline-block">
                                                           <button projectId ="${item.getId()}" userId="${item.getUser().getId()}"  class="btn btn-sm btn-danger btn-delete-groupwork" type="submit">Xóa</button>

                                                   </div>
                                                    <a href="<c:url value="/groupwork/details"/>?projectId=${item.getId()}&userProjectId=${item.getUser().getId()}" class="btn btn-sm btn-info">Xem</a>
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
            <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
<%--    <c:url value="linkJavaScript.jsp"/>--%>
   <jsp:include page="/linkJavaScript.jsp"/>

    <script>
        $(document).ready(function () {
            $('#example').DataTable();
        });
    </script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="<c:url value="/js/groupwork.js"/> "></script>



</body>

</html>