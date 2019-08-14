<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 30.06.2019
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<%@ page pageEncoding="UTF-8" %>
<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <title>FTH Log in/Sign up</title>


    <link href="../css/ClientAuth.css" rel="stylesheet" type="text/css">
<%--    <style>--%>
<%--        <%@include file="../css/ClientAuth.css" %>--%>
<%--    </style>--%>
</head>
<body>
<form class="actions__language">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
</form>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>

<div class="container">
    <div class="frame">
        <div class="nav">
            <ul class="links">
                <li class="signin-active"><a class="btn"><fmt:message key="singin.label.singIn" /></a></li>
                <li class="signup-inactive"><a class="btn"><fmt:message key="singup.label.singUp" /> </a></li>
            </ul>
        </div>
        <div ng-app ng-init="checked = false">
            <form class="form-signin" name="form">
                <label><fmt:message key="singin.label.emailSingIn"/></label>
                <input class="form-styling login" maxlength="250" type="text" name="email" placeholder=""/>

                <label><fmt:message key="singin.label.passwordSingIn"/></label>
                <input class="form-styling password" maxlength="30" type="password" name="password" placeholder=""/>

                <div class="btn-animate">
                    <a class="btn-signin">Sign in</a>
                </div>
            </form>

            <form class="form-signup" name="form">
                <label><fmt:message key="singup.label.nameSingUp"/>*</label>
                <input class="form-styling nameSingUp" maxlength="20" type="text" name="name" placeholder=""/>

                <label><fmt:message key="singup.label.surnameSingUp"/>*</label>
                <input class="form-styling surnameSingUp" maxlength="30" type="text" name="surname" placeholder=""/>

                <label><fmt:message key="singup.label.emailSingUp"/>*</label>
                <input class="form-styling emailSingUp" maxlength="250" type="text" name="email" placeholder=""/>

                <label><fmt:message key="singup.label.passwordSingUp"/>*</label>
                <input class="form-styling passwordSingUp" maxlength="30" type="password" name="password"
                       placeholder=""/>

                <label><fmt:message key="singup.label.confirmPasswordSingUp"/>*</label>
                <input class="form-styling confirmPasswordSingUp" maxlength="30" type="password" name="confirmpassword"
                       placeholder=""/>

                <a ng-click="checked = !checked" class="btn-signup">Sign Up</a>
            </form>

            <div class="success">
                <svg width="270" height="270" xmlns="http://www.w3.org/2000/svg"
                     x="0px" y="0px"
                     viewBox="0 0 60 60" id="check" ng-class="checked ? 'checked' : ''"/>
                <path fill="#ffffff" d="M40.61,23.03L26.67,36.97L13.495,23.788c-1.146-1.147-1.359-2.936-0.504-4.314
                  c3.894-6.28,11.169-10.243,19.283-9.348c9.258,1.021,16.694,8.542,17.622,17.81c1.232,12.295-8.683,22.607-20.849,22.042
                  c-9.9-0.46-18.128-8.344-18.972-18.218c-0.292-3.416,0.276-6.673,1.51-9.578"/>
                <div class="successtext">
                    <p> Thanks for signing up! Check your email for confirmation.</p>
                </div>
            </div>
        </div>



        <!--      <div>-->
        <!--        <div class="cover-photo"></div>-->
        <!--        <div class="profile-photo"></div>-->
        <!--        <h1 class="welcome">Welcome, Chris</h1>-->
        <!--        <a class="btn-goback" value="Refresh" onClick="history.go()">Go back</a>-->
        <!--      </div>-->
    </div>

    <a id="refresh" value="Refresh" onClick="history.go()">
        <svg class="refreshicon" version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg"
             x="0px" y="0px"
             width="25px" height="25px" viewBox="0 0 322.447 322.447" style="enable-background:new 0 0 322.447 322.447;"
             xml:space="preserve">
         <path d="M321.832,230.327c-2.133-6.565-9.184-10.154-15.75-8.025l-16.254,5.281C299.785,206.991,305,184.347,305,161.224
                c0-84.089-68.41-152.5-152.5-152.5C68.411,8.724,0,77.135,0,161.224s68.411,152.5,152.5,152.5c6.903,0,12.5-5.597,12.5-12.5
                c0-6.902-5.597-12.5-12.5-12.5c-70.304,0-127.5-57.195-127.5-127.5c0-70.304,57.196-127.5,127.5-127.5
                c70.305,0,127.5,57.196,127.5,127.5c0,19.372-4.371,38.337-12.723,55.568l-5.553-17.096c-2.133-6.564-9.186-10.156-15.75-8.025
                c-6.566,2.134-10.16,9.186-8.027,15.751l14.74,45.368c1.715,5.283,6.615,8.642,11.885,8.642c1.279,0,2.582-0.198,3.865-0.614
                l45.369-14.738C320.371,243.946,323.965,236.895,321.832,230.327z"/>
    </svg>
    </a>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.14/angular.min.js'></script>
<script type="text/javascript" src="../js/ClientAuth.js"></script>
</body>
</html>


