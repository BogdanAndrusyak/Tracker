<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/view-style.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/edit-user-style.css"/>">
    <title>View: Tracker</title>
    &lt;%&ndash;<script>&ndash;%&gt;
        &lt;%&ndash;function validate() {&ndash;%&gt;
            &lt;%&ndash;var result = true&ndash;%&gt;
        &lt;%&ndash;}&ndash;%&gt;
    &lt;%&ndash;</script>&ndash;%&gt;
</head>
<body>
&lt;%&ndash;edit user popup page&ndash;%&gt;
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
&lt;%&ndash;end&ndash;%&gt;

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

            &lt;%&ndash;todo other categories&ndash;%&gt;
            &lt;%&ndash;<a href="">&ndash;%&gt;
                &lt;%&ndash;<ul>&ndash;%&gt;
                    &lt;%&ndash;<li>&ndash;%&gt;
                        &lt;%&ndash;<span class="list-icon"><img src="<c:url value="/resources/images/list-other.png"/>"></span>&ndash;%&gt;
                        &lt;%&ndash;<span class="label">All</span>&ndash;%&gt;
                        &lt;%&ndash;<span class="list-count">2</span>&ndash;%&gt;
                    &lt;%&ndash;</li>&ndash;%&gt;
                &lt;%&ndash;</ul>&ndash;%&gt;
            &lt;%&ndash;</a>&ndash;%&gt;
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
                &lt;%&ndash;todo add to form more input fields&ndash;%&gt;
                &lt;%&ndash;<div class="add-more">&ndash;%&gt;
                    &lt;%&ndash;&ndash;%&gt;
                &lt;%&ndash;</div>&ndash;%&gt;
            </form>
            <div class="tasks-list">
                <div class="block" id="main-content">
                    &lt;%&ndash;add whith js&ndash;%&gt;
                </div>
            </div>
        </div>
    </div>
</div>







    &lt;%&ndash;change main content&ndash;%&gt;
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
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tracker</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/edit-user-style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/app.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/font-icons/style.css"/>" title="App Style" type="text/css"
          media="all" charset="utf-8">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script>
        $(
                $.ajax('../json', {
                    method : 'get',
                    complete: function (data) {
                        var result = "<option value = \"${user.country}\"selected>${user.country}</option>";
                        var countries = JSON.parse(data.responseText).countries;
                        for (var i=0; i!=countries.length; ++i) {
                            if (countries[i].countryName != "${user.country}") {
                                result += "<option value=\"" + countries[i].countryName + "\">" + countries[i].countryName + "</option>";
                            }
                        }
                        var select = document.getElementById("country-list");
                        select.innerHTML = result;
                    }
                })

        );

        $(changeCitiesList());

        function changeCitiesList() {
            $.ajax('../json', {
                method : 'get',
                complete: function (data) {
                    var countrySelected = document.getElementById('country-list').value;
                    var citySelected = document.getElementById('cities-list').value;
                    var result = "";
                    var cities = JSON.parse(data.responseText).cities;
                    var citiesOnly = [];

                    for (var i=0; i!=cities.length; ++i) {
                        if (cities[i].countryName == countrySelected) {
                            for (var t=0; t!=cities[i].citiesList.length; ++t){
                                if(cities[i].citiesList[t].cityName) {
                                    citiesOnly[t] = cities[i].citiesList[t].cityName;
                                    result += "<option value=\"" + cities[i].citiesList[t].cityName + "\">" + cities[i].citiesList[t].cityName + "</option>";
                                }
                            }
                        }
                    }

                    var currentCountry = false;
                    for(var h=0; h!=citiesOnly.length; ++h) {
                        if (citiesOnly[h] == "${user.city}") {
                            currentCountry = true;
                            var result2 = result;
                            result2 = result2.replace("<option value=\"${user.city}\">${user.city}</option>", "");
                            result = "<option value = \"${user.city}\"selected>${user.city}</option>";
                            result += result2;
                        }
                    }

                    if(currentCountry == false) {
                        var result2 = result;
                        result = "<option value = \"\"selected>Your city</option>";
                        result += result2;
                    }

                    var cityElement = document.getElementById("cities-list");
                    cityElement.innerHTML = result;
                }
            });
        }

    </script>
    <script>
        $(document.getElementById("show-password")).click(function(){
            var type = $(document.getElementsByName("password")[0]).attr('type') == "text" ? "password" : 'text',
                    c = $(this).text() == "Hide" ? "Show" : "Hide";
            $(this).text(c);
            $(document.getElementsByName("password")[0]).prop('type', type);
        });
    </script>
</head>
<body onhashchange="OnHashChange (event);">

<%--main content--%>
<div id="AppWrapper" style="width: 1920px; height: 100%;">
    <%--edit user dialog--%>
        <div class="listView FormCard popUp EditUserView" id="EditUserView" data-state data-view="popUpView" style="left: 0px; width: 1920px; margin-top:0;">

            <!--header_frame-->
            <div class="header_frame listHeader" data-comp="listHeader" style="margin-top: 60px; background-color: #fff;">

                <!--view-->
                <div class="full_box view">
                    <div class="center_box">
                        <div class="table">

                            <!--left-->
                            <div class="cell left">

                                <!--page_tittle-->
                                <div class="page_title big">
                                    <h2 data-comp="formTitle">Edit Profile</h2>
                                </div>
                                <!--page_tittle-->

                            </div>
                            <!--/left-->

                            <!--right-->
                            <div class="cell right">

                                <!--action-->
                                <div class="action">
                                    <a href="#backFromEditUserView" class="icon"><i class="icon-icontt_close"></i></a>
                                </div>
                                <!--/action-->

                            </div>
                            <!--/right-->

                        </div>
                    </div>
                </div>
                <!--/view-->

            </div>
            <!--header_frame-->

            <!--content_frame-->
            <div class="content_frame" data-comp="listBody" data-scroll="true" style="height: 500px; top: 122px; background-color: #fff;">
                <div class="full_box content formView">
                    <div class="center_box">

                        <!--form-->
                        <form name="editUser" action="${pageContext.servletContext.contextPath}/user/edit" method="post" novalidate="novalidate">
                            <!--hidden_inputs-->
                            <input type="hidden" name="id" value="${user.id}">
                            <input type="hidden" name="role-id" value="${user.role.id}">
                            <%--<input type="hidden" name="due_date" value="">--%>
                            <%--<input type="hidden" name="list_id" value="">--%>
                            <%--<input type="hidden" name="list_position" value="">--%>
                            <!--/hidden_inputs-->

                            <!--name-->
                            <label for="name" class="primary">
                                <h4 class="trans" data-orig_text="Task Name">Name</h4>
                                <input type="text" autocomplete="off" required="" name="name" data-orig_text="Task Name" value="${user.name}" aria-required="true">
                            </label>
                            <!--name-->

                            <!--name-->
                            <label for="login" class="primary">
                                <h4 class="trans" data-orig_text="Task Name">Login</h4>
                                <input type="text" autocomplete="off" required="" name="login" data-orig_text="Task Name" value="${user.login}" aria-required="true">
                            </label>
                            <!--name-->

                            <!--name-->
                            <label for="password" class="primary">
                                <h4 class="trans" data-orig_text="Task Name">Password</h4>
                                <input type="password" autocomplete="off" required="" name="password" data-orig_text="Task Name" value="${user.password}" aria-required="true">
                                <span id="show-password">Show</span>
                            </label>
                            <!--name-->

                            <!--name-->
                            <label for="email" class="primary">
                                <h4 class="trans" data-orig_text="Task Name">Email</h4>
                                <input type="text" autocomplete="off" required="" name="email" data-orig_text="Task Name" value="${user.email}" aria-required="true">
                            </label>
                            <!--name-->

                            <%--city--%>
                            <label for="country">
                                <h4 class="trans" data-orig_text="project">Country</h4>
                                <select id="country-list" name="country" onchange="changeCitiesList()">
                                </select>
                            </label>
                            <%--/city--%>

                            <%--city--%>
                            <label for="city">
                                <h4 class="trans" data-orig_text="project">City</h4>
                                <select id="cities-list" name="city">
                                </select>
                            </label>
                            <%--/city--%>

                            <!--submit-->
                            <input type="submit" value="">
                            <!--/submit-->

                        </form>
                        <!--/form-->
                    </div>
                </div>
            </div>
            <!--/content_frame-->

            <!--footer_frame-->
            <div class="footer_frame" data-comp="listFooter" style="background-color: #fff;">
                <div class="full_box footer">
                    <div class="center_box">

                        <!--formFooter-->
                        <div class="formFooter right">
                            <a href="#delete" class="trans form_button" data-orig_text="Delete" style="display: none;">Delete</a>
                            <a href="#open" class="trans form_button" data-orig_text="Re-open" style="display: none;">Re-open</a>
                            <a href="#close" class="trans form_button" data-orig_text="Mark as done" style="display: none;">Mark as done</a>
                            <a href="#saveEditUser" class="trans form_button" data-orig_text="Add" style="position: absolute; right: 20px;">Save</a>
                        </div>
                        <!--/formFooter-->

                    </div>
                </div>
            </div>
            <!--/footer_frame-->

        </div>
    <%--edit user dialog--%>


    <!--app_nav-->
    <div id="AppNav" class="active" style="display: block; left: 0; width: 1920px;">

        <!--nav_header-->
        <div class="row nav_header listHeader header_frame" data-comp="listHeader">

            <!--colapse_appnav-->
            <div class="colapse_appnav">

                <!--close-->
                <a href="#" data-comp="collapseAppView" class="close desktop_v">

                    <!--trackingTimeLogo-->
                    <div class="trackingTimeLogo white">
                        <p class="icon-logo">TRACKER</p>
                    </div>
                    <!--/trackingTimeLogo-->

                </a>
                <!--/close-->

            </div>
            <!--/colapse_appnav-->

        </div>
        <!--/nav_header-->

        <!--nav_content-->
        <div class="row nav_content content_frame" data-comp="listBody" data-scroll="true"
             style="height: 750px; top: 50px;">

            <!--switch_user_mobile-->
            <div class="switch_user_mobile">
                <!--change_account-->
                <a class="change_account" data-comp="switchAccountButton" href="#/switchAccount">

                    <!--icon-->
                    <div class="ico">
                        <i class="icon-icontt_nav-bottom"></i>
                    </div>
                    <!--/icon-->

                    <!--current_account-->
                    <div class="current_account">
                        <u class="title" data-orig_text="Active account">Active account</u>
                        <u class="current" data-comp="accountName"></u>
                    </div>
                    <!--/current_account-->

                </a>
                <!--change_account-->
            </div>
            <!--/switch_user_mobile-->

            <!--list_nav-->
            <div class="list_nav">

                <div id="user-toolbar">
                    <a class="user" data-path="me" role="button" tabindex="0"
                       aria-label="Bogdan User menu." aria-expanded="false" aria-haspopup="true">
                        <span class="user-avatar"><div class="avatar medium" title="Bogdan"><img
                                src="<c:url value="/resources/img/avatar.png"/>"></div></span>
                        <span class="user-name"><c:out value="${user.name}"/></span> <span class="user-arrow">

                            <a href="#EditUserView">
                                <svg
                            xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="20px"
                            height="20px" viewBox="0 0 20 20" version="1.1" xml:space="preserve"
                            style="fill-rule:evenodd;clip-rule:evenodd;stroke-linejoin:round;stroke-miterlimit:1.41421;"> <g> <path
                            d="M10.502,13c-0.132,0 -0.26,-0.053 -0.354,-0.146l-4.002,-4c-0.195,-0.195 -0.195,-0.512 0,-0.708c0.195,-0.195 0.512,-0.195 0.707,0l3.649,3.647l3.644,-3.647c0.195,-0.195 0.512,-0.195 0.707,0c0.195,0.195 0.195,0.512 0,0.708l-3.997,4c-0.094,0.093 -0.221,0.146 -0.354,0.146"></path> </g> </svg>
                            </a>
                        </span>
                    </a>
                </div>


                <ul data-comp="listContent">
                    <li>
                        <a class="task active" id="TaskListButton" data-base_url="tasks|task|project"
                           href="#/tasks" data-comp="appNavButton">
                            <span class="ico"><i class="icon-icontt_tasks-solid"></i></span>
                            <div class="tooltip_c">
                                <span class="desc trans" data-orig_text="Tasks">Tasks</span>
                            </div>
                        </a>
                    </li>
                    <%--todo maybe bad style--%>
                    <li style="border-top: 1px solid #e5190b; margin-top: 20px; padding-top: 10px;">
                        <a class="allusers can_view_others" data-base_url="dashboard" href="#/allusers"
                           data-comp="appNavButton">
                            <span class="ico pro_opacity"><i class="icon-icontt_dashboard"></i></span>
                            <div class="tooltip_c pro_corner_white">
                                <span class="desc pro_opacity" data-orig_text="Dashboard">All Users - <br><span style="color: white; font-size: 0.8em;">Not supported yet :(</span></span>
                            </div>
                        </a>
                    </li>

                    <%--todo other functions--%>
                    <%--
                    <li>
                        <a class="dashboard can_view_others" data-base_url="dashboard" href="#/dashboard"
                           data-comp="appNavButton">
                            <span class="ico pro_opacity"><i class="icon-icontt_dashboard"></i></span>
                            <div class="tooltip_c pro_corner_white">
                                <span class="desc pro_opacity" data-orig_text="Dashboard">Dashboard</span>
                            </div>
                        </a>
                    </li>
                    <li class="can_view_others">
                        <a class="activity" data-base_url="activity" href="#/activity" data-comp="appNavButton">
                            <span class="ico"><i class="icon-icontt_activity"></i></span>
                            <div class="tooltip_c">
                                <span class="desc trans" data-orig_text="Activity">Activity</span>
                            </div>
                        </a>
                    </li>
                    <li class="">
                        <a class="calendar" data-base_url="calendar" href="#/calendar" data-comp="appNavButton">
                            <span class="ico pro_opacity"><i class="icon-icontt_calendar"></i></span>
                            <div class="tooltip_c pro_corner_white">
                                <span class="desc trans pro_opacity" data-orig_text="Calendar">Calendar</span>
                            </div>
                        </a>
                    </li>
                    <li class="coworker">
                        <a class="team" data-base_url="team" href="#/team" data-comp="appNavButton">
                            <span class="ico"><i class="icon-icontt_profile"></i></span>
                            <div class="tooltip_c">
                                <span class="desc trans" data-orig_text="Team">Team</span>
                            </div>
                        </a>
                    </li>
                    <li class="coworker">
                        <a class="reports"
                           data-base_url="timesheets|analytics|projects-dashboard|live-view|custom-reports"
                           href="#/timesheets" data-comp="appNavButton">
                            <span class="ico"><i class="icon-icontt_reportsfunnel"></i></span>
                            <div class="tooltip_c">
                                <span class="desc trans" data-orig_text="Reports">Reports</span>
                            </div>
                        </a>
                    </li>
                    <li class="coworker">
                        <a class="company" data-base_url="company" href="#/company" data-comp="appNavButton">
                            <span class="ico"><i class="icon-icontt_company"></i></span>
                            <div class="tooltip_c">
                                <span class="desc trans" data-orig_text="Company">Company</span>
                            </div>
                        </a>
                    </li>--%>
                </ul>
            </div>
            <!--/list_nav-->

        </div>
        <!--/nav_content-->

        <!--nav_footer-->
        <div class="row nav_footer footer_frame" data-comp="listFooter">

            <!--brand-->
            <div class="brand">

                <!--trackingTimeLogo-->
                <div class="trackingTimeLogo white">
                    <p class="icon-logo" style="margin-left: 20px;">TRACKER</p>
                </div>
                <!--/trackingTimeLogo-->

                <!--social_networks-->
                <div class="social_networks" style="margin-top: 15px; color: white;">
                    <ul>
                        <li>
                            <a href="https://github.com/BogdanAndrusyak/Tracker" target="_blank">
                                <i class="icon-icontt_activity"></i><u class="trans" data-orig_text="Git repository">Git repository</u>
                            </a>
                        </li>
                    </ul>
                </div>
                <!--/social_networks-->

            </div>
            <!--/brand-->

        </div>
        <!--/nav_footer-->

    </div>
    <!--/app_nav-->

    <!--TopNav-->
    <div id="TopNav">
        <div class="center_content">
            <div class="table">

                <!--cell-->
                <div class="cell center">
                    <div class="table player" data-comp="player">
                        <form name="addTaskTop" action="${pageContext.request.contextPath}/item/create" method="post">
                            <div class="cell playInidcator">
                                <input type="text" autocomplete="off" placeholder="Add task" name="taskName"
                                       class="taskProject"/>
                            </div>
                        </form>
                        <div class="cell button">
                            <a href="#addTaskTop" class="playButton" data-comp="playButton">
                                <span class="play play_ic"><i class="icon-icontt_play"></i></span>
                            </a>
                        </div>
                    </div>
                </div>
                <!--/cell-->

            </div>
        </div>
    </div>
    <!--/top_nav-->

    <!--tasklistNavigator-->
    <div class="listView column_a taskListNavigator transparent active" id="TaskListNavigator" data-state=""
         data-view="fullView" data-display="column_a" style="left: 200px; width: 332.5px;">

        <!--header_frame-->
        <div class="header_frame listHeader" data-comp="listHeader" style="width: 312.5px;">

            <!--
            <div class="full_box view primary">
                <div class="center_box">
                    <div class="table">
                        <div class="cell left">
                            <div class="page_title">
                                <h2><u class="trans red" data-orig_text="Task list">Task list</u> <a href="#loadEverything"><i class="icon-icontt_sync"></i></a></h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            -->

            <!--secondary-->
            <div class="full_box view secondary">
                <div class="center_box">
                    <div class="table">

                        <!--cell-->
                        <div class="cell left">

                            <!--search_bar-->
                            <div class="searh_bar_c">

                                <!--search_box-->
                                <input type="text" autocomplete="off" name="search_term" placeholder="Search - Not supported yet :(" value=""
                                       data-comp="searchTable">
                                <!--/search_box-->

                                <!--load_anim-->
                                <div class="load_anim">
                                    <div class="dotsC">
                                        <div class="dot one"></div>
                                        <div class="dot two"></div>
                                        <div class="dot three"></div>
                                    </div>
                                </div>
                                <!--/load_anim-->

                                <!--search-->
                                <i class="icon-icontt_search"></i>
                                <!--/search-->

                                <!--search_bar_result-->
                                <div class="searchResults">
                                    <ul data-comp="searchResults"></ul>
                                </div>
                                <!--/search_bar_result-->

                            </div>
                            <!--/search_bar-->

                        </div>
                        <!--/cell-->

                    </div>
                </div>
            </div>
            <!--/secondary-->

        </div>
        <!--header_frame-->

        <!--sub_header_iframe-->
        <div class="sub_header_iframe" data-comp="listSubNav" style="width: 312.5px;">
            <div class="view full_box">
                <div class="center_box">

                    <!--my_task_navigation-->
                    <div class="full_box myTasksNavigation">

                        <!--task-->
                        <div class="center_box my_tasks_header notifyOn">
                            <a href="#/tasks"><u class="trans" data-orig_text="My Tasks">My Tasks</u></a>
                        </div>
                        <!--/task-->

                    </div>
                    <!--/my_task_navigation-->

                    <!--my_project_title-->
                    <div class="full_box myProjectTitle">
                        <div class="center_box noRight">
                            <div class="table">

                                <!--left-->
                                <div class="cell left">
                                    <h2>Projects: - <span style="color: red;">Not supported yet :(</span></h2>
                                </div>
                                <!--/left-->

                                <!--right-->
                                <div class="cell right can_edit_projects_and_tasks">
                                    <a href="#/edit-project">
                                        <i class="icon-icontt_add"> </i>
                                    </a>
                                </div>
                                <!--/right-->

                            </div>
                        </div>
                    </div>
                    <!--/my_project_title-->

                </div>
            </div>
        </div>
        <!--/sub_header_iframe-->

        <!--content_frame-->
        <div class="content_frame" data-comp="listBody" data-scroll="true"
             style="height: 379px; top: 112px; width: 312.5px;">
            <div class="full_box content">
                <div class="center_box">

                    <!--myProjectsContainer-->
                    <%--todo not supported yet--%>
                    <%--<div class="myProjectsContainer" data-comp="projectsContainer">--%>
                        <%--<li class="listItem" data-id="0" data-comp="projectListItem" data-client="" data-name=""--%>
                            <%--style=""><a href="#/project/0" data-id="0" data-action="select"--%>
                                        <%--class="listPrimaryAction"><span class="taskName " style="color:inherit"><u>No project</u></span></a>--%>
                            <%--<div class="listSecondaryAction"></div>--%>
                        <%--</li>--%>
                        <%--<ul>--%>
                            <%--<li class="listItem selected" data-id="324834" data-comp="projectListItem"--%>
                                <%--data-client="zzzz" data-name="Programming [road to WORK]" data-priority="1"--%>
                                <%--data-following="2" style="" data-sortable_date="no_due_date"><a href="#/project/324834"--%>
                                                                                                <%--data-id="324834"--%>
                                                                                                <%--data-action="select"--%>
                                                                                                <%--class="listPrimaryAction"><span--%>
                                    <%--class="taskName hasColor"--%>
                                    <%--style="color:#f95a4e"><u>Programming [road to WORK]</u></span>--%>
                            <%--</a>--%>
                                <%--<div class="listSecondaryAction"><a href="#" class="optionPoints"--%>
                                                                    <%--data-action="displayContextMenu" data-type="active"--%>
                                                                    <%--data-following="false" data-id="324834">• • •</a>--%>
                                <%--</div>--%>
                            <%--</li>--%>
                            <%--<li class="listItem" data-id="324835" data-comp="projectListItem" data-client="zzzz"--%>
                                <%--data-name="Hacking [POWER]" data-priority="2" data-following="2" style=""--%>
                                <%--data-sortable_date="no_due_date"><a href="#/project/324835" data-id="324835"--%>
                                                                    <%--data-action="select" class="listPrimaryAction"><span--%>
                                    <%--class="taskName hasColor" style="color:#fbcc4c"><u>Hacking [POWER]</u></span>--%>
                            <%--</a>--%>
                                <%--<div class="listSecondaryAction"><a href="#" class="optionPoints"--%>
                                                                    <%--data-action="displayContextMenu" data-type="active"--%>
                                                                    <%--data-following="false" data-id="324835">• • •</a>--%>
                                <%--</div>--%>
                            <%--</li>--%>
                            <%--<li class="listItem" data-id="326448" data-comp="projectListItem" data-client="zzzz"--%>
                                <%--data-name="Business" data-priority="3" data-following="2" style=""--%>
                                <%--data-sortable_date="no_due_date"><a href="#/project/326448" data-id="326448"--%>
                                                                    <%--data-action="select" class="listPrimaryAction"><span--%>
                                    <%--class="taskName hasColor" style="color:#00d6b9"><u>Business</u></span>--%>
                            <%--</a>--%>
                                <%--<div class="listSecondaryAction"><a href="#" class="optionPoints"--%>
                                                                    <%--data-action="displayContextMenu" data-type="active"--%>
                                                                    <%--data-following="false" data-id="326448">• • •</a>--%>
                                <%--</div>--%>
                            <%--</li>--%>
                            <%--<li class="listItem" data-id="0" data-comp="projectListItem" data-client="zzzz" data-name=""--%>
                                <%--data-priority="99" data-following="2" style="" data-sortable_date="no_due_date"></li>--%>
                            <%--<li class="listItem" data-id="327624" data-comp="projectListItem" data-client="zzzz"--%>
                                <%--data-name="Trading" data-priority="99" data-following="2" style=""--%>
                                <%--data-sortable_date="no_due_date"><a href="#/project/327624" data-id="327624"--%>
                                                                    <%--data-action="select" class="listPrimaryAction"><span--%>
                                    <%--class="taskName " style="color:inherit"><u>Trading</u></span>--%>
                            <%--</a>--%>
                                <%--<div class="listSecondaryAction"><a href="#" class="optionPoints"--%>
                                                                    <%--data-action="displayContextMenu" data-type="active"--%>
                                                                    <%--data-following="false" data-id="327624">• • •</a>--%>
                                <%--</div>--%>
                            <%--</li>--%>
                            <%--<li class="listItem" data-id="327625" data-comp="projectListItem" data-client="zzzz"--%>
                                <%--data-name="Trading Robots" data-priority="99" data-following="2" style=""--%>
                                <%--data-sortable_date="no_due_date"><a href="#/project/327625" data-id="327625"--%>
                                                                    <%--data-action="select" class="listPrimaryAction"><span--%>
                                    <%--class="taskName " style="color:inherit"><u>Trading Robots</u></span>--%>
                            <%--</a>--%>
                                <%--<div class="listSecondaryAction"><a href="#" class="optionPoints"--%>
                                                                    <%--data-action="displayContextMenu" data-type="active"--%>
                                                                    <%--data-following="false" data-id="327625">• • •</a>--%>
                                <%--</div>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</div>--%>
                    <!--/myProjectsContainer-->

                    <!-- /// -->

                    <!--myProjectsContainer-->
                    <div class="myProjectsContainer archived" data-comp="archivedProjectsContainer"></div>
                    <!--/myProjectsContainer-->

                </div>
            </div>
        </div>
        <!--/content_frame-->

    </div>
    <!--/tasklistNavigator-->

    <!--taskListDetailProject-->
    <div class="listView column_b taskListDetail sort_by_tasklist active show_closed_tasks_false project-archived-false"
         id="Project" data-state="" data-view="fullView" data-display="column_b" style="left: 530px; width: 1040px;">

        <!--header_frame-->
        <div class="header_frame listHeader" data-comp="listHeader" style="width: 1040px;">

            <!--view-->
            <div class="full_box view">
                <div class="center_box">
                    <div class="table" data-comp="projectHeaderTitle">
                        <div class="projectControler ">
                            <div class="nameProject primary hasColor " style="color:#f95a4e"><input
                                    data-comp="projectName" class="note" type="text" autocomplete="off"
                                    value="My Tasks"></div>
                            <div class="options_container"><a href="#" class="optionPoints"
                                                              data-action="displayContextMenu" data-type="active"
                                                              data-following="false" data-id="324834">• • •</a></div>
                        </div>
                    </div>
                </div>
            </div>
            <!--/view-->

        </div>
        <!--header_frame-->

        <!--sub_header_iframe-->
        <div class="sub_header_iframe" data-comp="listSubNav" style="width: 1040px;">
            <div class="view full_box">

                <!--sub_navigation-->
                <div class="center_box sub_navigation" data-comp="projectHeaderNavigation">
                    <div class="table">

                        <!--right-->
                        <div class="cell right">

                            <!--add_new_task-->
                            <div class="secondary_nav white add_new_task">
                                <ul>
                                    <li><a href="#addTask" class="trans can_edit_projects_and_tasks"
                                           data-orig_text="Add Task">Add Task</a></li>
                                </ul>
                            </div>
                            <!--/add_new_task-->

                        </div>
                        <!--/right-->

                    </div>
                </div>
                <!--/sub_navigation-->

            </div>
        </div>
        <!--/sub_header_iframe-->

        <!--content_frame-->
        <div class="content_frame" data-comp="listBody" data-scroll="true"
             style="height: 763px; top: 154px; width: 1040px;">
            <div class="full_box content">
                <div class="center_box">

                    <!--projectList-->
                    <div class="projectList" data-comp="listContent">
                        <div data-comp="newTaskList" class="newTaskList addItemBox" style="display:none">
                            <div class="row title"><h3>New task list</h3></div>
                            <div class="row add"><input type="text" autocomplete="off" value="" placeholder="List Title"
                                                        name="new_task_list_name">
                                <div class="secondary_nav">
                                    <ul class="ui-sortable">
                                        <li><a class="secondary" href="#cancelNewTaskList">cancel</a></li>
                                        <li><a class="primary" href="#addNewTaskList">save</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <ul class="connectedSortable ui-sortable" data-comp="taskList" data-task_list_id="null">

                            <%--todo change to all projects--%>
                            <c:forEach items="${user.items}" var="item">
                                <li task-id="${item.id}" class="taskListTask table is-done-false" data-project="Programming [road to WORK]">
                                    <span class="cell drag-handler ui-sortable-handle">
                                        <u class="dragBox">
                                            <i class="icon-icontt_menu"></i>
                                        </u>
                                    </span>
                                    <a class="cell task" data-comp="task_link" data-id="1712353" href="#/task/${item.id}">
                                        <span class="note input">${item.name} </span>
                                    </a>
                                    <span class="cell checkbox">
                                        <div class="checkbox_styled">
                                            <a type="checkbox" href="${pageContext.servletContext.contextPath}/item/delete?item-id=${item.id}"></a>
                                            <label for="${item.id}"></label>
                                        </div>
                                    </span>
                                </li>
                            </c:forEach>

                            <%--todo add is done tasks (not delete after check)--%>
                            <%--<li class="taskListTask table is-done-true" data-id="1690388"--%>
                                <%--data-comp="projecttaskListItem" data-username="bogdan.andrusiak "--%>
                                <%--data-avatar="https://app.trackingtime.co/v3/dispatcher/image?src=http://www.gravatar.com/avatar/11bd057027c752c3a62d2f6b3bdbe188.jpg?d=http://static.trackingtime.mobi/random_image_7.jpg&amp;w=200&amp;h=200"--%>
                                <%--data-name="Розібратися з Трекером | виконати перший таск"--%>
                                <%--data-project="Programming [road to WORK]" data-sortable_due_date="no_due_date"--%>
                                <%--data-end_date="" data-legible_due_date="No due date" data-is_done="true" data-pos="3">--%>
                                <%--<span class="cell drag-handler ui-sortable-handle" data-id="1690388" data-action="drag"><u--%>
                                        <%--class="dragBox"><i class="icon-icontt_menu"></i></u></span><span--%>
                                    <%--class="cell task" data-comp="task_link" data-id="1690388"><span data-id="1690388"--%>
                                                                                                    <%--class="note input">Розібратися з Трекером | виконати перший таск</span><span--%>
                                    <%--class="timeValues"></span></span><span class="cell play"><a--%>
                                    <%--data-action="track" data-id="1690388" class="playButton"><span class="play play_ic"><i--%>
                                    <%--class="icon-icontt_play"></i></span><span class="stop stop_ic"><i--%>
                                    <%--class="icon-icontt_stop"></i></span></a></span><span class="cell checkbox"><div--%>
                                    <%--class="checkbox_styled"><input type="checkbox" id="pv_task_1690388"--%>
                                                                   <%--data-id="1690388" name="pv_task_1690388"--%>
                                                                   <%--checked=""><label--%>
                                    <%--for="pv_task_1690388"></label></div></span></li>--%>
                        </ul>
                    </div>
                    <!--/projectList-->

                </div>
            </div>
        </div>
        <!--/content_frame-->

    </div>
    <!--/taskListDetailProject-->

    <!--TaskDetail-->
    <div class="listView column_b TaskDetail playing active task-archived-false showing-comments" id="TaskDetail"
         data-state="" data-view="fullView" data-display="side_view" style="left: 1580px;">

        <%-- todo I think using ajax - better way? --%>
        <c:forEach items="${user.items}" var="item">
            <div class="hideMore" task-id-more="${item.id}">
            <!--header_frame-->
            <div class="header_frame listHeader" data-comp="listHeader">
                <div class="full_box" data-comp="taskCard">
                    <div class="full_box view primary">
                        <div class="center_box">
                            <div class="table">

                                <div class="cell right">
                                    <div class="action" style="display: none;"><a onclick="navigateBack()" class="icon"><i
                                            class="icon-icontt_back"></i></a></div>
                                    <div class="action"><a href="#taskContextMenu" class="optionPoints"
                                                           data-action="displayTaskContextMenu" data-id="1712353"
                                                           data-project_id="324834" data-user_id="166684"
                                                           data-type="active">• • •</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="full_box view secondary">
                        <div class="center_box">
                            <div class="table">
                                <div class="cell left">
                                    <div class="playCard">
                                        <div class="title"><h3><a href="#changeProject" data-project_id="324834">Project name - <span style="color: red;">Not supported yet :(</span></a></h3>
                                            <h2><input type="text" autocomplete="off" name="name" class="note"
                                                       value="${item.name}"></h2></div>
                                        <span class="cell checkbox"><div class="checkbox_styled"><input type="checkbox"
                                                                                                        id="pv_task_1712353"
                                                                                                        data-id="1712353"
                                                                                                        name="pv_task_1712353"><label
                                                for="pv_task_1712353"></label></div></span>

                                    </div>
                                    <div class="subsTasksNav">
                                        <div class="secondary_nav rounded gray">
                                            <ul>
                                                <li><a data-comp="show-files">Files</a></li>
                                                <li><a data-comp="show-comments">Comments</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--header_frame-->

            <!--content_frame-->
            <div class="content_frame" data-comp="listBody" data-scroll="true" style="height: 637px; top: 245px;">
                <div class="full_box content">
                    <div class="center_box">

                        <!--taskItems-->


                            <div class="messegeComponent text subTaskList">
                                <div class="content">
                                    <div class="icoMessege"><img src="img/avatars/avatar-ttPRO_avatt-oldie.jpg"></div>
                                    <div class="textMessege"><h2><u><span style="color: red;">Not supported yet :(</span></u></h2>
                                        <%--<h2><a href="#addTodo"><u>Get Started!</u></a></h2>--%></div>
                                </div>
                            </div>

                            <div class="taskItems listContent draggable" data-comp="listContent">
                                <div class="commentsList">
                                    <ul class="comments-tab">
                                        <c:forEach items="${item.comments}" var="comment">
                                            <li class="myMessege" data-comp="commentItem" data-userid="166684">
                                                <div class="cell avatarImg">
                                                    <div class="avatar medium"><img src="<c:url value="/resources/img/avatar.png"/>"
                                                                                    width="30px" height="30px"></div>
                                                </div>

                                                <div class="cell messege"><p>${comment.description}</p><h4><fmt:formatDate value="${comment.createDate.time}" pattern="HH:mm dd-MM-yyyy" /></h4></div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>

                        </div>
                        <!--taskItems-->

                    </div>
                </div>
            </div>
            <!--/content_frame-->

            <!--footer_frame-->
            <div class="footer_frame" data-comp="listFooter">
                <div class="full_box footer">
                    <div class="center_box">

                        <!--subtaskForm-->
                        <form data-comp="subtaskForm" class="subtaskForm addItemBox">

                            <!--add-->
                            <div class="row add">
                                <input type="text" autocomplete="off" name="name" placeholder="File">
                                <input type="submit" value="add">
                            </div>
                            <!--/add-->

                        </form>
                        <!--/subtaskForm-->

                        <!--subtaskForm-->
                        <form action="${pageContext.servletContext.contextPath}/item/comment/create" method="post" data-comp="commentsForm" class="commentsForm addItemBox">
                            <input type="hidden" name="id" value="${item.id}">
                            <!--add-->
                            <div class="row add submitList">

                                <!--close-->
                                <a href="#closeCommentForm" class="closeExpand only_expanded">
                                    <i class="icon-icontt_close"></i>
                                </a>
                                <!--/close-->

                                <textarea type="text" name="description" placeholder="Comments" rows="1"
                                          autocomplete="off"></textarea>
                                <div class="only_expanded recipientList row" data-comp="recipientList"><h2>Notify People via
                                    Email</h2>
                                    <div class="labels">
                                        <ul></ul>
                                    </div>
                                </div>
                                <input type="submit" value="add">
                            </div>
                            <!--/add-->

                        </form>

                    </div>
                </div>
            </div>
            <!--/footer_frame-->

            <!-- /// -->

            <!--flap-->
            <div class="flap" data-comp="goBackView">
                <div class="back_ico">
                    <i class="icon-icontt_back"></i>
                </div>
            </div>
            <!--/flap-->
        </div>
        </c:forEach>

    </div>
    <!--/TaskDetail-->

    <%--editTask--%>
    <div class="listView FormCard popUp EditTaskView" id="EditTaskView" data-state="" data-view="popUpView" style="left: 0px; width: 1920px; margin-top:0;">

        <!--header_frame-->
        <div class="header_frame listHeader" data-comp="listHeader" style="margin-top: 60px; background-color: #fff;">

            <!--view-->
            <div class="full_box view">
                <div class="center_box">
                    <div class="table">

                        <!--left-->
                        <div class="cell left">

                            <!--page_tittle-->
                            <div class="page_title big">
                                <h2 data-comp="formTitle">New Task</h2>
                            </div>
                            <!--page_tittle-->

                        </div>
                        <!--/left-->

                        <!--right-->
                        <div class="cell right">

                            <!--action-->
                            <div class="action">
                                <a href="#back" class="icon"><i class="icon-icontt_close"></i></a>
                            </div>
                            <!--/action-->

                        </div>
                        <!--/right-->

                    </div>
                </div>
            </div>
            <!--/view-->

        </div>
        <!--header_frame-->

        <!--content_frame-->
        <div class="content_frame" data-comp="listBody" data-scroll="true" style="height: 500px; top: 122px; background-color: #fff;">
            <div class="full_box content formView">
                <div class="center_box">

                    <!--form-->
                    <form name="addTask" action="${pageContext.request.contextPath}/item/create" method="post" novalidate="novalidate">

                        <!--hidden_inputs-->
                        <input type="hidden" name="id" value="">
                        <input type="hidden" name="due_date" value="">
                        <input type="hidden" name="list_id" value="">
                        <input type="hidden" name="list_position" value="">
                        <!--/hidden_inputs-->



                        <!--name-->
                        <label for="taskName" class="primary">
                            <h4 class="trans" data-orig_text="Task Name">Task Name</h4>
                            <span class="icon">(*)</span>
                            <input type="text" autocomplete="off" required="" name="taskName" data-orig_text="Task Name" placeholder="Task Name" aria-required="true">
                        </label>
                        <!--name-->

                        <label for="project">
                            <h4 class="trans" data-orig_text="project">Project</h4>
                            <select name="project">
                                <option value="USD">Default</option>
                                <option value="EUR">Euro - EUR</option>
                            </select>
                        </label>

                        <!--project_id-->
                        <label for="project_id" data-comp="project-block" style="display: none;">
                            <h4 class="trans" data-orig_text="Project">Project</h4>
                            <input type="hidden" name="project_id" value="324834">
                            <input type="text" autocomplete="off" name="project_name" placeholder="Project Name" readonly="readonly">
                        </label>
                        <!--/project_id-->

                        <!--submit-->
                        <input type="submit" value="">
                        <!--/submit-->

                    </form>
                    <!--/form-->

                    <!--subtask_contianer-->
                    <label class="subtasksContainer" data-comp="subtasksContainer">
                        <h4><u class="trans" data-orig_text="To-dos">Comments</u></h4>
                        <p class="trans" data-orig_text="Enter comments in the field below.">Enter comments in the field below.</p>
                        <textarea name="comments" style="overflow-x: hidden; word-wrap: break-word; height: 100px;"></textarea>
                    </label>
                    <!--/subtask_contianer-->

                </div>
            </div>
        </div>
        <!--/content_frame-->

        <!--footer_frame-->
        <div class="footer_frame" data-comp="listFooter" style="background-color: #fff;">
            <div class="full_box footer">
                <div class="center_box">

                    <!--formFooter-->
                    <div class="formFooter right">
                        <a href="#delete" class="trans form_button" data-orig_text="Delete" style="display: none;">Delete</a>
                        <a href="#open" class="trans form_button" data-orig_text="Re-open" style="display: none;">Re-open</a>
                        <a href="#close" class="trans form_button" data-orig_text="Mark as done" style="display: none;">Mark as done</a>
                        <a href="#add" class="trans form_button" data-orig_text="Add" style="position: absolute; right: 20px;">Add</a>
                    </div>
                    <!--/formFooter-->

                </div>
            </div>
        </div>
        <!--/footer_frame-->

    </div>
    <%--/editTask--%>
</div>
<%--/main content--%>

<script src="<c:url value="/resources/js/app.js"/>"></script>

</body>
</html>