<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <a href="${pageContext.servletContext.contextPath}/">back</a>
    <form action="${pageContext.servletContext.contextPath}/user/edit" method="post">
        Id:   <input type="text" name="id" value="${user.id}" readonly><br>
        Name: <input type="text" name="name" value="${user.name}"><br>
        Login: <input type="text" name="login" value="${user.login}"><br>
        Email: <input type="text" name="email" value="${user.email}"><br>
        <input type="submit" value="Edit">
    </form>
</body>
</html>
