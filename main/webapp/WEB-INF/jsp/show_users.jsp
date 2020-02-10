<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
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
    <style>
        body {
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
            <li><a href="main">Main</a></li>
            <li><form action="controller" method="get">
                <input type="hidden" name="command" value="show_tarifs">
                <input type="submit" value="Tariffs" class="btn-link">
            </form></li>
            <li class="disabled"><a href="#">Registration</a></li>
            <li class="active"><a href="admin">Private page </a></li>
        </ul>
    </div>

    <div class="navbar-right">
        <form action="controller" method="get">
            <input type="hidden" name="command" value="logout"/>
            <input class="btn btn-success" type="submit" value="Log out"/>
        </form>
    </div>


</nav>

<div class="jumbotron">
    <div class="container">
        <h1>Hello, ${user.getName()}!</h1>
        <p>You are at admin's private page. Here you can manage user's accounts and tariffs of internet provider "My
            Telecom"! </p>

    </div>
</div>

<div class="container">

    <div class="row">
        <div class="col-md-12">
            <h2>Users</h2>
            <p>Here you can manage users' accounts: control balance, block/unblock or delete users!</p>
            <p>
                <div>
            <p style="color: #4cae4c">${updBalanceMessage}</p>
            <c:if test="${usersList!= null}">
                <table class="table-responsive table-striped table-bordered">
                    <tr class="active">
                        <th>№</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Balance, $</th>
                        <th>Active</th>
                        <th>Tariffs</th>
                        <th></th>
                    </tr>
                    <c:forEach var="users" items="${usersList}">
                        <tr>
                            <td> ${users.id}</td>

                            <td>${users.name}</td>

                            <td>${users.surname}</td>
                            <td>${users.phone}</td>

                            <td>${users.email}</td>

                            <td>
                                <form action="controller" method="post">
                                        ${users.balance}<br>
                                    <input type="hidden" name="command" value="change_balance">
                                    <input type="hidden" name="user_id" value="${users.id}">
                                    <input type="hidden" name="old_balance" value="${users.balance}">
                                    <input type="text" name="balance" value=""><br>
                                    <input type="submit" class="btn btn-info btn-md" name="upd" value="Update Balance">
                                </form>
                            </td>
                            <td>

                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="change_status">
                                    <input type="hidden" name="user_id" value="${users.id}">
                                    <c:choose>
                                        <c:when test="${users.active=='true'}">
                                            <input type="radio" class="radio-button" name="active" value="active"
                                                   checked>active</input>
                                            <input type="radio" сlass="radio-button active" name="active"
                                                   value="blocked">blocked</input>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" class="radio-button" name="active" value="active">active</input>
                                            <input type="radio" сlass="radio-button active" name="active"
                                                   value="blocked"
                                                   checked>blocked</input>
                                        </c:otherwise>
                                    </c:choose>

                                    <input type="submit" class="btn btn-info btn-md" name="" value="Update Status">
                                </form>
                            </td>

                            <td>

                                <form action="controller" method="get" >
                                    <input type="hidden" name="command" value="show_user_tarif">
                                    <input type="hidden" name="user_id" value="${users.id}">
                                    <input type="submit" class="btn btn-info btn-md" value="User's tariffs">
                                </form>

                            </td>

                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="delete_user">
                                    <input type="hidden" name="user_id" value="${users.id}">
                                    <input type="submit" class="btn btn-danger btn-xs" name="" value="Delete user">
                                </form>

                            </td>

                        </tr>
                    </c:forEach>
                </table>

            </c:if>

        </div>


    </div>
    <div class="row">
        <div class="col-md-12">
            <c:if test="${tariffs!=null}">
            <h2>User's tariffs</h2>

            <table class="table table-striped">
                <tr class="active">
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>

                </tr>

                <c:forEach var="tarifList" items="${tariffs}">
                    <tr>
                        <td> ${tarifList.name}</td>

                        <td>${tarifList.description}</td>

                        <td>${tarifList.price}</td>

                    </tr>
                </c:forEach>

            </table>
            </c:if>
        </div>
    </div>

</div>
<br>
<br><br>


<footer class="footer mt-auto py-3">
    <div class="container">
        <hr>
        <span class="text-muted">&copy; EPAM 2020</span>
        <hr>

    </div>

</footer>
<script src="webjars/jquery-1.10.2.min.js"></script>
<script src="webjars/dist/js/bootstrap.min.js"></script>

</body>
</html>