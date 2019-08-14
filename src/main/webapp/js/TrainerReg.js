const host = "/start";
const serverErrorPage = "error500.jsp";
$(function () {
    $(".registration-form").ready(function () {
        $.get(host,
            {
                "command": "IN_SYSTEM",
                "str": "TrainerReg.jsp"
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
                    }
                }
            }
        }
    });

    $(".regButton").click(function () {
        $.post(host,
            {
                "command": "SING_UP",
                "name": $(".nameSingUp").val(),
                "surname": $(".surnameSingUp").val(),
                "email": $(".emailSingUp").val(),
                "password": $(".passwordSingUp").val(),
                "confirmPassword": $(".confirmPasswordSingUp").val(),
                "bool": "true" //от тренера
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
                    }
                }
            }
        }
    });
});