<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>




<%--
  Created by IntelliJ IDEA.
  User: peanhatphat
  Dates: 18/04/2023
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container mt-3">
        <h1>QUẢN LÝ SẢN PHẨM</h1>
        <form action="product" method="post">
            <div class="form-group">
                <label>Tên Sản Phẩm</label>
                <input type="text" class="form-control" name="productName"required>
            </div>
            <div class="form-group">
                <label>Số Lượng</label>
                <input type="number" class="form-control" name="quantity"required>
            </div>
            <div class="form-group">
                <label>Giá bán</label>
                <input type="number" class="form-control" name="price" required>
            </div>
            <button type="submit" class="btn btn-primary">Lưu lại</button>
        </form>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Tên Sản Phẩm</th>
            <th scope="col">Số Lượng</th>
            <th scope="col">Giá Bán</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach varStatus="loop" var="item" items="${listProduct}">
            <tr>
                <th scope="row">${loop.index+1}</th>
                <td>${item.getProductName()}</td>
                <td>${item.getQuantity()}</td>
                <td>${item.getPrice()}</td>
            </tr>


        </c:forEach>


        </tbody>
    </table>

</body>
</html>
