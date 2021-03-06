const host = "/start";
const serverErrorPage = "error500.jsp";
$(function () {
    $(".wrapper").ready(function () {
        $.get(host,
            {
                "command" : "IN_SYSTEM",
                "str": "TrainerAuth.jsp"
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


    $(".login-button").click(function () {
        $.post(host,
            {
                "command": "TRAINER_SING_IN",
                "emailSingIn": $(".login").val(),
                "passwordSingIn": $(".password").val()
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