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
        $(".restTime").prop("value", "1:00");
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

    function setExerciseTable() {
        const id = $.cookie("chosenOne");
        let exercises = $.cookie("client" + id);//если для пользователя с таким id есть таблица упражнений
        if (exercises === undefined) {
            return;
        }
        $.each(JSON.parse(exercises), function (index, value) {//рисуем таблицу
            let str = "<tr class=\"exercises\">\n" +
                "<td>\n" +
                value.drill +
                "</td>\n" +
                "<td>\n" +
                value.set +
                "</td>\n" +
                "<td>\n" +
                value.repeat +
                "</td>\n" +
                "<td>\n" +
                value.time +
                "</td>\n" +
                "<td>\n" +
                value.weight +
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
        const re1 = new RegExp("[.,]")
        return (val === 44 || val === 46) ? !re1.test(value) : false;
    }

    $(".drill").click(function () {
        if (this.classList.contains("selected")) { //если уже выделена
            return;
        }
        toDefaultFields();
        toDefaultDrills();
        $(this).addClass("selected");
        $(".modal-give__select").prop("disabled", false);//включает элементы ввода данных
        const drill = this.innerText;
        $.cookie("chosenDrill", drill);
    });

    $(".addExercise").click(function () {
        let weight = $(".workWeight").val();
        if (weight !== "") {
            weight = recoverWeight(weight);
            const set = $(".setAmount").val();
            const repeat = $(".repeatAmount").val();
            const time = $(".restTime").val();
            const drill = $.cookie("chosenDrill");

            let str = "<tr class=\"exercises\">\n" +
                "<td>\n" +
                drill +
                "</td>\n" +
                "<td>\n" +
                set +
                "</td>\n" +
                "<td>\n" +
                repeat +
                "</td>\n" +
                "<td>\n" +
                time +
                "</td>\n" +
                "<td>\n" +
                weight +
                "</td>\n" +
                "</tr>\n";
            $(".modal-give__table").append(str);

            const id = $.cookie("chosenOne");
            const exercise = {};
            exercise.drill = drill;
            exercise.set = set;
            exercise.repeat = repeat;
            exercise.time = time;
            exercise.weight = weight;

            let exercises = $.cookie("client" + id);
            let ar = [];
            if (exercises !== undefined) {
                ar = JSON.parse(exercises);
            }
            ar.push(exercise);
            $.cookie("client" + id, JSON.stringify(ar));
            toDefaultFields();
        }
    });

    function recoverWeight(weight) {
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

    $(".clearButton").click(function () {
        $(".exercises").remove(); //удаляет упражнения из таблицы
        toDefaultFields();
        toDefaultExercises();
        const id = $.cookie("chosenOne");
        $.removeCookie("client" + id);
    });
});