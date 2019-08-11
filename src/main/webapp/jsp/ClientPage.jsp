<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 10.08.2019
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Стартовая страница</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="../css/TrainerPage/normalize.css"/>
    <link rel="stylesheet" href="../css/ClientPage/style.css"/>
</head>
<body>
<header class="page-header">

</header>
<main class="page-main">
    <div class="page-main__wrapper">
        <div class="user__wrapper">
            <section class="user-info">
                <img class="user-info__photo" width="60px" height="60px" alt="Фото тренера">
                <p class="user-info__nameblock">
              <span class="user-info__name">
                Павел
              </span>
                    <span class="user-info__surname">
               Кислюк
              </span>
                </p>
            </section>
            <section class="user-menu">
                <ul class="user-menu__list">
                    <li class="user-menu__action action-1">
                        действие один
                    </li>
                    <li class="user-menu__action action-2">
                        действие два
                    </li>
                    <li class="user-menu__action action-3">
                        действие три
                    </li>
                    <li class="user-menu__action action-4">
                        действие четыре
                    </li>
                    <li class="user-menu__action action-5">
                        действие пять
                    </li>
                </ul>
            </section>
        </div>
        <section class="user-text-area">
            <div class="user-text-area__wrapper">
                <h2 class="user-text-area__title">Информация о сайте</h2>
                <p>
                    Информация, поступающая с сервера при нажатии на клиента.
                </p>
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
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript" src="../js/lib/cookie/jquery.cookie.js"></script>
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

