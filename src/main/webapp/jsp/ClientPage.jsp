<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 10.08.2019
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="ClientPage"/>
<%@ page pageEncoding="UTF-8" %>
<html lang="${language}">
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>FTH</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="../css/TrainerPage/normalize.css"/>
    <link rel="stylesheet" href="../css/ClientPage/style.css"/>
</head>
<form class="actions__language">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
</form>
<body>
<header class="page-header">
    <span class="actions__language" hidden>
          <ul class="language">
            <li class="language__stroke">
              <button class="language__choice russian">Русский</button>
            </li>
            <li class="language__stroke">
              <button class="language__choice english">English</button>
            </li>
          </ul>
        </span>

</header>
<main class="page-main">
    <div class="page-main__wrapper">
        <div class="user__wrapper">
            <section class="user-info">

            </section>

            <section class="user-menu">
                <ul class="user-menu__list">
                    <li class="user-menu__action startExercise" hidden>
                        <fmt:message key="client.label.start"/>
                    </li>
                    <li class="user-menu__action finishExercise" hidden>
                        <fmt:message key="client.label.finish"/>
                    </li>
                    <li class="user-menu__action requestExercise" hidden>
                        <fmt:message key="client.label.request"/>
                    </li>
                    <li class="user-menu__action discardExercise" hidden>
                        <fmt:message key="client.label.discard"/>
                    </li>
                    <li class="user-menu__action changeTrainer">
                        <fmt:message key="client.label.changeTrainer"/>
                    </li>
                    <li class="user-menu__action changeData">
                        <fmt:message key="client.label.changeData"/>
                    </li>
                    <li class="user-menu__action exit">
                        <fmt:message key="client.label.out"/>
                    </li>
                </ul>
            </section>
        </div>
        <section class="user-text-area">

            <div class="user-text-area__wrapper">
                <h2 class="user-text-area__title"></h2>
            </div>
        </section>
    </div>
    <div class="page-main__training">
        <div class="page-main__training--left drillList">

        </div>
        <div class="page-main__training--right setGroup">


        </div>
    </div>
</main>
<footer>

</footer>

<div class="modal-choice">
    <button class="modal-choice-close tClose" type="button" title="Закрыть">Закрыть</button>
    <h2 class="modal-content-title"><fmt:message key="client.label.choice"/></h2>
    <ul class="modal-choice__choose trainerList">
    </ul>
    <button class="button trainerButton" type="submit"><fmt:message key="client.label.submit"/></button>
</div>
<div class="modal-overlay"></div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript" src="../js/lib/cookie/jquery.cookie.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/ClientPage/ClientPage.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/ClientPage/ExerciseIn.js"></script>
<script>
    $(".user-menu__action").on("click", function () {
        $(".user-menu__action").removeClass("selected");
        $(this).addClass("selected");
    });

    $(".training-list__stroke").on("click", function () {
        $(".training-list__stroke").removeClass("selected");
        $(this).addClass("selected");
    });
</script>
</body>
</html>

