<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/edit-user-style.css"/>">
    <title>View: Tracker</title>
</head>
<body>
    <div class="center-block-main">
        <div class="input-form">
            <div class="title">
                <span>Edit Profile</span>
            </div>
            <form action="${pageContext.servletContext.contextPath}/user/edit" method="post">
                <c:choose>
                    <c:when test="${sessionScope.roleId == 1}">
                        <div class="col">
                            <div class="col-text">Id</div>
                            <div class="col-input">
                                <c:out value="${user.id}"/>
                                <input type="hidden" name="id" value="${user.id}" readonly>
                            </div>
                        </div><br>
                        <div class="col">
                            <div class="col-text">Role</div>
                            <div class="col-input">
                                <select name="role-id">
                                    <option selected value="${user.role.id}">${user.role.name}</option>
                                    <c:forEach items="${roles}" var="role">
                                        <c:if test="${user.role.id != role.id}">
                                            <option value="${role.id}">${role.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div><br>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="id" value="${user.id}" readonly>
                        <input type="hidden" name="role-id" value="${user.role.id}" readonly>
                    </c:otherwise>
                </c:choose>


                <div class="col">
                    <div class="col-text">Name</div>
                    <div class="col-input">
                        <input type="text" name="name" value="${user.name}">
                    </div>
                </div><br>
                <div class="col">
                    <div class="col-text">Login</div>
                    <div class="col-input">
                        <input type="text" name="login" value="${user.login}">
                    </div>
                </div><br>
                <div class="col">
                    <div class="col-text">Password</div>
                    <div class="col-input show-password">
                        <input type="password" name="password" value="${user.password}">
                        <span id="show-password">Show</span>
                    </div>
                </div><br>
                <div class="col">
                    <div class="col-text">Email</div>
                    <div class="col-input">
                        <input type="text" name="email" value="${user.email}">
                    </div>
                </div><br>
                <div class="col input-buttons">
                    <input type="submit" value="Submit">
                    <a href="${pageContext.servletContext.contextPath}/user/view">Cancel</a>
                </div>
            </form>
        </div>
    </div>

    <script>
        $(document.getElementById("show-password")).click(function(){
            var type = $(document.getElementsByName("password")[0]).attr('type') == "text" ? "password" : 'text',
                    c = $(this).text() == "Hide" ? "Show" : "Hide";
            $(this).text(c);
            $(document.getElementsByName("password")).prop('type', type);
        });
    </script>
</body>
</html>
