const host = "/FTH/start";
$(function () {
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
            if (response.redirect) {
                window.location.href = response.redirect;
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
            const request = (value.exerciseRequest) ? "requested" : "";
            eTable += "<li class=\"client-base__actual " + request + "\">\n" +
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