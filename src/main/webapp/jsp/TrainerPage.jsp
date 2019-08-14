<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 18.07.2019
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sa" uri="http://mycompany.com" %>
<c:set var="language" value="${not empty param.language ? param.language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="TrainerPage"/>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/SetAmountTag.tld" prefix="sam" %>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>FTH</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/TrainerPage/style.css">
    <link rel="stylesheet" href="../css/TrainerPage/normalize.css">
</head>
<form class="actions__language">
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
</form>
<body>
<header class="page-header">
    <section class="couch-info"> <%--trainer init--%>
    </section>
    <section class="actions">
        <p class="actions__text">
            <fmt:message key="trainer.label.headUp"/>
        </p>
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
        <ul class="actions__profile">
            <li class="actions__variant">
                <button type="button" class="actions__button settings-button">
                    <ul class="settings">
                        <li class="settings__variant">
                            <a href="#" class="profile"><fmt:message key="trainer.label.changeData"/></a>
                        </li>
                        <li class="settings__variant">
                            <a href="#" class="trainings"><fmt:message key="trainer.label.createExercise"/></a>
                        </li>
                    </ul>
                </button>
            </li>
            <li class="actions__variant">
                <button type="button" class="actions__button exit-button"></button>
            </li>
        </ul>
    </section>
</header>
<main class="page-main">
    <section class="client-base">
        <form class="sort">
            <p class="sort-name">
                <fmt:message key="trainer.label.sortAll"/>
            </p>
            <div class="sort-wrapper__name">
                <label class="sort-label">
                    <input class="conditionRefresh name" id="name" type="radio" name="sort-name"/>
                    <div class="sort-div">
                        <fmt:message key="trainer.label.byName"/>
                    </div>
                </label>
                <label class="sort-label">
                    <input class="conditionRefresh" id="surname" type="radio" name="sort-name"/>
                    <div class="sort-div">
                        <fmt:message key="trainer.label.bySurname"/>
                    </div>
                </label>
            </div>
            <div class="sort-wrapper">
                <label class="sort-label">
                    <input class="conditionRefresh" id="expired" type="radio" name="sort"/>
                    <div class="sort-div">
                        <fmt:message key="trainer.label.expired"/>
                    </div>
                </label>
                <label class="sort-label">
                    <input class="conditionRefresh" id="actual" type="radio" name="sort"/>
                    <div class="sort-div">
                        <fmt:message key="trainer.label.actual"/>
                    </div>
                </label>
                <label class="sort-label">
                    <input class="conditionRefresh" id="requested" type="radio" name="sort"/>
                    <div class="sort-div">
                        <fmt:message key="trainer.label.requestedExercise"/>
                    </div>
                </label>
                <label class="sort-label">
                    <input class="conditionRefresh each_And_Every" id="each_And_Every" type="radio" name="sort"/>
                    <div class="sort-div">
                        <fmt:message key="trainer.label.eachAndEvery"/>
                    </div>
                </label>
            </div>
        </form>
        <ul class="client-base__list"> <%-- Клиенты--%> </ul>
    </section>
    <section class="client-info">
        <div class="client-info__wrapper">
            <h2></h2>
            <p>
            </p>
        </div>
    </section>
</main>
<footer>

</footer>
<div class="modal-content-profile">
    <button class="modal-content-profile-close" type="button" title="Закрыть"><fmt:message
            key="trainer.label.closeModal"/></button>
    <h2 class="modal-content-title"><fmt:message key="trainer.label.changingData"/></h2>
    <form class="change-form" action="" method="post">
        <input class="change-name" type="text" name="name" placeholder="Введите новое имя"/>
        <input class="change-surname" type="text" name="surname" placeholder="Введите новую фамилию"/>
        <input class="change-email" type="email" name="email" placeholder="Введите новый email"/>
        <input class="change-password" type="password" name="password" placeholder="Введите новый пароль"/>
        <input class="change-password--repeat" type="password" name="password" placeholder="Повторите новый пароль"/>
        <%--        <button class="modal-button wide--button" type="submit">Подтвердить</button>--%>
    </form>
</div>
<div class="modal-content-trainings">
    <button class="modal-content-trainings-close" type="button" title="Закрыть"><fmt:message
            key="trainer.label.closeModal"/></button>
    <h2 class="modal-content-title"><fmt:message key="trainer.label.creationDrill"/></h2>
    <form class="change-form" action="" method="post">
        <input class="change-train newDrillName" type="text" maxlength="256" value="" name="train"
               placeholder="Введите название упражнения"/>
        <p><fmt:message key="trainer.label.choiceMuscleGroup"/>
            <select class="muscleGroupId">
                <option value="1">
                    <fmt:message key="trainer.label.breast"/>
                </option>
                <option value="2">
                    <fmt:message key="trainer.label.back"/>
                </option>
                <option value="3">
                    <fmt:message key="trainer.label.shoulders"/>
                </option>
                <option value="4">
                    <fmt:message key="trainer.label.biceps"/>
                </option>
                <option value="5">
                    <fmt:message key="trainer.label.triceps"/>
                </option>
                <option value="6">
                    <fmt:message key="trainer.label.legs"/>
                </option>
                <option value="7">
                    <fmt:message key="trainer.label.press"/>
                </option>
            </select>
        </p>
        <button class="modal-button wide--button createNewDrill" type="button"><fmt:message
                key="trainer.label.confirmData"/></button>
    </form>
</div>
<div class="modal-content-delete delete-user">
    <button class="modal-content-delete-close" type="button" title="Закрыть"><fmt:message
            key="trainer.label.closeModal"/></button>
    <p class="modal-content-delete__text">
        <fmt:message key="trainer.label.deleteUserQuestion"/>
    </p>
    <div class="delete-wrapper">
        <button class="modal-button delete-button" type="button"><fmt:message key="trainer.label.deleteUser"/></button>
        <button class="modal-button deny-button" type="button"><fmt:message key="trainer.label.denyRequest"/>с</button>
        <button class="modal-button fire-button" type="button"><fmt:message key="trainer.label.fireSeason"/></button>
        <button class="modal-button bun-button" type="button" hidden><fmt:message key="trainer.label.ban"/></button>
        <button class="modal-button cancel-button" type="button"><fmt:message key="trainer.label.cancel"/></button>
    </div>
</div>
<div class="modal-content-give">
    <button class="modal-content-give-close" type="button" title="Закрыть"><fmt:message
            key="trainer.label.closeModal"/></button>
    <form class="modal-give__form">
        <table class="modal-give__table" cellspacing="0" cellpadding="5" border="1" bordercolor="#9c9c9c">
            <tr>
                <th>
                    <fmt:message key="trainer.label.mainDrillName"/>
                </th>
                <th valign="center">
                    <span class="medium-image approaches-img"></span>
                </th>
                <th>
                    <span class="medium-image repeat-img"></span>
                </th>
                <th>
                    <span class="medium-image rest-img"></span>
                </th>
                <th>
                    <span class="medium-image weight-img"></span>
                </th>
            </tr>
            <tr class="exercises">
            </tr>
        </table>
        <div class="modal-give__buttons">
            <button type="button" class="modal-button outButton" disabled="disabled"><fmt:message
                    key="trainer.label.outExercise"/></button>
            <button type="reset" class="modal-button clearButton"><fmt:message key="trainer.label.clear"/></button>
        </div>
    </form>
    <div class="modal-give__wrapper">
        <ul class="modal-give__list">
            <li class="modal-give__point muscleGroup" id="1">
                <fmt:message key="trainer.label.chestGroup"/>
                <div class="modal-give__menu">
                    <ul class="modal-give__hide__menu group1">
                    </ul>
                </div>
            </li>
            <li class="modal-give__point muscleGroup" id="2">
                <fmt:message key="trainer.label.backGroup"/>
                <div class="modal-give__menu">
                    <ul class="modal-give__hide__menu group2">
                    </ul>
                </div>
            </li>
            <li class="modal-give__point muscleGroup" id="3">
                <fmt:message key="trainer.label.shouldersGroup"/>
                <div class="modal-give__menu">
                    <ul class="modal-give__hide__menu group3">
                    </ul>
                </div>
            </li>
            <li class="modal-give__point muscleGroup" id="4">
                <fmt:message key="trainer.label.bicepsGroup"/>
                <div class="modal-give__menu">
                    <ul class="modal-give__hide__menu group4">
                    </ul>
                </div>
            </li>
            <li class="modal-give__point muscleGroup" id="5">
                <fmt:message key="trainer.label.tricepsGroup"/>
                <div class="modal-give__menu">
                    <ul class="modal-give__hide__menu group5">
                    </ul>
                </div>
            </li>
            <li class="modal-give__point muscleGroup" id="6">
                <fmt:message key="trainer.label.legGroup"/>
                <div class="modal-give__menu">
                    <ul class="modal-give__hide__menu group6">
                    </ul>
                </div>
            </li>
            <li class="modal-give__point muscleGroup" id="7">
                <fmt:message key="trainer.label.pressGroup"/>
                <div class="modal-give__menu">
                    <ul class="modal-give__hide__menu group7">
                    </ul>
                </div>
            </li>
        </ul>
        <form class="modal-give__train-form">
            <table class="modal-give__table-form" cellpadding="5">
                <tr>
                    <td class="modal-give__table-form--td">
                        <fmt:message key="trainer.label.setsAmount"/><span class="small-image approaches-img"></span>
                    </td>
                    <td>
                        <select class="modal-give__select setAmount" disabled>
                            <sam:setAmount from="3" to="20"/>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="modal-give__table-form--td">
                        <fmt:message key="trainer.label.repeatsAmount"/><span class="small-image repeat-img"></span>
                    </td>
                    <td>
                        <select class="modal-give__select repeatAmount" disabled>
                            <sam:setAmount from="1" to="100"/>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="modal-give__table-form--td">
                        <fmt:message key="trainer.label.restedTime"/><span class="small-image rest-img"></span>
                    </td>
                    <td>
                        <select class="modal-give__select restTime" disabled>
                            <sam:setAmount restTime="true"/>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="modal-give__table-form--td">
                        <fmt:message key="trainer.label.startWeight"/><span class="small-image weight-img"></span>
                    </td>
                    <td class="modal-give__table-form--input">
                        <input type="text" maxlength="5" class="modal-give__select workWeight" disabled/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="button" class="modal-button addExercise"><fmt:message
                                key="trainer.label.addExercise"/></button>
                    </td>
                    <td>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div class="modal-content-extend">
    <button class="modal-content-extend-close" type="button" title="Закрыть"><fmt:message
            key="trainer.label.closeModal"/></button>
    <h2 class="modal-content-title"><fmt:message key="trainer.label.seasonPass"/></h2>
    <form class="extend-form" action="" method="post">
        <label class="extend-label">
            <input type="checkbox" name="extend-checkbox" class="extend-checkbox"/>
            <span></span>
            <div class="extend-div">
                <fmt:message key="trainer.label.unlim"/>
            </div>
        </label>
        <label class="extend-label__train">
            <input class="extend-train" type="text" maxlength="3" name="train" value="1"/>
            <div class="extend-div__train">
                <fmt:message key="trainer.label.exerciseAmount"/>
            </div>
        </label>
        <label class="extend-label__date">
            <span class="extend-span"><fmt:message key="trainer.label.fromDate"/></span>
            <input class="extend-date dateFrom" type="date" name="date" readonly>
        </label>
        <label class="extend-label__date">
            <span class="extend-span"><fmt:message key="trainer.label.tillDate"/></span>
            <input class="extend-date dateTill" type="date" name="extend-date"/>
        </label>
        <button class="modal-button extend-button" type="button"><fmt:message key="trainer.label.extendPass"/></button>
    </form>
</div>
<div class="modal-overlay"></div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/lib/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/lib/cookie/jquery.cookie.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/TrainerPage/variables.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/TrainerPage/TrainerPage.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/TrainerPage/ClientButton.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/TrainerPage/SeasonExtensionButton.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/TrainerPage/ExerciseOut.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/TrainerPage/RemoveButton.js"></script>
</body>
</html>
