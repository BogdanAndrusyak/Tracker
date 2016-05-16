<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <a href="${pageContext.servletContext.contextPath}/user/create">Add new user</a><br>
    <table style="border: 1px solid black;" border="1" cellpadding="1" cellspacing="0">
        <tr>
            <th>Id</th>
            <th>Role</th>
            <th>Name</th>
            <th>Login</th>
            <th>Password</th>
            <th>Email</th>
            <th>Create Date</th>
            <th>Actions</th>
        </tr>
        <с:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.role.name}</td>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
            <td>${user.simpleCreateDate}</td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/user/edit?id=${user.id}">Edit</a><br>
                <a href="${pageContext.servletContext.contextPath}/user/delete?id=${user.id}">Delete</a><br>
            </td>
        </tr>
        </с:forEach>
    </table>
    <a href="${pageContext.servletContext.contextPath}/signout">Exit</a>
</body>
</html>
