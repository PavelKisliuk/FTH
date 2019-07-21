<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 19.07.2019
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" /> <%--!!!имя resourcebundle в папке properties--%>
<%@ page pageEncoding="UTF-8" %>
<html lang="${language}">
<%--Всё что выше нужно чтобы локализаций работала--%>
<head>
    <title>Title</title>

</head>
<body>
<%--Хрень ниже вставляешь на страничке там появится выскакивающая штучка для смены языка--%>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
</form>

<form>
    <label for="nameSingUp"><fmt:message key="singup.label.nameSingUp" /></label>
    <input class="form-styling" type="text" name="name" id="nameSingUp" placeholder=""/>
</form>

</body>
</html>
