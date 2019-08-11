<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 18.07.2019
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/UserInfo.tld" prefix="ui" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Личный кабинет</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/TrainerPage/style.css">
    <link rel="stylesheet" href="../css/TrainerPage/normalize.css">
</head>
<body>
<header class="page-header">
    <section class="couch-info"> <%--trainer init--%>
    </section>
    <section class="actions">
        <p class="actions__text">
            Fitness trainer helper
        </p>
        <span class="actions__language">
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
                            <a href="#" class="profile">Профиль</a>
                        </li>
                        <li class="settings__variant">
                            <a href="#" class="trainings">Тренировки</a>
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
                Упорядочить:
            </p>
            <div class="sort-wrapper__name">
                <label class="sort-label">
                    <input class="conditionRefresh name" id="name" type="radio" name="sort-name"/>
                    <div class="sort-div">
                        По имени
                    </div>
                </label>
                <label class="sort-label">
                    <input class="conditionRefresh" id="surname" type="radio" name="sort-name"/>
                    <div class="sort-div">
                        По фамилии
                    </div>
                </label>
            </div>
            <div class="sort-wrapper">
                <label class="sort-label">
                    <input class="conditionRefresh" id="expired" type="radio" name="sort"/>
                    <div class="sort-div">
                        Истекшие
                    </div>
                </label>
                <label class="sort-label">
                    <input class="conditionRefresh" id="actual" type="radio" name="sort"/>
                    <div class="sort-div">
                        Действительные
                    </div>
                </label>
                <label class="sort-label">
                    <input class="conditionRefresh" id="requested" type="radio" name="sort"/>
                    <div class="sort-div">
                        Запросившие
                    </div>
                </label>
                <label class="sort-label">
                    <input class="conditionRefresh each_And_Every" id="each_And_Every" type="radio" name="sort"/>
                    <div class="sort-div">
                        Показать всех
                    </div>
                </label>
            </div>
        </form>
        <ul class="client-base__list"> <%-- Клиенты--%> </ul>
    </section>
    <section class="client-info">
        <div class="client-info__wrapper">
            <h2>Информация о сайте</h2>
            <p>
                Информация, поступающая с сервера при нажатии на клиента.
            </p>
        </div>
    </section>
</main>
<footer>

</footer>
<div class="modal-content-profile">
    <button class="modal-content-profile-close" type="button" title="Закрыть">Закрыть</button>
    <h2 class="modal-content-title">Изменение данных</h2>
    <form class="change-form" action="" method="post">
        <input class="change-name" type="text" name="name" placeholder="Введите новое имя"/>
        <input class="change-surname" type="text" name="surname" placeholder="Введите новую фамилию"/>
        <input class="change-email" type="email" name="email" placeholder="Введите новый email"/>
        <input class="change-password" type="password" name="password" placeholder="Введите новый пароль"/>
        <input class="change-password--repeat" type="password" name="password" placeholder="Повторите новый пароль"/>
        <button class="modal-button wide--button" type="submit">Подтвердить</button>
    </form>
</div>
<div class="modal-content-trainings">
    <button class="modal-content-trainings-close" type="button" title="Закрыть">Закрыть</button>
    <h2 class="modal-content-title">Настройки</h2>
    <form class="change-form" action="" method="post">
        <input class="change-train" type="text" name="train" placeholder="Введите название тренировки"/>
        <p>Выбирете мышечную группу
            <select>
                <option>
                    Бицепсы
                </option>
                <option>
                    Трицепсы
                </option>
                <option>
                    Трапеции
                </option>
                <option>
                    Грудные
                </option>
                <option>
                    Пресс
                </option>
            </select>
        </p>
        <button class="modal-button wide--button" type="submit">Подтвердить</button>
    </form>
</div>
<div class="modal-content-delete">
    <button class="modal-content-delete-close" type="button" title="Закрыть">Закрыть</button>
    <p class="modal-content-delete__text">
        Удалить выбранного пользователя?
    </p>
    <div class="delete-wrapper">
        <button class="modal-button delete-button" type="button">Удалить</button>
        <button class="modal-button annul-button" type="button">Отвергнуть запрос</button>
        <button class="modal-button fire-button" type="button">Сжечь абонемент</button>
        <button class="modal-button bun-button" type="button">Забанить</button>
        <button class="modal-button cancel-button" type="button">Отмена</button>
    </div>
</div>
<div class="modal-content-give">
    <button class="modal-content-give-close" type="button" title="Закрыть">Закрыть</button>
    <form class="modal-give__form">
        <table class="modal-give__table" cellspacing="0" cellpadding="5" border="1" bordercolor="#9c9c9c">
            <tr>
                <th>
                    Название:
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
            <button type="button" class="modal-button outButton" disabled="disabled">Выдать</button>
            <button type="reset" class="modal-button clearButton">Очистить</button>
        </div>
    </form>
    <div class="modal-give__wrapper">
        <ul class="modal-give__list">
            <li class="modal-give__point muscleGroup" id="1">
                Грудная мышечная группа
                <div class="modal-give__menu">
                    <ul class="modal-give__hide__menu group1">
                    </ul>
                </div>
            </li>
            <li class="modal-give__point muscleGroup" id="2">
                Мышечная группа спины
                <div class="modal-give__menu">
                    <ul class="modal-give__hide__menu group2">
                    </ul>
                </div>
            </li>
            <li class="modal-give__point muscleGroup" id="3">
                Плечевая мышечная группа
                <div class="modal-give__menu">
                    <ul class="modal-give__hide__menu group3">
                    </ul>
                </div>
            </li>
            <li class="modal-give__point muscleGroup" id="4">
                Бицепс
                <div class="modal-give__menu">
                    <ul class="modal-give__hide__menu group4">
                    </ul>
                </div>
            </li>
            <li class="modal-give__point muscleGroup" id="5">
                Трицепс
                <div class="modal-give__menu">
                    <ul class="modal-give__hide__menu group5">
                    </ul>
                </div>
            </li>
            <li class="modal-give__point muscleGroup" id="6">
                Мышечная группа ног
                <div class="modal-give__menu">
                    <ul class="modal-give__hide__menu group6">
                    </ul>
                </div>
            </li>
            <li class="modal-give__point muscleGroup" id="7">
                Брюшная мышечная группа
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
                        Количество подходов<span class="small-image approaches-img"></span>
                    </td>
                    <td>
                        <select class="modal-give__select setAmount" disabled>
                            <option>
                                3
                            </option>
                            <option>
                                4
                            </option>
                            <option>
                                5
                            </option>
                            <option>
                                6
                            </option>
                            <option>
                                7
                            </option>
                            <option>
                                8
                            </option>
                            <option>
                                9
                            </option>
                            <option>
                                10
                            </option>
                            <option>
                                11
                            </option>
                            <option>
                                12
                            </option>
                            <option>
                                13
                            </option>
                            <option>
                                14
                            </option>
                            <option>
                                15
                            </option>
                            <option>
                                16
                            </option>
                            <option>
                                17
                            </option>
                            <option>
                                18
                            </option>
                            <option>
                                19
                            </option>
                            <option>
                                20
                            </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="modal-give__table-form--td">
                        Количество повторений<span class="small-image repeat-img"></span>
                    </td>
                    <td>
                        <select class="modal-give__select repeatAmount" disabled>
                            <option>
                                1
                            </option>
                            <option>
                                2
                            </option>
                            <option>
                                3
                            </option>
                            <option>
                                4
                            </option>
                            <option>
                                5
                            </option>
                            <option>
                                6
                            </option>
                            <option>
                                7
                            </option>
                            <option>
                                8
                            </option>
                            <option>
                                9
                            </option>
                            <option>
                                10
                            </option>
                            <option>
                                11
                            </option>
                            <option>
                                12
                            </option>
                            <option>
                                13
                            </option>
                            <option>
                                14
                            </option>
                            <option>
                                15
                            </option>
                            <option>
                                16
                            </option>
                            <option>
                                17
                            </option>
                            <option>
                                18
                            </option>
                            <option>
                                19
                            </option>
                            <option>
                                20
                            </option>
                            <option>
                                21
                            </option>
                            <option>
                                22
                            </option>
                            <option>
                                23
                            </option>
                            <option>
                                24
                            </option>
                            <option>
                                25
                            </option>
                            <option>
                                26
                            </option>
                            <option>
                                27
                            </option>
                            <option>
                                28
                            </option>
                            <option>
                                29
                            </option>
                            <option>
                                30
                            </option>
                            <option>
                                31
                            </option>
                            <option>
                                32
                            </option>
                            <option>
                                33
                            </option>
                            <option>
                                34
                            </option>
                            <option>
                                35
                            </option>
                            <option>
                                36
                            </option>
                            <option>
                                37
                            </option>
                            <option>
                                38
                            </option>
                            <option>
                                39
                            </option>
                            <option>
                                40
                            </option>
                            <option>
                                41
                            </option>
                            <option>
                                42
                            </option>
                            <option>
                                43
                            </option>
                            <option>
                                44
                            </option>
                            <option>
                                45
                            </option>
                            <option>
                                46
                            </option>
                            <option>
                                47
                            </option>
                            <option>
                                48
                            </option>
                            <option>
                                49
                            </option>
                            <option>
                                50
                            </option>
                            <option>
                                51
                            </option>
                            <option>
                                52
                            </option>
                            <option>
                                53
                            </option>
                            <option>
                                54
                            </option>
                            <option>
                                55
                            </option>
                            <option>
                                56
                            </option>
                            <option>
                                57
                            </option>
                            <option>
                                58
                            </option>
                            <option>
                                59
                            </option>
                            <option>
                                60
                            </option>
                            <option>
                                61
                            </option>
                            <option>
                                62
                            </option>
                            <option>
                                63
                            </option>
                            <option>
                                64
                            </option>
                            <option>
                                65
                            </option>
                            <option>
                                66
                            </option>
                            <option>
                                67
                            </option>
                            <option>
                                68
                            </option>
                            <option>
                                69
                            </option>
                            <option>
                                70
                            </option>
                            <option>
                                71
                            </option>
                            <option>
                                72
                            </option>
                            <option>
                                73
                            </option>
                            <option>
                                74
                            </option>
                            <option>
                                75
                            </option>
                            <option>
                                76
                            </option>
                            <option>
                                77
                            </option>
                            <option>
                                78
                            </option>
                            <option>
                                79
                            </option>
                            <option>
                                80
                            </option>
                            <option>
                                81
                            </option>
                            <option>
                                82
                            </option>
                            <option>
                                83
                            </option>
                            <option>
                                84
                            </option>
                            <option>
                                85
                            </option>
                            <option>
                                86
                            </option>
                            <option>
                                87
                            </option>
                            <option>
                                88
                            </option>
                            <option>
                                89
                            </option>
                            <option>
                                90
                            </option>
                            <option>
                                91
                            </option>
                            <option>
                                92
                            </option>
                            <option>
                                93
                            </option>
                            <option>
                                94
                            </option>
                            <option>
                                95
                            </option>
                            <option>
                                96
                            </option>
                            <option>
                                97
                            </option>
                            <option>
                                98
                            </option>
                            <option>
                                99
                            </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="modal-give__table-form--td">
                        Время отдыха<span class="small-image rest-img"></span>
                    </td>
                    <td>
                        <select class="modal-give__select restTime" disabled>
                            <option value="40">
                                0:40
                            </option>
                            <option value="45">
                                0:45
                            </option>
                            <option value="50">
                                0:50
                            </option>
                            <option value="60">
                                1:00
                            </option>
                            <option value="70">
                                1:10
                            </option>
                            <option value="80">
                                1:20
                            </option>
                            <option value="90">
                                1:30
                            </option>
                            <option value="120">
                                2:00
                            </option>
                            <option value="180">
                                3:00
                            </option>
                            <option value="300">
                                5:00
                            </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="modal-give__table-form--td">
                        Стартовый вес снаряда<span class="small-image weight-img"></span>
                    </td>
                    <td class="modal-give__table-form--input">
                        <input type="text" maxlength="5" class="modal-give__select workWeight" disabled/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="button" class="modal-button addExercise">Добавить</button>
                    </td>
                    <td>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div class="modal-content-extend">
    <button class="modal-content-extend-close" type="button" title="Закрыть">Закрыть</button>
    <h2 class="modal-content-title">Выдача абонемента</h2>
    <form class="extend-form" action="" method="post">
        <label class="extend-label">
            <input type="checkbox" name="extend-checkbox" class="extend-checkbox"/>
            <span></span>
            <div class="extend-div">
                Анлим
            </div>
        </label>
        <label class="extend-label__train">
            <input class="extend-train" type="text" maxlength="3" name="train" value="1"/>
            <div class="extend-div__train">
                Количество тренировок
            </div>
        </label>
        <label class="extend-label__date">
            <span class="extend-span">From</span>
            <input class="extend-date dateFrom" type="date" name="date" readonly>
        </label>
        <label class="extend-label__date">
            <span class="extend-span">До</span>
            <input class="extend-date dateTill" type="date" name="extend-date"/>
        </label>
        <button class="modal-button extend-button" type="button">Продлить</button>
    </form>
</div>
<div class="modal-overlay"></div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/lib/cookie/jquery.cookie.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/TrainerPage/variables.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/TrainerPage/TrainerPage.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/TrainerPage/ClientButton.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/TrainerPage/SeasonExtensionButton.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/TrainerPage/ExerciseOut.js"></script>
</body>
</html>
