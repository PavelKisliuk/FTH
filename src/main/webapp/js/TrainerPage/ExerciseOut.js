$(function () {
    $(".client-base__list").on("click", ".give-train", function () {//при нажатии на выдать тренировку
        $(".exercises").remove(); //удаляет упражнения из таблицы
        toDefaultFields();
        toDefaultExercises();
        setExerciseTable();
    });

    function toDefaultFields() {
        $(".setAmount").prop("value", "4");
        $(".repeatAmount").prop("value", "10");
        $(".restTime").prop("value", "60");
        $(".workWeight").prop("value", "");
    }

    function toDefaultExercises() {
        $(".modal-give__select").prop("disabled", true); //отключает элементы ввода данных
        $(".modal-give__point").removeClass("selected");//убираем выделение с группы мышц
        const drills = document.getElementsByClassName("modal-give__menu");//получаем все упражнения
        $.each(drills, function (index, value) {//делаем их невидимыми
            value.classList.remove("active");
        });
        toDefaultDrills();
    }

    function toDefaultDrills() {
        $(".modal-give__hide__point").removeClass("selected");//убиарем выделение с упражнения
    }

    function setExerciseTable() { ////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        const id = $.cookie("chosenOne");
        let exercises = $.cookie("client" + id);//если для пользователя с таким id есть таблица упражнений
        if (exercises === undefined) {
            $(".outButton").prop("disabled", true);
            return;
        }
        $(".outButton").prop("disabled", false);
        $.each(JSON.parse(exercises), function (index, value) {//рисуем таблицу
            const drill = $("#" + value.drillId + ".drill")[0].innerText;//НЕ СТАВИТЬ ПРОБЕЛ МЕЖДУ КЛАССОМ И ID!!!!!!
            let str = "<tr class=\"exercises\">\n" +
                "<td>\n" +
                drill +
                "</td>\n" +
                "<td>\n" +
                value.setAmount +
                "</td>\n" +
                "<td>\n" +
                value.repeatAmount +
                "</td>\n" +
                "<td>\n" +
                timeToFormat(value.restTime) +
                "</td>\n" +
                "<td>\n" +
                value.weightTool +
                "</td>\n" +
                "</tr>\n";
            $(".modal-give__table").append(str);
        });
    }

    $(".muscleGroup").click(function () { //при нажатии на группу мышц
        const element = this.getElementsByTagName("div").item(0);
        if (element.classList.contains("active")) {
            return;
        }
        toDefaultFields();
        toDefaultExercises();
        element.classList.add("active");
        $(this).addClass("selected");
        $.cookie("muscleGroup", this.id);
    });

    $(".workWeight").keypress(function (eventObject) {
        const value = $(this).val();
        const val = eventObject.which;
        if (!isNum(val) && !isPeriod(val, value)) {
            eventObject.preventDefault();
        }
    });

    function isNum(val) {
        return val >= 48 && val <= 59;
    }

    function isPeriod(val, value) {
        const re1 = new RegExp("[.,]");
        return (val === 44 || val === 46) ? !re1.test(value) : false;
    }

    $(".modal-give__hide__menu").on("click", ".drill", function () {
        if (this.classList.contains("selected")) { //если уже выделена
            return;
        }
        toDefaultFields();
        toDefaultDrills();
        $(this).addClass("selected");
        $(".modal-give__select").prop("disabled", false);//включает элементы ввода данных
        const drill = this.id;
        $.cookie("chosenDrillId", drill);
    });

    $(".addExercise").click(function () {
        let weightTool = $(".workWeight").val();
        if (weightTool === "") {
            return;
        }
        $(".outButton").prop("disabled", false);
        weightTool = recoverWeight(weightTool);
        const setAmount = $(".setAmount").val();
        const repeatAmount = $(".repeatAmount").val();
        const chosenTimeOption = $(".restTime");
        const restTime = chosenTimeOption.val();
        const chosenDrillId = $.cookie("chosenDrillId");
        const drill = $("#" + chosenDrillId + ".drill")[0].innerText;//НЕ СТАВИТЬ ПРОБЕЛ МЕЖДУ КЛАССОМ И ID!!!!!!

        let str = "<tr class=\"exercises\">\n" +
            "<td>\n" +
            drill +
            "</td>\n" +
            "<td>\n" +
            setAmount +
            "</td>\n" +
            "<td>\n" +
            repeatAmount +
            "</td>\n" +
            "<td>\n" +
            timeToFormat(restTime) +
            "</td>\n" +
            "<td>\n" +
            weightTool +
            "</td>\n" +
            "</tr>\n";
        $(".modal-give__table").append(str);

        //---------------добавляем упражнение в массив и перезаписываем в кукуки
        const muscleGroupId = $.cookie("muscleGroup");
        const id = $.cookie("chosenOne");
        const exercise = {};
        exercise.drillId = chosenDrillId;
        exercise.muscleGroupId = muscleGroupId;
        exercise.setAmount = setAmount;
        exercise.repeatAmount = repeatAmount;
        exercise.restTime = restTime;
        exercise.weightTool = weightTool;

        let exercises = $.cookie("client" + id);
        let ar = [];
        if (exercises !== undefined) {
            ar = JSON.parse(exercises);
        }
        ar.push(exercise);
        $.cookie("client" + id, JSON.stringify(ar));
        toDefaultFields();
    });

    function recoverWeight(weight) {
        if (weight.length === 0) {
            return "0";
        }
        weight = weight.replace(",", ".");
        if (weight === ".") {
            weight = "0";
        } else if (weight.charAt(0) === ".") {
            weight = "0" + weight;
        } else if (weight.charAt(weight.length - 1) === ".") {
            weight = weight + "0";
        }
        return weight;
    }

    function timeToFormat(time) {
        let formatTime;
        switch (time) {
            case "40":
                formatTime = "0:40";
                break;
            case "45":
                formatTime = "0:45";
                break;
            case "50":
                formatTime = "0:50";
                break;
            case "60":
                formatTime = "1:00";
                break;
            case "70":
                formatTime = "1:10";
                break;
            case "80":
                formatTime = "1:20";
                break;
            case "90":
                formatTime = "1:30";
                break;
            case "120":
                formatTime = "2:00";
                break;
            case "180":
                formatTime = "3:00";
                break;
            case "300":
                formatTime = "5:00";
                break;
        }
        return formatTime;
    }

    $(".clearButton").click(function () {
        $(".exercises").remove(); //удаляет упражнения из таблицы
        toDefaultFields();
        toDefaultExercises();
        const id = $.cookie("chosenOne");
        $.removeCookie("client" + id);
    });

    $(".outButton").click(function () {
        const clientId = $.cookie("chosenOne");
        const exercise = $.cookie("client" + clientId);
        $(".give-train").attr("disabled", true); //отключаем кнопку выдачи тренировки
        postExercise(clientId, exercise);
        $.removeCookie("client" + clientId);
        popupGive.classList.remove("modal-content-show--flex");
        overlay.classList.remove("modal-overlay-show");
    });

    function postExercise(clientId, exercise) {
        $.post(host,
            {
                "command": "TRAINER_EXERCISE",
                "clientId": clientId,
                "exercise": exercise
            }, request).fail(function () {
            window.location.href = serverErrorPage;
        });

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
                    const chosen = $("#" + clientId + ".requested");
                    chosen.removeClass("requested"); //убираем идентификатор запроса (колокольчик)
                    $(".client-base__actions").removeClass("active");
                }
            }
        }
    }

    function clearCookie(clientId) {
        $.removeCookie("chosenOne");
        $.removeCookie("client" + clientId);
    }
});