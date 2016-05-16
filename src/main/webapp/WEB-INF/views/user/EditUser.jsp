<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <c:choose>
        <c:when test="${sessionScope.roleId == 1}">
            <a href="${pageContext.servletContext.contextPath}/">back</a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.servletContext.contextPath}/signout">Exit</a>
        </c:otherwise>
    </c:choose>

    <form action="${pageContext.servletContext.contextPath}/user/edit" method="post">
        Id:   <c:out value="${user.id}"/>
        <input type="hidden" name="id" value="${user.id}" readonly><br>
        Role:

        <c:choose>
            <c:when test="${sessionScope.roleId == 1}">
                <select name="role-id">
                    <option selected value="${user.role.id}">${user.role.name}</option>
                    <c:forEach items="${roles}" var="role">
                        <c:if test="${user.role.id != role.id}">
                            <option value="${role.id}">${role.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
                <select name="role" disabled>
                    <option selected value="${user.role.id}">${user.role.name}</option>
                </select>
            </c:otherwise>
        </c:choose><br>
        Name: <input type="text" name="name" value="${user.name}"><br>
        Login: <input type="text" name="login" value="${user.login}"><br>
        Password: <input type="password" name="password" value="${user.password}"><br>
        Email: <input type="text" name="email" value="${user.email}"><br>
        <input type="submit" value="Edit">
    </form>
</body>
</html>
