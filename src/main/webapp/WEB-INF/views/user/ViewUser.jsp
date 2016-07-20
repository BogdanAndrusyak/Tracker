<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/view-style.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/edit-user-style.css"/>">
    <title>View: Tracker</title>
    <%--<script>--%>
        <%--function validate() {--%>
            <%--var result = true--%>
        <%--}--%>
    <%--</script>--%>
</head>
<body>
<%--edit user popup page--%>
<div class="edit-user-dialog" id="popup1">
    <div class="edit-user-content">
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
                    <a href="javascript:PopUpHide()">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>
<%--end--%>

<div class="center-block-main">
    <nav class="lists">
        <div class="logo">
            <div><span style="color:#61bb46;">T</span><span style="color:#fdb827;">r</span><span style="color:#f5821f;">a</span><span style="color:#eb5e2f;">c</span><span style="color:#e03a3e;">k</span><span style="color:#963d97;">e</span><span style="color:#009ddc;">r</span></div>
        </div>
        <div class="user-toolbar">
            <img src="<с:url value="/resources/images/icon-login.png"/>" alt="avatar">
            <span>${user.name}</span>
            <a href="javascript:PopUpShow()">Edit profile</a>
        </div>
        <div class="lists user-tools">
            <h2>Your items:</h2>

            <a href="javascript:ChangeToAll()">
                <ul>
                    <li>
                        <span class="list-icon"><img src="<с:url value="/resources/images/list-all.png"/>"></span>
                        <span class="label">All</span>
                        <span class="list-count">${user.items.size()}</span>
                    </li>
                </ul>
            </a>

            <%--todo other categories--%>
            <%--<a href="">--%>
                <%--<ul>--%>
                    <%--<li>--%>
                        <%--<span class="list-icon"><img src="<c:url value="/resources/images/list-other.png"/>"></span>--%>
                        <%--<span class="label">All</span>--%>
                        <%--<span class="list-count">2</span>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</a>--%>
        </div>
        <c:if test="${sessionScope.roleId == 1}">
            <div class="lists admin-tools">
                <h2>Administration tools</h2>
                <a href="javascript:ChangeToAllUsers()">
                    <ul>
                        <li>
                            <span class="list-icon"><img src="<c:url value="/resources/images/icon-admin-group.png"/>"></span>
                            <span class="label">All users</span>
                            <span class="list-count">${users.size()}</span>
                        </li>
                    </ul>
                </a>
            </div>
        </c:if>
    </nav>

    <div class="tasks">
        <div class="list-toolbar">
            <h1>Title - All items</h1>

            <a class="logout-button" href="${pageContext.servletContext.contextPath}/signout"><img src="<c:url value="/resources/images/logout-button.png"/>"></a>
        </div>
        <div class="tasks-scroll">
            <form action="${pageContext.request.contextPath}/item/create" method="post" class="addTask">
                <input type="text" name="itemName" placeholder="Add new task" required>
                <a href=""><img src="<c:url value="/resources/images/add-more.png"/>"></a>
                <input type="image" src="<c:url value="/resources/images/add-button.png"/>" alt="Add Form">
                <%--todo add to form more input fields--%>
                <%--<div class="add-more">--%>
                    <%----%>
                <%--</div>--%>
            </form>
            <div class="tasks-list">
                <div class="block" id="main-content">
                    <%--add whith js--%>
                </div>
            </div>
        </div>
    </div>
</div>







    <%--change main content--%>
    <script>
        $(document).ready(function(){
            //show all where page load
            ChangeToAll();
        });

        function ChangeToAll() {
//            todo maybe exists easy way ??
            var inner = '<h2>All</h2>' +
                    '<ul>' +
                    '<c:forEach items="${user.items}" var="item">' +
                    '<li class="clearfix">' +
                    '<a class="check-box" href="${pageContext.servletContext.contextPath}/item/delete?item-id=${item.id}"><img src="<c:url value="/resources/images/check-box.png"/>"></a>' +
                    '<div class="task-title">' +
                    '<span>${item.name}</span>' +
                    '</div>' +
                    '<a class="edit-item" href="">More/Edit</a>' +
                    '</li>' +
                    '</c:forEach>' +
                    '</ul>';
            document.getElementById("main-content").innerHTML = inner;
        }

        function ChangeToAllUsers() {
//            todo maybe exists easy way ??
            var inner = '<table class="table">' +
                    '<tr>' +
                    '<th>Id</th>' +
                    '<th>Role</th>' +
                    '<th>Name</th>' +
                    '<th>Login</th>' +
                    '<th>Password</th>' +
                    '<th>Email</th>' +
                    '<th>Create Date</th>' +
                    '<th>Actions</th>' +
                    '</tr>' +
                    '<с:forEach items="${users}" var="user">' +
                    '<tr>' +
                    '<td>${user.id}</td>' +
                    '<td>${user.role.name}</td>' +
                    '<td>${user.name}</td>' +
                    '<td>${user.login}</td>' +
                    '<td>${user.password}</td>' +
                    '<td>${user.email}</td>' +
                    '<td>${user.simpleCreateDate}</td>' +
                    '<td>' +
                    '<a href="${pageContext.servletContext.contextPath}/user/edit?id=${user.id}">Edit</a><br>' +
                    '<a class = "link-delete" href="${pageContext.servletContext.contextPath}/user/delete?id=${user.id}">Delete</a><br>' +
                    '</td>' +
                    '</tr>' +
                    '</с:forEach>' +
                    '</table>';
            document.getElementById("main-content").innerHTML = inner;
        }

    </script>
    <script>
        $(document).ready(function(){
            //Скрыть PopUp при загрузке страницы
            PopUpHide();
        });
        //Функция отображения PopUp
        function PopUpShow(){
            $("#popup1").show();
        }
        //Функция скрытия PopUp
        function PopUpHide(){
            $("#popup1").hide();
        }
    </script>
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
