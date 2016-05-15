<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <a href="<%=request.getContextPath()%>/">back</a>
    <form action="<%=request.getContextPath()%>/user/create" method="post">
        Name: <input type="text" name="name" value=""><br>
        Login: <input type="text" name="login" value=""><br>
        Email: <input type="text" name="email" value=""><br>
        <input type="submit" value="Create">
    </form>
</body>
</html>
