$(function () {
    const host = "/FTH/start";
    $(".action-1").click(function () {
        $.get(host,
            {
                "command": "TO_CLIENT_EXERCISE"
            }, request).fail(function (xhr, status, error) {
            alert("Error");
        });

        function request(response) {
            const names = JSON.parse(response.answer1).drillName;
            const sets = JSON.parse(response.answer2).setInfo;
            const id = JSON.parse(response.answer3).ID;

            let setsToClient = [];
            let it1 = 0;
            while (it1 < id.length) {
                const dynAr = [];
                $.each(id[it1], function (index, value) {
                    const dynSet = {};
                    const setInfo = sets[it1];
                    dynSet.id = value;
                    dynSet.selfConsistent = 0;
                    dynSet.helpConsistent = 0;
                    dynSet.weightTool = setInfo.weightTool;
                    dynAr.push(dynSet);
                });
                it1++;
                setsToClient.push(dynAr);
            }


            const constAr = [];
            $.each(sets, function (index, value) {
                const constSet = {};
                constSet.necessaryReps = value.necessaryReps;
                constSet.restTime = value.restTime;
                constAr.push(constSet);
            });

            it1 = 1;
            $.each(setsToClient, function (index, value) {
                $.cookie("drillDynamic" + it1, JSON.stringify(value));
                it1++;
            });

            it1 = 1;
            $.each(constAr, function (index, value) {
                $.cookie("drillStatic" + it1, JSON.stringify(value));
                it1++;
            });


            let namesToPage = "<p>Текущая тренировка:</p>\n" + "<ul class=\"training-list\">\n";
            let i = 1;
            $.each(names, function (index, drillName) {
                let str = "<li class=\"training-list__stroke drillName\" id=\"" + i + "\">\n" +
                    drillName +
                    "</li>\n";
                namesToPage += str;
                i++;
            });
            namesToPage += "</ul>\n" + "</div>";
            $(".drillList").html(namesToPage);
        }
    });

    $(".drillList").on("click", ".drillName", function () {
        $(".drillName").removeClass("selected");
        $(this).addClass("selected");
        drawSets(this.id);
    });

    function drawSets(id) {
        const setDyn = JSON.parse($.cookie("drillDynamic" + id));
        const setConst = JSON.parse($.cookie("drillStatic" + id));

        let drillsToPage = "<p>Подходы:</p>\n" +
            "<section class=\"train-table\">\n";

        let i = 1;
        $.each(setDyn, function (index, value) {
            drillsToPage += "<ul class=\"train-table__list\">\n" +
                "<li class=\"train-table__first-stroke\">\n" +
                "<div>Подход #" + i + "</div>" +
                "</li>" +
                "<li class=\"train-table__second-stroke\">" +
                "<div>Сам</div><div>Пом</div>" +
                "</li>" +
                "<li class=\"train-table__third-stroke\">" +
                "<div class=\"table__input\" title=\"Выполнено самостоятельно\">" +
                "<input class=\"consistent\" type=\"text\" name=\"\" maxlength=\"3\" value=\"" + value.selfConsistent + "\">" +
                "</div>" +
                "<div class=\"table__input\" title=\"Выполнено с помощью\">" +
                "<input class=\"consistent\" type=\"text\" name=\"\" maxlength=\"3\" value=\"" + value.helpConsistent + "\">" +
                "</div>" +
                "</li>" +
                "<li class=\"train-table__fourth-stroke\">" +
                "<div title=\"Кол-во повторений\">Повт. " + setConst.necessaryReps + "</div>" +
                "<div class=\"table__select\" title=\"Вес снаряда\">" +
                "<input class=\"table__inputWeight workWeight\"  maxlength=\"5\" type=\"text\" name=\"\" value=\"" + value.weightTool + "\">" +
                "</div>" +
                "<div title=\"Время отдыха\">" + timeToFormat(setConst.restTime) + "</div>" +
                "</li>" +
                "</ul>";
            i++;
        });
        drillsToPage += "</section>\n";
        $(".setGroup").html(drillsToPage);
    }

    function timeToFormat(time) {
        let formatTime;
        switch (time) {
            case 40:
                formatTime = "0:40";
                break;
            case 45:
                formatTime = "0:45";
                break;
            case 50:
                formatTime = "0:50";
                break;
            case 60:
                formatTime = "1:00";
                break;
            case 70:
                formatTime = "1:10";
                break;
            case 80:
                formatTime = "1:20";
                break;
            case 90:
                formatTime = "1:30";
                break;
            case 120:
                formatTime = "2:00";
                break;
            case 180:
                formatTime = "3:00";
                break;
            case 300:
                formatTime = "5:00";
                break;
        }
        return formatTime;
    }

    $(".setGroup").on("click", ".workWeight", function (eventObject) {
        this.select(); //принажатии будут выделены все данные
    });

    $(".setGroup").on("click", ".consistent", function (eventObject) {
        this.select(); //принажатии будут выделены все данные
    });

    $(".setGroup").on("keypress", ".workWeight", function (eventObject) {
        const value = $(this).val();
        const val = eventObject.which;
        if (!isNum(val) && !isPeriod(val, value)) {
            eventObject.preventDefault();
        }
    });

    $(".setGroup").on("keypress", ".consistent", function (eventObject) {
        const val = eventObject.which;
        if (!isNum(val)) {
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
});