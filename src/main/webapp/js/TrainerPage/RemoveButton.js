$(function () {
    $(".deny-button").click(function () {
        const clientId = $.cookie("chosenOne");
        if ($("#" + clientId + ".requested").length === 0) {
            return;
        }
        $.post(host,
            {
                "command": "DENY_CLIENT",
                "clientId": clientId
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
                    let condition_name = $.cookie("sort-name");
                    let condition_quality = $.cookie("sort");
                    obtainClientGroup(condition_name, condition_quality);
                    popupDelete.classList.remove("modal-content-show");
                    overlay.classList.remove("modal-overlay-show");
                }
            }
        }
    });

    function obtainClientGroup(condition_name, condition_quality) {
        $.get(host,
            {
                "command": "CONDITION_REFRESH",
                "condition_name": condition_name,
                "condition_quality": condition_quality
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
                    insertClientGroup(response.client);
                }
            }
        }
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

    $(".fire-button").click(function () {
        const clientId = $.cookie("chosenOne");
        $.post(host,
            {
                "command": "FIRE_SEASON",
                "clientId": clientId
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
                    let condition_name = $.cookie("sort-name");
                    let condition_quality = $.cookie("sort");
                    obtainClientGroup(condition_name, condition_quality);
                    popupDelete.classList.remove("modal-content-show");
                    overlay.classList.remove("modal-overlay-show");
                }
            }
        }
    });

    $(".delete-button").click(function () {
        const clientId = $.cookie("chosenOne");
        $.post(host,
            {
                "command": "REMOVE_CLIENT",
                "long": clientId
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
                    let condition_name = $.cookie("sort-name");
                    let condition_quality = $.cookie("sort");
                    obtainClientGroup(condition_name, condition_quality);
                    popupDelete.classList.remove("modal-content-show");
                    overlay.classList.remove("modal-overlay-show");
                }
            }
        }
    });
});