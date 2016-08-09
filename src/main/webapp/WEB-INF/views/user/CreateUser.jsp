<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">

    <title>Registration: Tracker</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


    <script>
        $(
                $.ajax('../json', {
                    method : 'get',
                    complete: function (data) {
                        var result = "<option value = \"\"selected>Your country</option>";
                        var countries = JSON.parse(data.responseText);
                        for (var i=0; i!=countries.length; ++i) {
                            result += "<option value=\"" + countries[i].countryName + "\">" + countries[i].countryName + "</option>";
                        }
                        var select = document.getElementById("country-list");
                        select.innerHTML = result;
                    }
                })
        );

        function validate() {
            var result = true;
            if ($( "#country-list option:selected" ).text() == "Your country") {
                result = false;
            }
            return result;
        }
    </script>
</head>
<body>
    <c:if test="${error != ''}">
        <div class="error">
            <c:out value="${error}"/>
        </div>
    </c:if>

    <div class="logo">
        <div><span style="color:#61bb46;">T</span><span style="color:#fdb827;">r</span><span style="color:#f5821f;">a</span><span style="color:#eb5e2f;">c</span><span style="color:#e03a3e;">k</span><span style="color:#963d97;">e</span><span style="color:#009ddc;">r</span></div>
    </div>
    <div class="card-auth">
        <form class="auth-form" action="" method="post" onsubmit="return validate()">
            <div class="row row-name">
                <span class="icon icon-name"><img src="<c:url value="/resources/img/icon-name.png"/>"></span>
                <input type="text" name="name" placeholder="Name" required><br>
            </div>
            <div class="row row-login">
                <span class="icon icon-login"><img src="<c:url value="/resources/img/icon-login.png"/>"></span>
                <input type="text" name="login" placeholder="Login" required><br>
            </div>
            <div class="row row-login">
                <span class="icon icon-email"><img src="<c:url value="/resources/img/icon-email.png"/>"></span>
                <input type="email" name="email" placeholder="Email" required><br>
            </div>

            <%----%>
            <div class="row row-country">
                <span class="icon icon-country"><img src="<c:url value="/resources/img/icon-country.png"/>"></span>

                <label for="country">
                    <select id="country-list" name="country" required oninvalid="this.setCustomValidity('Please select your country from the list.')"
                            oninput="setCustomValidity('')">
                        <%--data from ajax--%>
                    </select>

                </label>
            </div>

            <div class="row row-city">
                <span class="icon"><img src="<c:url value="/resources/img/icon-city.png"/>"></span>
                <input type="text" name="city" placeholder="City" required><br>
            </div>
            <%----%>

            <div class="row row-password">
                <span class="icon icon-password"><img src="<c:url value="/resources/img/icon-password.png"/>"></span>
                <input type="password" name="password" placeholder="Password" required><br>
            </div>
            <div class="row row-submit">
                <input type="submit" value="Register"><br>
            </div>
        </form>
        <span class="create-user"><a href="${pageContext.servletContext.contextPath}/signin">I have an account.</a></span>
    </div>
</body>
</html>
