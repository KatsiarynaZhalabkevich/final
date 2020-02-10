<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="webjars/js/jquery-3.4.1.slim.min.js"></script>
    <script src="webjars/js/popper.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">

    <title>Tariffs page</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">

    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a href="main" class="navbar-brand">My Telecom</a>
    </div>
    <div>
        <ul class="nav navbar-nav">
            <li><a href="main">Main</a></li>
            <li class="active"><a href="#">Tariffs</a></li>
            <li class=""><a href="registration">Registration</a></li>
            <li class="disabled"><a href="#">Private page </a></li>
        </ul>
    </div>
<c:if test="${user!=null}">
    <div class="navbar-right">
        <form action="controller" method="get">
            <input type="hidden" name="command" value="logout"/>
            <input class="btn btn-success" type="submit" value="Log out"/>
        </form>
    </div>
</c:if>

</nav>
<div class="jumbotron">
    <div class="container">
        <br>

        <p>The list of actual tariffs for Internet. You can join us anytime! </p>

    </div>
</div>

    <div class="table-responsive">
        <table class="table table-striped">
            <tr class="active">
                <th>#</th>
                <th>Name</th>
                <th>Description</th>
                <th>Speed</th>
                <th>Price</th>
                <th>Discount</th>
                <th></th>
            </tr>

            <c:forEach var="tarifList" items="${tarifs}">
            <tr>
                <td>${tarifList.id}</td>
                <td> ${tarifList.name}</td>

                <td>${tarifList.description}</td>
                <td>${tarifList.speed}</td>
                <td>${tarifList.price}</td>
                <td>${tarifList.discount}</td>
                </c:forEach>
        </table>
    </div>



<footer class="footer mt-auto py-3 navbar-fixed-bottom">
    <div class="container">
        <hr>
        <span class="text-muted">&copy; EPAM 2020</span>
        <hr>
        <br>
        <br>
    </div>

</footer>
<script src="webjars/jquery-1.10.2.min.js"></script>
<script src="webjars/dist/js/bootstrap.min.js"></script>
</body>
</html>
