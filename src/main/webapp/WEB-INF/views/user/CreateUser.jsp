<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
    <title>Registration: Tracker</title>
</head>
<body>
    <div class="logo">
        <div><span style="color:#61bb46;">T</span><span style="color:#fdb827;">r</span><span style="color:#f5821f;">a</span><span style="color:#eb5e2f;">c</span><span style="color:#e03a3e;">k</span><span style="color:#963d97;">e</span><span style="color:#009ddc;">r</span></div>
    </div>
    <div class="card-auth">
        <form class="auth-form" action="" method="post">
            <div class="row row-name">
                <span class="icon icon-name"><img src="<c:url value="/resources/images/icon-name.png"/>"></span>
                <input type="text" name="name" placeholder="Name"><br>
            </div>
            <div class="row row-login">
                <span class="icon icon-login"><img src="<c:url value="/resources/images/icon-login.png"/>"></span>
                <input type="text" name="login" placeholder="Login"><br>
            </div>
            <div class="row row-login">
                <span class="icon icon-email"><img src="<c:url value="/resources/images/icon-email.png"/>"></span>
                <input type="email" name="email" placeholder="Email"><br>
            </div>
            <div class="row row-password">
                <span class="icon icon-password"><img src="<c:url value="/resources/images/icon-password.png"/>"></span>
                <input type="password" name="password" placeholder="Password"><br>
            </div>
            <div class="row row-submit">
                <input type="submit" value="Register"><br>
            </div>
        </form>
        <span class="create-user"><a href="${pageContext.servletContext.contextPath}/signin">I have an account.</a></span>
    </div>
</body>
</html>
