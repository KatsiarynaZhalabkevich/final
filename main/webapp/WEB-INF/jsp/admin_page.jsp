<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="../../page/error.jsp"  %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />

    <fmt:message bundle="${loc}" key="local.mainpage" var="main"/>
    <fmt:message bundle="${loc}" key="local.registration" var="registr"/>
    <fmt:message bundle="${loc}" key="local.tarif" var="tarif"/>
    <fmt:message bundle="${loc}" key="local.logout" var="logout"/>
    <fmt:message bundle="${loc}" key="local.message1admin" var="mes1admin"/>
    <fmt:message bundle="${loc}" key="local.message2admin" var="mes2admin"/>
    <fmt:message bundle="${loc}" key="local.buttontarif" var="buttarif"/>
    <fmt:message bundle="${loc}" key="local.privpage" var="priv"/>
    <fmt:message bundle="${loc}" key="local.butuser" var="butuser"/>
    <fmt:message bundle="${loc}" key="local.message3admin" var="mes3admin"/>
    <fmt:message bundle="${loc}" key="local.message4admin" var="mes4admin"/>
    <fmt:message bundle="${loc}" key="local.message5admin" var="mes5admin"/>



    <link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="webjars/js/jquery-3.4.1.slim.min.js"></script>
    <script src="webjars/js/popper.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <style>
        body{
            padding: 5px;
        }
    </style>

    <title>Admin private page</title>
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
                <li><a href="main">${main}</a></li>
                <li><form action="controller" method="get">
                    <input type="hidden" name="command" value="show_tarifs">
                    <input type="submit" value="Tariffs" class="btn-link">
                </form></li>
                <li class="disabled"><a href="#">${registr}</a></li>
                <li class="active"><a href="#">${priv} </a></li>
            </ul>
        </div>

        <div class="navbar-right">
            <form  action="controller" method="get">
                <input type="hidden" name="command" value="logout"/>
                <input class="btn btn-success" type="submit" value="${logout}"/>
            </form>
        </div>


</nav>

<div class="jumbotron">
    <div class="container">
        <h1>${mes1admin}, ${user.getName()}!</h1>
        <p>${mes2admin} </p>

    </div>
</div>

<div class="container">

    <div class="row">
        <div class="col-md-6">
            <h2>${mes3admin}</h2>
            <p>${mes4admin}</p>
            <p>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="show_users"/>
                <input class="btn btn-info btn-md" type="submit" value="${butuser} &raquo;"/>

            </form>

            </p>
        </div>
        <div class="col-md-6">
            <h2>${tarif}</h2>
            <p>${mes5admin}</p>
            <p>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="show_tarifs"/>
                <input class="btn btn-info btn-md" role="button" type="submit" name="show tarifs"
                       value="${buttarif}&raquo;"/>
            </form>
            </p>
        </div>

    </div>
</div>
<br>
<br>

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
