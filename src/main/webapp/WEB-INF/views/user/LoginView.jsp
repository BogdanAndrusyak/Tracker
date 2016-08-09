<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
    <title>Login: Tracker</title>
    <script>
        function validate() {
            var result = true;
            var login = document.getElementsByName("login")[0];
            var password = document.getElementsByName("password")[0];

            if(login.value == '') {
                login.classList.add("invalid");
                result = false;
            }
            if(password.value == '') {
                password.classList.add("invalid");
                result = false;
            }

            return result;
        }
    </script>
</head>
<body>
    <c:if test="${error != ''}">
        <div class="error">
            <c:out value="${error}"/>
        </div>
    </c:if>
    <div class="logo">
        <div><span style="color:#61bb46;">T</span><span style="color:#fdb827;">r</span><span style="color:#f5821f;">a</span><span style="color:#eb5e2f;">c</span><span style="color:#e03a3e;">k</span><span style="color:#963d97;">e</span><span style="color:#009ddc;">r</span></div>
    </div>
    <div class="card-auth">
        <form class="auth-form" action="${pageContext.servletContext.contextPath}/signin" method="post" onsubmit="return validate();">
            <div class="row row-login">
                <span class="icon icon-login"><img src="<c:url value="/resources/img/icon-login.png"/>"></span>
                <input type="text" name="login" placeholder="Login"><br>
            </div>
            <div class="row row-password">
                <span class="icon icon-password"><img src="<c:url value="/resources/img/icon-password.png"/>"></span>
                <input type="password" name="password" placeholder="Password"><br>
            </div>
            <div class="row row-submit">
                <input type="submit" value="Login"><br>
            </div>
        </form>
        <span class="create-user"><a href="${pageContext.servletContext.contextPath}/user/create">Create new User?</a></span>
    </div>
</body>
</html>
