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
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>jQuery File Upload Example</title>

    <h1>This is &lt/h1></h1>
</head>
<body>
<form action="http://localhost:8080/FTH/start" method="POST" enctype="multipart/form-data">
    <form action="upload" method="post" enctype="multipart/form-data">
        <input type="text" name="description" />
        <input type="file" name="file" />
        <input type="submit" name="ss"/>
    </form>
</form>
</body>
</html>
