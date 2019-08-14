<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 14.07.2019
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="TrainerAuth"/>
<%@ page pageEncoding="UTF-8" %>
<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <title>FTH Log In</title>
    <link rel="stylesheet" href="../css/TrainerAuth.css" />
    <%--    <link rel="stylesheet" href="../css/style.css"/>--%>
</head>
<form class="actions__language">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
</form>
<body>
<h1><fmt:message key="trainerAuth.label.register"/></h1>
<div id="wrapper">
    <form method="post"  action="" class="form" id="signIn" >
        <input type="text" class="login" name="user" placeholder="e-mail"/>
        <input type="password" class="password" name="pass" placeholder="password"/>

        <button class="login-button" type="button">&#xf0da;</button>
    </form>
</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript" src="../js/TrainerAuth.js"></script>
</body>
</html>