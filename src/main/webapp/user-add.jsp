<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
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
        <jsp:include page="/navigation.jsp"></jsp:include>
        <!-- Left navbar-header -->

        <!-- Left navbar-header end -->
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Thêm mới thành viên</h4>
                    </div>
                </div>
                <div class="notification">
                    <p>Announce:</p>
                    <a href="#" style="display: none" class="notification-link">${message}</a>
                    <strong class="notification-content">${message}</strong>
                    <button class="close-btn">Close</button>
                </div>
                <!-- /.row -->
                <!-- .row  -->
                <div class="row">

                    <div class="col-md-2 col-12"> </div>
                    <div class="col-md-8 col-xs-12">
                        <div class="white-box">

                            <form class="form-horizontal form-material" enctype="multipart/form-data" action=<c:url value="/user/add" />  method="post">
                                <div class="form-group">
                                    <label class="col-md-12">Full Name</label>
                                    <div class="col-md-12">
                                        <input name="fullName" value="${fullName}" required type="text" placeholder="Johnathan Doe"
                                            class="form-control form-control-line"> </div>
                                </div>
                                <div class="form-group">
                                    <label for="example-email" class="col-md-12">Email</label>
                                    <div class="col-md-12">
                                        <input name="email" value="${email}" required type="email" placeholder="johnathan@admin.com"
                                            class="form-control form-control-line" name="example-email"
                                            id="example-email"> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Password</label>
                                    <div class="col-md-12">
                                        <input type="password" name="password" required placeholder="*******" value="${password}" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Phone No</label>
                                    <div class="col-md-12">
                                        <input name="phone" value="${phone}" required type="number" placeholder="123 456 7890"
                                            class="form-control form-control-line"> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12">Select Country</label>
                                    <div class="col-sm-12">
                                        <select name="country" value="${password}" required class="form-control form-control-line">
                                            <option value="London">London</option>
                                            <option value="India">India</option>
                                            <option value="Usa">Usa</option>
                                            <option value="Canada">Canada</option>
                                            <option value="Thailand">Thailand</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12">Select Role</label>
                                    <div class="col-sm-12">
                                        <select name="roleId" class="form-control form-control-line">
                                            <c:forEach var="item" items="${roleList}">
                                                <option value="${item.getId()}">${item.getDescription()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button type="submit" class="btn btn-success">Add User</button>
                                        <a href="<c:url value="/user"/>" class="btn btn-primary">Quay lại</a>
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

    <jsp:include page="linkJavaScript.jsp"/>


</body>

</html>