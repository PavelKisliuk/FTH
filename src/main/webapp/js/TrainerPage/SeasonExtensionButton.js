$(function () {
    $(".client-base__list").on("click", ".extend-sub", function () {//при нажатии на продлить абонемент
        setDate(); //устанавливаем сегодняшнюю дату и завтрашнюю дату(продлять можно только на день вперёд)
        $(".extend-checkbox").prop("checked", false); //убираем галочку
        $(".extend-train").prop("disabled", false).prop("value", "1");//делаем возможным вводить количество тренировок
    });

    function setDate() {
        let today = new Date();
        let tomorrow = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
        let dd = today.getDate();
        let mm = today.getMonth() + 1; //January is 0!
        let yyyy = today.getFullYear();
        let tomDay = tomorrow.getDate();
        let tomMonth = tomorrow.getMonth() + 1;
        let tomYear = tomorrow.getFullYear();
        if (dd < 10) {
            dd = '0' + dd
        }
        if (mm < 10) {
            mm = '0' + mm
        }
        today = yyyy + '-' + mm + '-' + dd;
        if (tomDay < 10) {
            tomDay = '0' + tomDay
        }
        if (tomMonth < 10) {
            tomMonth = '0' + tomMonth
        }
        tomorrow = tomYear + '-' + tomMonth + '-' + tomDay;
        $(".dateFrom").val(today);
        $(".dateTill").val(tomorrow).attr("min", tomorrow);
    }

    $(".extend-checkbox").change(function () { //при нажатии на checkbox
        if ($(this).is(":checked")) { //если выбран
            $(".extend-train").prop("disabled", true).prop("value", "");//нельзя вводить количество тренировок
        } else {
            $(".extend-train").prop("disabled", false).prop("value", "1");//можнно вводить количество тренировок (изначально 1)
        }
    });

    $(".extend-train").keypress(function (eventObject) {//при вводе количества занятий
        if (eventObject.which < 48 || eventObject.which > 59) {//если не число
            eventObject.preventDefault();//введённый символ не появится
        }
    }).click(function () {//при нажатии на поле количества занятий
        $(".extend-train").select(); //принажатии будут выделены все данные
    });

    $(".extend-button").click(function (eventObject) {//при нажатии на кнопку выдачи
        if (!$(".extend-checkbox").is(":checked") &&
            $(".extend-train").val() === "") {
            eventObject.preventDefault();
            alert("Enter amount of exercises!");
        }
    });
});