const host = "/start";
const serverErrorPage = "error500.jsp";


var language = document.querySelector(".language");
var russian = document.querySelector(".russian");
var english = document.querySelector(".english");
var languageChoice = document.querySelector(".language__choice");
var actionsLanguage = document.querySelector(".actions__language");

var popupChoice = document.querySelector(".modal-choice");
var closeChoice = document.querySelector(".modal-choice-close");

var overlay = document.querySelector(".modal-overlay");

russian.addEventListener("click", function (event) {
    if (actionsLanguage.classList.contains("actions__language--usa")) {
        actionsLanguage.classList.remove("actions__language--usa");
    }
});

english.addEventListener("click", function (event) {
    actionsLanguage.classList.add("actions__language--usa");
});

actionsLanguage.addEventListener("click", function (event) {
    if (language.classList.contains("active")) {
        language.classList.remove("active");
    } else {
        language.classList.add("active");
    }
});


$(function () {
    $(".user-menu").ready(function () {
        $.get(host,
            {
                "command": "IN_SYSTEM",
                "str": "ClientPage.jsp"
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
                    if (response.redirect) {
                        window.location.href = response.redirect;
                    } else {
                        start();
                    }
                }
            }
        }
    });

    function start() {
        $.get(host,
            {
                "command": "CLIENT_PAGE"
            }, request).fail(function () {
            window.location.href = serverErrorPage;
        });

        function request(response) {
            if (response.errorRedirect) {
                window.location.href = response.errorRedirect;
            } else {
                if (response.message) {
                    alert(response.message);
                }
                if (response.redirect) {
                    window.location.href = response.redirect;
                } else {
                    if (response.without) {
                        $(".tClose").addClass("without");
                        $(".changeTrainer").click();
                    } else {
                        insertClientData(response.client);
                        setButtons(response.buttonCondition);
                    }
                }
            }
        }

        function insertClientData(data) {
            const name = data[0].name;
            const surname = data[0].surname;
            const photo = data[0].photoPath;
            let eTable = "<img class=\"user-info__photo\" width=\"60px\" height=\"60px\" alt=\"Фото клиента\" " +
                "src=\"" + photo + "\">" +
                "<p class=\"user-info__nameblock\">" +
                "<span class=\"user-info__name\">" +
                name +
                "</span>" +
                "<span class=\"user-info__surname\">" +
                surname +
                "</span>" +
                "</p>";
            $(".user-info").html(eTable);
        }

        function setButtons(conditions) {
            $(".startExercise").toggle(conditions[0].bool);
            $(".finishExercise").toggle(conditions[1].bool);
            $(".requestExercise").toggle(conditions[2].bool);
            $(".discardExercise").toggle(conditions[3].bool);
        }
    }

    $(".discardExercise").click(function () {
        $.post(host,
            {
                "command": "DISCARD_EXERCISE"
            }, request1).fail(function () {
            window.location.href = serverErrorPage;
        });

        function request1(response) {
            if (response.errorRedirect) {
                window.location.href = response.errorRedirect;
            } else {
                if (response.errorMessage) {
                    alert(response.errorMessage);
                } else {
                    if (response.message) {
                        alert(response.message);
                    }
                    $(".setGroup").remove();
                    $(".drillList").remove();
                    for (let i = 1; i <= $.cookie("drillDynamicLength"); i++) {
                        $.removeCookie("drillDynamic" + i);
                    }
                    for (let i = 1; i <= $.cookie("drillStaticLength"); i++) {
                        $.removeCookie("drillStatic" + i);
                    }
                    $.removeCookie("drillDynamicLength");
                    $.removeCookie("drillStaticLength");
                    $(".startExercise").toggle(false);
                    $(".finishExercise").toggle(false);
                    $(".requestExercise").toggle(true);
                    $(".discardExercise").toggle(false);
                }
            }
        }
    });

    $(".requestExercise").click(function () {
        $.post(host,
            {
                "command": "REQUEST_EXERCISE"
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
                    $(".startExercise").toggle(false);
                    $(".finishExercise").toggle(false);
                    $(".requestExercise").toggle(false);
                    $(".discardExercise").toggle(true);
                }
            }
        }
    });

    $(".finishExercise").click(function () {
        currentToCookie();
        const outSet = [];
        for (let i = 1; i <= $.cookie("drillDynamicLength"); i++) {
            const cook = JSON.parse($.cookie("drillDynamic" + i));
            $.each(cook, function (index, value) {
                outSet.push(recoverValue(value));
            });
        }

        $.post(host,
            {
                "command": "FINISH_EXERCISE",
                "setGroup": JSON.stringify(outSet)
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
                    $(".setGroup").remove();
                    $(".drillList").remove();
                    for (let i = 1; i <= $.cookie("drillDynamicLength"); i++) {
                        $.removeCookie("drillDynamic" + i);
                    }
                    for (let i = 1; i <= $.cookie("drillStaticLength"); i++) {
                        $.removeCookie("drillStatic" + i);
                    }
                    $.removeCookie("drillDynamicLength");
                    $.removeCookie("drillStaticLength");
                    window.location.reload();
                }
            }
        }

        function recoverValue(value) {
            if (!isNaN(value.selfConsistent)) {
                value.selfConsistent = 0;
            }
            if (!isNaN(value.helpConsistent)) {
                value.helpConsistent = 0;
            }
            if (!isNaN(value.weightTool)) {
                value.weightTool = 0;
            }
            return value;
        }

        function currentToCookie() {
            const currentDrill = $(".drillName.selected");
            if (currentDrill.length !== 0) {
                const drillId = currentDrill[0].id;
                const dynSet = JSON.parse($.cookie("drillDynamic" + drillId));//!!!!!!!!!!!!!!

                const self = $(".setGroup")[0].getElementsByClassName("self");
                const help = $(".setGroup")[0].getElementsByClassName("help");
                const workWeight = $(".setGroup")[0].getElementsByClassName("workWeight");

                for (let i = 0; i < dynSet.length; i++) {
                    dynSet[i].selfConsistent = self[i].value;
                    dynSet[i].helpConsistent = help[i].value;
                    dynSet[i].weightTool = workWeight[i].value;
                }
                $.cookie("drillDynamic" + drillId, JSON.stringify(dynSet));
                var v = 0;
            }
        }
    });


    $(".changeTrainer").click(function () {
        popupChoice.classList.add("modal-content-show--flex");
        overlay.classList.add("modal-overlay-show");

        $.get(host,
            {
                "command": "OBTAIN_TRAINER"
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
                    const trainerGroup = response.data;
                    let eTable = "";
                    $.each(trainerGroup, function (index, value) {
                        eTable += "<li class=\"modal-choice__trainer trainerField\" id=\"" + value.userId + "\">\n" +
                            "<img class=\"modal-choice__photo small-image\" width=\"20px\" height=\"20px\" alt=\"Фото тренера\"" +
                            "src=\"" + value.photoPath + "\">\n" +
                            "<p class=\"modal-choice__nameblock\">\n" +
                            "<span class=\"modal-choice__name\">\n" +
                            value.surname +
                            "</span>\n" +
                            "<span class=\"modal-choice__surname\">\n" +
                            value.name +
                            "</span>\n" +
                            "</p>\n" +
                            "</li>";
                    });
                    $(".trainerList").html(eTable);
                }
            }
        }

        //клавиша подтверждения должна разблок тренера !!!!!!!!!!!!!!!!!!!!!!!!!!
    });

    $(".tClose").click(function () {
        popupChoice.classList.remove("modal-content-show--flex");
        overlay.classList.remove("modal-overlay-show");
        if ($(".without").length > 0) {
            $.get(host,
                {
                    "command": "OUT"
                }, request).fail(function () {
                window.location.href = "../index.jsp";
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
                        window.location.href = "../index.jsp";
                    }
                }
            }
        }
    });

    $(".trainerList").on("click", ".trainerField", function () {
        $(".trainerField").removeClass("selected");
        $(this).addClass("selected");
        $.cookie("chosenTrainer", this.id);
    });

    $(".trainerButton").click(function () {
        const trainerId = $.cookie("chosenTrainer");
        if (trainerId === undefined) {
            return;
        }

        $.post(host,
            {
                "command": "CHANGE_TRAINER",
                "long": trainerId
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
                    $(".tClose").removeClass("without");
                    if (!$(".discardExercise").is(":hidden")) {
                        $(".discardExercise").click();
                    }
                    $.removeCookie("chosenTrainer");
                    $(".tClose").click();
                    if ($(".user-info__photo").length === 0) {
                        window.location.reload();
                    }
                }
            }
        }
    });

    $(".exit").click(function () {
        $.get(host,
            {
                "command": "OUT"
            }, request).fail(function () {
            for (let i = 1; i <= $.cookie("drillDynamicLength"); i++) {
                $.removeCookie("drillDynamic" + i);
            }
            for (let i = 1; i <= $.cookie("drillStaticLength"); i++) {
                $.removeCookie("drillStatic" + i);
            }
            $.removeCookie("drillDynamicLength");
            $.removeCookie("drillStaticLength");
            $.removeCookie("chosenTrainer");
            window.location.href = "../index.jsp";
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
                    for (let i = 1; i <= $.cookie("drillDynamicLength"); i++) {
                        $.removeCookie("drillDynamic" + i);
                    }
                    for (let i = 1; i <= $.cookie("drillStaticLength"); i++) {
                        $.removeCookie("drillStatic" + i);
                    }
                    $.removeCookie("drillDynamicLength");
                    $.removeCookie("drillStaticLength");
                    $.removeCookie("chosenTrainer");
                    window.location.href = "../index.jsp";
                }
            }
        }
    });
});

