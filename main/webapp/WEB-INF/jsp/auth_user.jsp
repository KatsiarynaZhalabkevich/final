<%@page import="by.epam.web.unit6.bean.User" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" errorPage="../../page/error.jsp" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />

    <fmt:message bundle="${loc}" key="local.mainpage" var="main"/>
    <fmt:message bundle="${loc}" key="local.registration" var="registr"/>
    <fmt:message bundle="${loc}" key="local.tarif" var="tarif"/>
    <fmt:message bundle="${loc}" key="local.logout" var="logout"/>
    <fmt:message bundle="${loc}" key="local.message1admin" var="mes1admin"/>
    <fmt:message bundle="${loc}" key="local.message2user" var="mes2user"/>
    <fmt:message bundle="${loc}" key="local.message3user" var="mes3user"/>
    <fmt:message bundle="${loc}" key="local.privpage" var="priv"/>
    <fmt:message bundle="${loc}" key="local.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="local.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="local.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.accountstat" var="account"/>
    <fmt:message bundle="${loc}" key="local.butteditinfo" var="edit"/>
    <fmt:message bundle="${loc}" key="local.balance" var="balance"/>
    <fmt:message bundle="${loc}" key="local.buttupdbalance" var="buttupdbalance"/>
    <fmt:message bundle="${loc}" key="local.message4user" var="mess4user"/>
    <fmt:message bundle="${loc}" key="local.nametarif" var="nametarif"/>
    <fmt:message bundle="${loc}" key="local.description" var="descrip"/>
    <fmt:message bundle="${loc}" key="local.price" var="price"/>
    <fmt:message bundle="${loc}" key="local.buttactivate" var="buttactiv"/>
    <fmt:message bundle="${loc}" key="local.message5user" var="mess5user"/>
    <fmt:message bundle="${loc}" key="local.date" var="date"/>
    <fmt:message bundle="${loc}" key="local.buttdeactiv" var="buttdeactiv"/>


    <link rel="stylesheet" href="../../lib/bootstrap/css/bootstrap.min.css">
    <script src="../../lib/js/jquery-3.4.1.slim.min.js"></script>
    <script src="../../lib/js/popper.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">
    <link href="../../lib/bootstrap/css/bootstrap.css" rel="stylesheet">

    <title>User private page</title>
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
            <li><a href="../../page/tarif.jsp">${tarif}</a></li>
            <li class="disabled"><a href="../../page/registr.jsp">${registr}</a></li>
            <li class="active"><a href="#">${priv}</a></li>
        </ul>
    </div>

    <div class="navbar-right">
        <form action="controller" method="get">
            <input type="hidden" name="command" value="logout"/>
            <input class="btn btn-success" type="submit" value="${logout}"/>
        </form>
    </div>


</nav>

<div class="jumbotron">
    <div class="container">
        <h1>${mes1admin}, ${user.getName()}!</h1>
        <p>${mes2user} </p>

    </div>
</div>

<div class="container">

    <div class="row">
        <div class="col-md-6">
            <h2>${mes3user}</h2>
            <p style="color: blue">${updateMessage}</p>
            <form>
                <div class="form-group">${login}: ${user.getLogin()}</div>
                <div class="form-group"> ${name}:
                    ${user.getName()}</div>
                <div class="form-group">${surname}:
                    ${user.getSurname()}</div>
                <div class="form-group">${phone}:
                    ${user.getPhone()}</div>
                <div class="form-group"> ${email}:
                    ${user.getEmail()} </div>
                <div class="form-group">${account}: ${user.isActive()}</div>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="edit_profile">
                    <input class="btn btn-success" type="submit" value="${edit}"/>
                </form>
            </form>
        </div>
        <div class="col-md-6">

            <form action="controller" method="post">
                <h2> ${balance}:</h2>
                <input type="hidden" name="command" value="update_balance"/>
                <p>${user.balance}</p>
                <input type="text" name="balance"/>
                <input class="btn btn-success" type="submit" name="send" value="${buttupdbalance}">
                <p style="color: blue">${updBalanceMessage}</p>
            </form>
            <br>

        </div>

    </div>
</div>
<div class="container">

    <div class="row">
        <div class="col-md-6">
            <h2>${mess4user}</h2>
            <c:if test="${tarifs!=null}">
                <table class="table table-striped">
                    <tr class="active">
                        <th>${nametarif}</th>
                        <th>${descrip}</th>
                        <th>${price}</th>
                        <th></th>
                    </tr>

                    <c:forEach var="tarifList" items="${tarifs}">
                        <tr>
                            <td> ${tarifList.name}</td>

                            <td>${tarifList.description}</td>

                            <td>${tarifList.price}</td>
                            <td>
                                <!--Как не переходить ни на какую страницу? заново отправлять эти тарифы, чтобы они не исчезали? -->
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="add_note">
                                    <input type="hidden" name="tarif_id" value="${tarifList.id}">
                                    <input type="submit" class="btn btn-info" value="${buttactiv}">
                                </form>
                            </td>

                        </tr>
                    </c:forEach>


                </table>
            </c:if>
        </div>
        <div class="col-md-6">

            <h2> ${mess5user}:</h2>
            <c:if test="${userTarifs!=null}">

                <table class="table-responsive table-striped">
                    <tr class="active">

                        <th>${nametarif}</th>
                        <th>${price}</th>
                        <th>${date}</th>
                        <th></th>
                    </tr>

                    <c:forEach var="tariffs" items="${userTarifs}">

                        <tr>
                            <!--простой счетчик-->
                            <td> ${tariffs.name}</td>
                            <td>${tariffs.price}</td>
                            <td>${tariffs.date}</td>

                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="delete_note">
                                    <input type="hidden" name="note_id" value="${tariffs.noteId}">
                                    <input type="submit" class="btn btn-info" value="${buttdeactiv}">
                                </form>
                            </td>
                        </tr>

                    </c:forEach>

                </table>
            </c:if>

            <br>

        </div>

    </div>
</div> <br><br>

<footer class="footer mt-auto py-3 ">
    <div class="container">
        <hr>
        <span class="text-muted">&copy; EPAM 2020</span>
        <hr>

    </div>

</footer>



</body>
</html>
