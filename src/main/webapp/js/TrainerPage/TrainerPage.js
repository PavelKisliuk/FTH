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
                        $(".name").prop("checked", true);
                        $.cookie("sort-name", "name");
                        $(".each_And_Every").prop("checked", true);
                        $.cookie("sort", "each_And_Every");
                        insertTrainerData(response.trainer);
                        insertClientGroup(response.client);
                        getDrills();
                    }
                }
            }
        }

        function getDrills() {
            $.get(host,
                {
                    "command": "DRILL_BASE"
                }, request).fail(function (xhr, status, error) {
                alert("Error");
            });

            function request(response) {
                if (response.errorRedirect) {
                    window.location.href = response.errorRedirect;
                } else {
                    const data = response.data;
                    createDrills(data);
                }
            }

            function createDrills(data) {
                let eTable = "";
                let muscleGroupId = data[0].muscleGroupId;
                $.each(data, function (index, value) {
                    if (muscleGroupId !== value.muscleGroupId) {
                        $(".group" + muscleGroupId).html(eTable);
                        eTable = "";
                        muscleGroupId = value.muscleGroupId;
                    }
                    eTable += "<li class=\"modal-give__hide__point drill\" id=\"" + value.drillBaseId + "\">\n" +
                        value.drillName +
                        "</li>\n";
                });
                $(".group" + muscleGroupId).html(eTable);
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
            const id = value.clientId;
            const request = (value.exerciseRequest) ? "requested" : "";
            eTable += "<li class=\"client-base__actual " + request + "\" id=\"" + id + "\">\n" +
                "<div class=\"client-base__block\">";
            let nameElement1;
            let nameElement2;
            if ($.cookie("sort-name") === "name") {
                nameElement1 = value.firstName;
                nameElement2 = value.lastName;
            } else {
                nameElement1 = value.lastName;
                nameElement2 = value.firstName;
            }
            const photo = value.photoPath;

            eTable += "<span class=\"client-base__notification\"></span>\n" +
                "<img class=\"client-base__photo\" width=\"30px\" height=\"30px\" alt=\"Фото клиента\" " +
                "src=\"" + photo + "\">\n" +
                "<p class=\"client-base__name\"  id=\"" + id + "\">" + nameElement1 + " " + nameElement2 + "</p>\n";
            eTable += "</div>\n" +
                "<ul class=\"client-base__actions\">\n" +
                "<li class=\"client-base__point\">\n" +
                "<button class=\"extend-sub button\">Продлить абонемент</button>\n" +
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

    $(".conditionRefresh").click(function (eventObject) {
        let condition_name;
        let condition_quality;
        if (this.name === "sort-name") {
            condition_name = this.id;
            if ($.cookie("sort-name") === condition_name) {
                return;
            }
            condition_quality = $.cookie("sort");
            $.cookie("sort-name", condition_name);
            obtainClientGroup(condition_name, condition_quality)
        } else {
            condition_quality = this.id;
            if ($.cookie("sort") === condition_quality) {
                return;
            }
            condition_name = $.cookie("sort-name");
            $.cookie("sort", condition_quality);
            obtainClientGroup(condition_name, condition_quality)
        }
    });

    function obtainClientGroup(condition_name, condition_quality) {
        $.get(host,
            {
                "command": "CONDITION_REFRESH",
                "condition_name": condition_name,
                "condition_quality": condition_quality
            }, request).fail(function (xhr, status, error) {
            alert("Error");
        });
    }

    function request(response) {
        if (response.errorRedirect) {
            window.location.href = response.errorRedirect;
        } else {
            insertClientGroup(response.client);
        }
    }


});