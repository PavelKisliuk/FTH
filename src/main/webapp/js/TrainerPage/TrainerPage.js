$(function () {
    const host = "/FTH/start";
    $(".couch-info").ready(function () {
        $.get(host,
            {
                "command": "IN_SYSTEM",
                "str": "TRAINER_AUTH",
                "condition": "false"
            }, request).fail(function (xhr, status, error) {
            alert("Error");
        });

        function request(response) {
            if (response.page) {
                window.location.href = response.page;
            } else {
                $.get(host,
                    {
                        "command": "TRAINER_PAGE"
                    }, request).fail(function (xhr, status, error) {
                    alert("Error");
                });

                //
                function request(response) {
                    if (response.errorRedirect) {
                        window.location.href = response.errorRedirect;
                    } else {
                        insertTrainerData(response.trainer);
                        insertClientGroup(response.client);
                    }
                }
            }
        }

    });

    function insertTrainerData(data) {
        const name = data[0].name;
        const surname = data[0].surname;
        const photo = data[0].photoPath;
        let eTable = "<img class=\"couch-info__photo\" width=\"60px\" height=\"60px\" alt=\"Фото тренера\" " +
            "src=\"" + photo + "\">\n" +
            "<p class=\"couch-info__nameblock\">" +
            "<span class=\"couch-info__name\">" + name + "</span>" +
            "<span class=\"couch-info__surname\">" + surname + " </span>\n" + "</p>\n";
        $(".couch-info").html(eTable);
    }

    function insertClientGroup(data) {
        let eTable = "";

        $.each(data, function (index, value) {
            eTable += "<li class=\"client-base__actual\">\n" +
                "<div class=\"client-base__block\">";

            const id = value.clientId;
            const name = value.firstName;
            const surname = value.lastName;
            const photo = value.photoPath;

            eTable += "<span class=\"client-base__notification\"></span>\n" +
                "<img class=\"client-base__photo\" width=\"30px\" height=\"30px\" alt=\"Фото клиента\" " +
                "src=\"" + photo + "\">\n" +
                "<p class=\"client-base__name\"  id=\"" + id + "\">" + name + " " + surname + "</p>\n";
            eTable += "</div>\n" +
                "<ul class=\"client-base__actions\">\n" +
                "<li class=\"client-base__point\">\n" +
                "<button class=\"extend-sub button\">" + "Продлить абонемент" + "</button>\n" +
                "</li>\n" +
                "<li class=\"client-base__point\">\n" +
                "<button class=\"give-train button\">Выдать тренировку</button>\n" +
                "</li>\n" +
                "<li class=\"client-base__point\">\n" +
                "<button class=\"delete-user button\">Удалить</button>\n" +
                "</li>\n" +
                "</ul>\n" +
                "</li>\n";
        });
        $(".client-base__list").html(eTable);
    }
});

$(function () {
    $(".client-base__list").on("click", ".client-base__actual", function () {
        const element = this.getElementsByTagName("ul").item(0);
        if (!element.classList.contains("active--flex")) {
            const x = document.getElementsByClassName("client-base__actions");
            $.each(x, function (index, value) {
                value.classList.remove("active--flex");
            });
            element.classList.add("active--flex");
            $(".client-base__actual").removeClass("selected--dark");
            $(this).addClass("selected--dark");

            //to cookie
            const id = $(this.getElementsByClassName("client-base__name")).attr("id");
            $.cookie("chosenOne", id);
        }
    });
});

$(function () {
    $(".client-base__list").on("click", ".extend-sub", function () {
        popupExtend.classList.add("modal-content-show--flex");
        overlay.classList.add("modal-overlay-show");
    });

    $(".client-base__list").on("click", ".give-train", function () {
        popupGive.classList.add("modal-content-show--flex");
        overlay.classList.add("modal-overlay-show");
    });

    $(".client-base__list").on("click", ".delete-user", function () {
        popupDelete.classList.add("modal-content-show");
        overlay.classList.add("modal-overlay-show");
    });
});

$(function () {
    $(".client-base__list").on("click", ".extend-sub", function () {
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
        $("#dateFrom").val(today);
        $("#dateTill").val(tomorrow);
        $('#dateTill').attr("min", tomorrow);
        $(".extend-checkbox").prop("checked", false);
        $(".extend-train").prop("disabled", false);
        $(".extend-train").prop("value", "1");
    });

    $(".extend-checkbox").change(function () {
        if($(this).is(":checked")) {
            $(".extend-train").prop("disabled", true);
            $(".extend-train").prop("value", "");
        } else {
            $(".extend-train").prop("disabled", false);
            $(".extend-train").prop("value", "1");
        }
    });

    $(".extend-train").keypress(function(eventObject){
        if(eventObject.which < 48 || eventObject.which > 59) {
            eventObject.preventDefault();
        }
    });

    $(".extend-train").click(function(){
        $(".extend-train").select();
    });

    $(".extend-button").click(function(eventObject){
        if(!$(".extend-checkbox").is(":checked") &&
            $(".extend-train").val() === "") {
            eventObject.preventDefault();
            alert("Enter amount of exercises!");
        }
    });
});

$(function () {
    $(".client-base__list").on("click", ".give-train", function () {
        const id = $.cookie("chosenOne");
        if($.cookie("client" + id) === undefined) {
            $(".modal-give__point").removeClass("selected");
            $(".modal-give__hide__point").removeClass("selected");
            $(".modal-give__select").prop("disabled", true);
            const x = document.getElementsByClassName("modal-give__menu");
            $.each(x, function (index, value) {
                value.classList.remove("active");
            });
            $(".exercises").remove();
            toDefault();
        }
    });

    function toDefault() {
        $(".setAmount").prop("value", "4");
        $(".repeatAmount").prop("value", "10");
        $(".restTime").prop("value", "1:00");
        $(".workWeight").prop("value", "");
    }

    $(".modal-give__point").click(function () {
        const element = this.getElementsByTagName("div").item(0);
        if (!element.classList.contains("active")) {
            const x = document.getElementsByClassName("modal-give__menu");
            $.each(x, function (index, value) {
                value.classList.remove("active");
            });
            element.classList.add("active");
            $(".modal-give__select").prop("disabled", true);
            $(".modal-give__hide__point").removeClass("selected");
            toDefault();
        }
    });

    $(".workWeight").keypress(function(eventObject){
        const value = $(this).val();
        const val = eventObject.which;
        if(!isNum(val) && !isPeriod(val, value)) {
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

    $(".modal-give__hide__point").click(function () {
        $(".modal-give__select").prop("disabled", false);
    });

    $(".modal-give__point").on("click", function () {
        $(".modal-give__point").removeClass("selected");
        $(this).addClass("selected");
        toDefault();
    });

    $(".modal-give__hide__point").on("click", function () {
        $(".modal-give__hide__point").removeClass("selected");
        $(this).addClass("selected");
        toDefault();
    });

    $(".addExercise").click(function () {

    });
});