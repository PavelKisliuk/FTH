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
<fmt:setBundle basename="text" />
<%@ page pageEncoding="UTF-8" %>
<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <title>Log in</title>
    <link rel="stylesheet" href="../css/TrainerAuth.css" />
</head>
<body>
<h1>Sign In Form</h1>
<div id="wrapper">
    <form method="post"  action="" class="form" id="signIn" >
        <input type="text" class="login" name="user" placeholder="e-mail"/>
        <input type="password" class="password" name="pass" placeholder="password"/>

        <button class="login-button" type="button">&#xf0da;</button>

        <p>forgot your password? <a href="#">click here</a></p>
    </form>
</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript" src="../js/TrainerAuth.js"></script>
</body>
</html>