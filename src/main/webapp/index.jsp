<%@ page import="ru.parsentev.store.UserCache" %>
<%@ page import="ru.parsentev.models.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <a href="<%=request.getContextPath()%>/views/user/CreateUser.jsp">Add new user</a>
    <table style="border: 1px solid black;" border="1" cellpadding="1" cellspacing="0">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Create Date</th>
            <th>Actions</th>
        </tr>
        <%for (User user : UserCache.getInstance().users()) {%>
        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getName()%></td>
            <td><%=user.getLogin()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=new SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(user.getCreateDate().getTime())%></td>
            <td>
                <a href="<%=request.getContextPath()%>/user/edit?id=<%=user.getId()%>">Edit</a><br>
                <a href="<%=request.getContextPath()%>/user/delete?id=<%=user.getId()%>">Delete</a><br>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
