$(function () {
    $(".client-base__list").on("click", ".client-base__actual", function () {//при нажатии на клиента
        const element = this.getElementsByTagName("ul").item(0); //получаем элемент на который нажали
        if (element.classList.contains("active--flex")) { //если элемент уже нажат ничего не делаем
            return;
        }
        openClient(element); //открываем меню действий у клиента и делаем этого клиента выделенным
        const id = $(this.getElementsByClassName("client-base__name")).attr("id");
        //to cookie
        $.cookie("chosenOne", id);
        actionsAdjustment(id, this); //настраиваем кнопки действий
    });

    function openClient(element) {
        $(".extend-sub").attr("disabled", true); //отключаем кнопку продления абонемента
        $(".give-train").attr("disabled", true); //отключаем кнопку выдачи тренировки
        unmarkUsers();
        element.classList.add("active--flex"); //делаем пользователя активным (появляется меню действий)
        $(this).addClass("selected--dark"); //делаем тёмненькую подсветку
    }

    function unmarkUsers() {
        const userGroup = document.getElementsByClassName("client-base__actions"); //получаем всех пользователей
        $.each(userGroup, function (index, value) { //убираем у них все активность (делаем невыделенными)
            value.classList.remove("active--flex");
        });
        $(".client-base__actual").removeClass("selected--dark"); //убираем тёмненькую подсветку
    }

    function actionsAdjustment(id, chosenClient) {
        $.get(host,
            {
                "command": "REQUEST_AND_EXPIRED",
                "long": id
            }, request).fail(function () {
            window.location.href = serverErrorPage;
        });//посылаем запрос серверу, чтобы узнать нужно ли клиенту выдавать абонемент и запросил ли он тренировку

        function request(response) {
            if (response.errorRedirect) {
                window.location.href = response.errorRedirect;
            } else {
                if (response.errorMessage) {
                    alert(response.errorMessage);
                } else {
                    if (response.message) {
                        alert(response.message);
                    }
                    const data = response.data;
                    if (data.restVisitation === 0 || data.expiredDay < new Date().getTime()) { //если абонемент недействителен
                        $(".extend-sub").attr("disabled", false);//включаем кнопку выдачи абонемент
                    }
                    if (data.exerciseRequest) { //если есть запрос на тренировку
                        $(chosenClient).addClass("requested");
                        $(".give-train").attr("disabled", false); //включаем кнопку выдачи тренировки
                    } else {
                        $(chosenClient).removeClass("requested");
                    }
                }
            }
        }
    }
});