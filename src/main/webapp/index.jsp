<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 13.08.2019
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="index"/>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>FTH</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="css/TrainerPage/normalize.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<form class="actions__language">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
</form>
<header class="page-header__login">index.label.fitness
    <h2 class="page-header__title"><fmt:message key="index.label.fitness"/></h2>
    <h3 class="page-header__welcome-title"><fmt:message key="index.label.title"/></h3>
</header>
<main class="page-main__login">
    <a class="page-main__staff" href="jsp/TrainerAuth.jsp">
        <button type="button" class="button-login"><fmt:message key="index.label.astrainer"/></button>
    </a>
    <a class="page-main__staff" href="jsp/TrainerReg.jsp">
        <button type="button" class="button-login"><fmt:message key="index.label.betrainer"/></button>
    </a>
    <a class="page-main__user" href="jsp/ClientAuth.jsp">
        <button type="button" class="button-login"><fmt:message key="index.label.asclient"/></button>
    </a>
</main>
</body>
<script>
</script>
</html>

