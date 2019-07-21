<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 18.07.2019
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Личный кабинет</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
<%--    <style>--%>
<%--    <%@include file="../css/TrainerPage/style.css" %>--%>
<%--    <%@include file="../css/TrainerPage/normalize.css" %>--%>
<%--    </style>--%>
    <link rel="stylesheet" href="../css/TrainerPage/style.css" />
    <link rel="stylesheet" href="../css/TrainerPage/normalize.css" />
</head>
<body>
<header class="page-header">
    <section class="couch-info" id="trainer"></section>
    <nav class="actions">
        <ul class="actions__list">
            <li class="actions__variant">
                <button type="button" class="actions__button">Действие</button>
            </li>
            <li class="actions__variant">
                <button type="button" class="actions__button">Действие</button>
            </li>
            <li class="actions__variant">
                <button type="button" class="actions__button">Действие</button>
            </li>
            <li class="actions__variant">
                <button type="button" class="actions__button">Действие</button>
            </li>
        </ul>
        <ul class="actions__profile">
            <li class="actions__variant">
                <button type="button" class="actions__button exit-button">Выход</button>
            </li>
        </ul>
    </nav>
</header>
<main class="page-main">
    <section class="client-base">
        <ul class="client-base__list" id="clients"></ul>
    </section>
    <section class="client-info">
        <div class="client-info__wrapper">
            <p>
                Информация, поступающая с сервера при нажатии на клиента.
            </p>
        </div>
    </section>
</main>
<footer>

</footer>
<script>

</script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript" src="../js/TrainerPage.js"></script>
</body>
</html>
