const host = "/start";
const serverErrorPage = "error500.jsp";
$(function () {
    $(".container").ready(function () {
        $.get(host,
            {
                "command": "IN_SYSTEM",
                "str": "ClientAuth.jsp"
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

    $(function () {
        $(".btn").click(function () {
            $(".form-signin").toggleClass("form-signin-left");
            $(".form-signup").toggleClass("form-signup-left");
            $(".frame").toggleClass("frame-long");
            $(".signin-active").toggleClass("signin-inactive");
            $(".signup-inactive").toggleClass("signup-active");
            $(".forgot").toggleClass("forgot-left");
            $(this).removeClass("idle").addClass("active");
        });
    });

    $(function () {
        $(".btn-signup").click(function () {
            $.post(host,
                {
                    "command": "SING_UP",
                    "name": $(".nameSingUp").val(),
                    "surname": $(".surnameSingUp").val(),
                    "email": $(".emailSingUp").val(),
                    "password": $(".passwordSingUp").val(),
                    "confirmPassword": $(".confirmPasswordSingUp").val(),
                    "bool": "false" //от клиента
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
//
    $(function () {
        $(".btn-signin").click(function () {
            $.post(host,
                {
                    "command": "CLIENT_SIGN_IN",
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
});