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
            padding: 5%;
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
            <li class="active"><a href="#" >Tariffs</a></li>
            <li class="disabled"><a href="#">Registration</a></li>
            <li ><a href="admin">Private admin's page </a></li>
            <li ><a href="auth_user">Private user's page </a></li>
        </ul>
    </div>

    <div class="navbar-right">
        <form action="controller" method="get">
            <input type="hidden" name="command" value="logout"/>
            <input class="btn btn-success" type="submit" value="Log out"/>
        </form>
    </div>


</nav>

<div class="container">


</div>
<div class="jumbotron">
    <div class="container">

        <p>The list of actual tariffs for Internet. You can manage tariffs! </p>

    </div>
</div>


<form action="controller" method="get">
    <input type="hidden" name="command" value="show_tarifs">
    <div class="table-responsive">
        <table class="table table-striped table-condensed table-responsive">
            <tr class="active">
                <th>#</th>
                <th>Name</th>
                <th>Description</th>
                <th>Speed, Mbit/s</th>
                <th>Price, $</th>
                <th>Discount</th>
                <th></th>
            </tr>



            <tr>

                    <td>${tarifList.id}</td>
                    <td><input type="text" class="form-control" name="name" value="${tarifList.name}"/></td>
                    <td><input type="text" class="form-control" name="description" value="${tarifList.description}"/></td>
                    <td><input type="text" class="form-control" name="speed" value=" ${tarifList.speed}"/></td>
                    <td><input type="text" class="form-control" name="price" value="${tarifList.price}"/></td>
                    <td><input type="text" class="form-control" name="discount" value="${tarifList.discount}"/></td>

                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="delete_tarif">
                            <input type="hidden" name="tarif_id" value="${tarifList.id}">
                            <input type="submit" class="btn btn-md btn-danger" value="Delete">
                        </form>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="edit_tarif">
                            <input type="submit" class="btn btn-md btn-info" value="Edit">
                        </form>
                    </td>

            </tr>


            <tr>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="add_tarif">
                    <td></td>
                    <td>  <input type="text" placeholder="Name" class="form-control" name="name"/></td>
                    <td>  <input type="text" placeholder="Description" class="form-control" name="description"/></td>
                    <td>  <input type="text" placeholder="Speed" class="form-control" name="speed"/></td>
                    <td>  <input type="text" placeholder="Price" class="form-control" name="price"/></td>
                    <td>  <input type="text" placeholder="Discount" class="form-control" name="discount"/></td>
                    <td>   <input type="submit" class="btn btn-md btn-success" value="Add tariff"></td>
                </form>
            </tr>
        </table>
    </div>
</form>


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
