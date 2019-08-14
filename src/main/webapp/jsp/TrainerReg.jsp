<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 14.08.2019
  Time: 4:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="TrainerReg"/>
<%@ page pageEncoding="UTF-8" %>
<html lang="${language}">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/TrainerReg.css"/>
</head>
<form class="actions__language">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
</form>
<body>
<div class="container">
    <header>
        <h1>
            <a href="#">
                <img src="http://tfgms.com/sandbox/dailyui/logo-1.png" alt="Authentic Collection">
            </a>
        </h1>
    </header>
    <h1 class="text-center"><fmt:message key="trainerReg.label.register"/></h1>
    <form class="registration-form">
        <label class="col-one-half">
            <span class="label-text"><fmt:message key="trainerReg.label.name"/>*</span>
            <input class="nameSingUp" maxlength="20" type="text" name="firstName">
        </label>
        <label class="col-one-half">
            <span class="label-text"><fmt:message key="trainerReg.label.surname"/>*</span>
            <input class="surnameSingUp" maxlength="30" type="text" name="lastName">
        </label>
        <label>
            <span class="label-text"><fmt:message key="trainerReg.label.email"/>*</span>
            <input class="emailSingUp" maxlength="250" type="text" name="email">
        </label>
        <label class="password">
            <span class="label-text"><fmt:message key="trainerReg.label.password"/>*</span>
            <button class="toggle-visibility" title="toggle password visibility" tabindex="-1">
                <span class="glyphicon glyphicon-eye-close"></span>
            </button>
            <input class="passwordSingUp" maxlength="30" type="password" name="password">
        </label>
        <label class="password confirm">
            <span class="label-text"><fmt:message key="trainerReg.label.confirm"/>*</span>
            <button class="toggle-visibility" title="toggle password visibility" tabindex="-1">
                <span class="glyphicon glyphicon-eye-close"></span>
            </button>
            <input class="confirmPasswordSingUp" maxlength="30" type="password" name="password">
        </label>
        <div class="text-center">
            <button class="regButton" type="button"><fmt:message key="trainerReg.label.up"/></button>
        </div>
    </form>
</div>
</body>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.14/angular.min.js'></script>
<script type="text/javascript" src="../js/TrainerReg.js"></script>
</html>
