<<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="webjars/js/jquery-3.4.1.slim.min.js"></script>
    <script src="webjars/js/popper.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">


    <title>Error page</title>
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
            <li><form action="controller" method="get">
                <input type="hidden" name="command" value="show_tarifs">
                <input type="submit" value="Tariffs" class="btn-link">
                </form></li>
            <li><a href="registration">Registration</a></li>
            <li class="active"><a href="#">Error page </a></li>
        </ul>
    </div>
</nav>

<div class="jumbotron">
    <div class="container">
        <c:if test="${user!=null}">
            <h2>Hello, ${user.getName()}!</h2>
        </c:if>
        <p>You are at error page! Something goes wrong... We are looking for a problem.</p>
        <p style="color: red">${errorMessage}</p>
    </div>
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
