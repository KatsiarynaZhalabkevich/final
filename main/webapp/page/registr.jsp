<%@ page contentType="text/html;charset=UTF-8" language="java"
errorPage="error.jsp" %>
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

    <script type="text/javascript">
        function valid(form) {
            var fail = false;
            var name = form.name.value;
            var surname = form.surname.value;
            var phone = form.phone.value;
            var email = form.email.value;
            var login = form.login.value;
            var password1 = form.password1.value;
            var password2 = form.password2.value;
            var email_pattern = /^[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}$/i;
            var phone_pattern =/^\+?(\d{1,3})?[- .]?\(?(?:\d{2,3})\)?[- .]?\d\d\d[- .]?\d\d\d\d$/;
            var password_pattern =/[0-9a-zA-Z!@#$%^&*]{6,}/;
            var login_pattern = /[0-9a-zA-Z]{4,10}/;
            if (name == "" || name == " ") {
                fail = "Name is incorrect";
            } else if (surname == "" || surname == " ") {
                fail = "Surname is incorrect";
            } else if (phone_pattern.test(phone) == false) {
                fail = "Incorrect phone format";
            } else if (email_pattern.test(email) == false) {
                fail="Incorrect email";
            }else if(login_pattern.test(login)==false){
                fail="Incorrect login format";
            }else if(password_pattern.test(password1)){
                fail="Incorrect password format";
            }else if(password1!=password2){
                fail="Passwords not equals";
            }
            if(fail){
                alert(fail);
                return false;
            }

        }

        function sign(form) {
            var fail = false;
            var login = form.login.value;
            var password1 = form.password1.value;
            if (login == "" || login == " ") {
                fail = "Please, insert your login";
            } else if (password == "" || password == " ") {
                fail = "Please, insert your password";
            }
            if (fail) {
                alert(fail);
                return false;
            }
        }
    </script>

    <title>Registration page</title>
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
                </form>
            </li>
            <li class="active"><a href="#">Registration</a></li>
            <li class="disabled"><a href="#">Private page </a></li>
        </ul>
    </div>
</nav>
<br>

<div class="jumbotron">
    <div class="container">
        <h1>Welcome to our community!</h1>
        <p>Please fill in the form to register. After registration you would manage your account: choose tariffs of
            internet provider "My Telecom"! </p>
    </div>
</div>


<div class="container">

    <div class="row">
        <div class="col-md-6">
            <h2>Registration form</h2>
            <form action="controller" method="post" id="registration">
                <input type="hidden" name="command" value="create_user"/>
                <p style="color: red">${errorMessage}</p>
                <div class="form-group"><label for="name">Name:</label></div>

                <div class="form-group"><input type="text" name="name" value="" id="name"></div>
                <div class="form-group"><label for="surname"> Surname:</label></div>
                <div class="form-group"><input type="text" name="surname" value="" id="surname"></div>
                <div class="form-group"> Phone number:</div>
                <div class="form-group"><input type="text" name="phone" value=""></div>
                <div class="form-group"> Email:</div>
                <div class="form-group"><input type="text" name="email" value=""></div>

                <div class="form-group"> Login:</div>
                <div class="form-group"><input type="text" name="login" value=""></div>
                <p style="color: red">${errorLoginMessage}</p>
                <div class="form-group"> Password:</div>
                <div class="form-group"><input type="password" name="password1" value=""></div>
                <div class="form-group"> Enter your password one more time:</div>
                <div class="form-group"><input type="password" name="password2" value=""></div>
                <p style="color: red">${errorPasswordMessage}</p>

                <div class="form-group"><input class="btn btn-success" type="submit"
                                               onclick="valid(document.getElementById('registration'))"
                                               value="Registration"/></div>

            </form>
            <br>
            <br>
        </div>
        <div class="col-md-6">
            <h2>Sign in</h2>
            <p>If you already have an account, please sign in </p>
            <form action="controller" method="post" id="sign">

                <input type="hidden" name="command" value="authorization"/>
                <div class="form-group"> Login:</div>
                <div class="form-group"><input type="text" name="login" value=""/></div>
                <div class="form-group"> Password:</div>
                <div class="form-group"><input type="password" name="password" value=""/></div>
                <p style="color: red">${loginErrorMessage}</p>
                <div class="form-group"><input class="btn-success btn" type="submit" name="signup"
                                               onclick="sign(document.getElementById('sign'))" value="Sign in">
                </div>

            </form>
        </div>
    </div>
</div>


<br>
<br>
<br>
<br>

<footer class="footer mt-auto py-3 ">
    <div class="container">
        <hr>
        <span class="text-muted">&copy; EPAM 2020</span>
        <hr>
        <br></div>

</footer>

<script src="webjars/jquery-1.10.2.min.js"></script>
<script src="webjars/dist/js/bootstrap.min.js"></script>
</body>
</html>
