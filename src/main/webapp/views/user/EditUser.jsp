<%@ page import="ru.parsentev.models.User" %>
<%@ page import="ru.parsentev.store.UserCache" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <a href="<%=request.getContextPath()%>/">back</a>
    <form action="<%=request.getContextPath()%>/user/edit" method="post">
        <%User user = UserCache.getInstance().get(Integer.valueOf(request.getParameter("id")));%>
        Id:   <input type="text" name="id" value="<%=user.getId()%>" readonly><br>
        Name: <input type="text" name="name" value="<%=user.getName()%>"><br>
        Login: <input type="text" name="login" value="<%=user.getLogin()%>"><br>
        Email: <input type="text" name="email" value="<%=user.getEmail()%>"><br>
        <input type="submit" value="Edit">
    </form>
</body>
</html>
