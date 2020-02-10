<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
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
    <style>
        body{
            padding: 5%;
        }
    </style>

    <title>Edit page</title>
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
            <li class="active"><a href="#">Edit page </a></li>
        </ul>
    </div>
    <div class="navbar-right">
        <form action="controller" method="get">
            <input type="hidden" name="command" value="logout"/>
            <input class="btn btn-success" type="submit" value="Log out"/>
        </form>
    </div>
</nav>
<br>

<div class="jumbotron">
    <div class="container">
        <h1>Welcome to our community!</h1>
        <p>Please fill in the form to register. After registration you would manage your account: choose tariffs of internet provider "My
            Telecom"! </p>
    </div>
</div>


<div class="container">

    <div class="row">
        <div class="col-md-6">
            <h2>Registration form</h2>
            <p style="color: red">${updateMessage}</p>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="update_user"/>
                Login: ${user.getLogin()}<br>
                Name:
                <input type="text" name="name" value="${user.getName()}"></br>
                Surname:
                <input type="text" name="surname" value="${user.getSurname()}"></br>
                Phone number:
                <input type="text" name="phone" value="${user.getPhone()}"></br>
                Email:
                <input type="text" name="email" value="${user.getEmail()}"> </br>

                Enter new password:
                <input type="password" name="password1" value=""></br> <p style="color: red">${passwordError}</p>
                Enter new password ones more:
                <input type="password" name="password2" value=""></br>

                <input class="btn btn-success" type="submit" value="Update information"/>


            </form>

        </div>

    </div>
</div>
<br>
<br>
<footer class="footer mt-auto py-3 ">
<div class="container">
    <hr>
    <span class="text-muted">&copy; EPAM 2020</span>
    <hr>
    <br>
    <br>    </div>

</footer>
<script src="webjars/jquery-1.10.2.min.js"></script>
<script src="webjars/dist/js/bootstrap.min.js"></script>
</body>
</html>





