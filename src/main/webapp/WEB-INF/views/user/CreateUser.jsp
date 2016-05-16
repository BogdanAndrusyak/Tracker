<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <a href="${pageContext.servletContext.contextPath}/">back</a>
    <form action="${pageContext.servletContext.contextPath}/user/create" method="post">
        Name: <input type="text" name="name"><br>
        Login: <input type="text" name="login"><br>
        Password: <input type="password" name="password"><br>
        Email: <input type="text" name="email"><br>
        <input type="submit" value="Create">
    </form>
</body>
</html>
